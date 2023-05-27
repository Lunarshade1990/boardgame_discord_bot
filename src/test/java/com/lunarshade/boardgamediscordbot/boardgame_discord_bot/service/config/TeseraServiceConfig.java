package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.service.TeseraService;

@Configuration
public class TeseraServiceConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    };

    @Bean
    TeseraService teseraService() {
        return new TeseraService(restTemplate());
    }

}
