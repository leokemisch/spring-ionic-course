package com.kemisch.course.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kemisch.course.domain.BankSlipPayment;
import com.kemisch.course.domain.CardPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(BankSlipPayment.class);
                objectMapper.registerSubtypes(CardPayment.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}

