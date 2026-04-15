package dev.java10x.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClienteConfig {

    @Value("${chatgpt.url}")
    private String chatGptUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(chatGptUrl)
                .build();
    }

}
