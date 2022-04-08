package com.hercules.configuration;

import feign.ExceptionPropagationPolicy;
import feign.Feign;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
@RequiredArgsConstructor
public class FeignConfiguration {


    @Bean
    public Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Feign.Builder feignBuilder(Retryer retryer) {
        return Feign.builder()
                .exceptionPropagationPolicy(ExceptionPropagationPolicy.NONE)
                .errorDecoder(new CustomErrorDecoder())
                .retryer(retryer);
    }
}
