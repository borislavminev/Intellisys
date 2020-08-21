package com.intellisoft.excelreader.web.controller;


import com.intellisoft.excelreader.domain.entity.Role;
import com.intellisoft.excelreader.domain.entity.User;
import com.intellisoft.excelreader.domain.model.bindingModel.UserRegisterBindingModel;
import com.intellisoft.excelreader.repository.RolesRepository;
import com.intellisoft.excelreader.repository.UsersRepository;
import com.intellisoft.excelreader.service.RoleModelService;
import com.intellisoft.excelreader.service.UserModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Controller
public class UserController {
    private final
    UsersRepository usersRepository;
    private final
    RolesRepository rolesRepository;
    private final
    RoleModelService roleModelService;

    private final ModelMapper modelMapper;
    UserModelService userModelService;

    @Autowired
    public UserController(UsersRepository usersRepository, RolesRepository rolesRepository, RoleModelService roleModelService, RoleModelService roleModelService1, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.roleModelService = roleModelService1;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (rolesRepository.findAll().size()==0){
            roleModelService.seedRolesInDb();
        }
        model.addAttribute("view", "user/register");

        return "home";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerProcess(UserRegisterBindingModel ub){

        if(!ub.getPassword().equals(ub.getConfirmPassword())){
            return "redirect:/register";
        }


        String passWord = ub.getPassword();


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userPassword = bCryptPasswordEncoder.encode(passWord);
        Role userRole = this.rolesRepository.findByName("ROLE_USER");
        User user = new User(
                ub.getEmail(),
                userPassword,
                Collections.singleton(userRole)
        );

        this.usersRepository.saveAndFlush(user);

        return "redirect:/login";
    }

    @RequestMapping(value="/login")
    public String login(Model model){
        model.addAttribute("view", "user/login");

        return "home";
    }
    @RequestMapping(value="/bad-login")
    public String badLogin(Model model){
        model.addAttribute("view", "user/wrong-login");

        return "home";
    }
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userModelService.findByEmail(principal.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        model.addAttribute("user", user);
        model.addAttribute("view", "user/profile");

        return "home";
    }

    @GetMapping("/exception-login")
    public String exceptionLogin(Model model){
        String message = "Unsuccsessfull Login";
        model.addAttribute("view","exception/exception");
        model.addAttribute(message);
        return "home";
    }





//    @GetMapping("/contact")
//    @PreAuthorize("isAuthenticated()")
//    public String ContactForm(Model model){
//
//        model.addAttribute("view", "user/contact");
//
//        return "home";
//    }

    @GetMapping("/under-construction")
    public String underConstruction(Model model){

        model.addAttribute("view", "home/under-construction");

        return "home";
    }
//
//    @GetMapping("/check-email")
//    public boolean checkEmail(String email){
//       return userModelService.findUserByEmail(email);
//    }


}

