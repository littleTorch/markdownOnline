package com.torch.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorResourceFactory;

@Configuration
public class ReactNettyConfiguration {

    @Bean
    public ReactorResourceFactory reactorClientResourceFactory() {
        System.setProperty("reactor.netty.ioWorkerCount", "32");
        return new ReactorResourceFactory();
    }
}
