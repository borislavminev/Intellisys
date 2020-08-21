package com.intellisoft.excelreader.config;


import com.intellisoft.excelreader.repository.UsersRepository;
import com.intellisoft.excelreader.service.UsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;



@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UsersDetailsService usersDetailsService;

    @Autowired
    public SecurityConfiguration(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(this.usersDetailsService).passwordEncoder
                (new BCryptPasswordEncoder());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().csrfTokenRepository(csrfTokenRepository());
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**","/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/","/login", "/register").anonymous()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/").failureForwardUrl("/badLogin")
                .and()
                .authorizeRequests().antMatchers("/admin/**","/converter","/uploadStatus","/download","/send", "/uploadPnr")
                .permitAll();
    }
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    private CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository =
                new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

}

