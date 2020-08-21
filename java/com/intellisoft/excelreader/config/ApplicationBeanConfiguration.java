package com.intellisoft.excelreader.config;




import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    static ModelMapper mapper;
    static ObjectMapper objectMapper;

    static {
        mapper = new ModelMapper();
    }

    static {
        objectMapper = new ObjectMapper();
    }

    @Bean
    public ObjectMapper objectMapper(){
            return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }



}
