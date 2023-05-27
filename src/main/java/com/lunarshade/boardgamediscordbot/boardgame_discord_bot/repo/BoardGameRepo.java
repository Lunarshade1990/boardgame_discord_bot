package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.entities.BoardGame;

public interface BoardGameRepo extends JpaRepository<BoardGame, Integer> {

    BoardGame findByTeseraId(long id);

    BoardGame findByBggId(long id);

    @Query("SELECT b FROM BoardGame b WHERE b.name=:name OR b.name2=:name")
    BoardGame findByName(String name);

    @Query("SELECT b FROM BoardGame b WHERE b.minPlayerNumber >= :min AND b.maxPlayerNumber <= :max")
    Set<BoardGame> findByPlayers(int min, int max);

    @Query("SELECT b FROM BoardGame b WHERE b.parent = :parentId")
    Set<BoardGame> findExpansions(int parentId);

    @Query("SELECT u.boardGames FROM User u WHERE u.id = :userId")
    Set<BoardGame> findBoardGamesByUserId(int userId);

    @Query("SELECT u.boardGames FROM User u WHERE u.discordId = :discordId")
    Set<BoardGame> findBoardGamesByDiscordId(long discordId);
}
