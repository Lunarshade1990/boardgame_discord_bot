package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractEntity {
    private Long discordId;
    private String teseraNickname;
    private String username;
    private String discriminator;
    private String avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<BoardGame> boardGames;
}
