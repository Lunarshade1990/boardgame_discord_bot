package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.dto;

import lombok.Data;

@Data
public class TeseraGame {
    Game game;

    @Data
    public static class Game {
        private long teseraId;
        private long bggId;
        private String title;
        private String title2;
        private String alias;
        private String descriptionShort;
        private String description;
        private String photoUrl;
        private int year;
        private int playersMin;
        private int playersMax;
        private int playtimeMin;
        private int playtimeMax;
    }
}
