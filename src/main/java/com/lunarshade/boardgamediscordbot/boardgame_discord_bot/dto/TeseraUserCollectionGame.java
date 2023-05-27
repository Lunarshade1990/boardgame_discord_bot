package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.dto;

import lombok.Data;

@Data
public class TeseraUserCollectionGame {

    private Game game;

    @Data
    public static class Game {
        private String alias;
    }

    public String getAlias() {
        return this.game.alias;
    }
}
