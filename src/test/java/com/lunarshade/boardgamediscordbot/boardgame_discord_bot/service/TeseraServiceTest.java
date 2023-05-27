package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.dto.TeseraGame;
import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.dto.TeseraUserCollectionGame;
import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.service.config.TeseraServiceConfig;

//Todo Mock server
@SpringBootTest(classes = TeseraServiceConfig.class)
public class TeseraServiceTest {
    @Autowired
    TeseraService service;

    @Test
    void getOwnGameCount() {
        int actual = service.fetchOwnGameCount("lunarshade");
        assertThat(actual).isEqualTo(60);
    }

    @Test
    void getUserGameCollection() {
        List<TeseraUserCollectionGame> actual = service.fetchUserGameCollection("lunarshade");
        assertThat(actual).hasSize(60);
    }

    @Test
    void getUserGames() {
        List<TeseraGame.Game> actual = service.getUserGames("lunarshade");
        assertThat(actual).hasSize(60);
    }

}
