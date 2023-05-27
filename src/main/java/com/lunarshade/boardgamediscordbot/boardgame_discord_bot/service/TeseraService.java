package com.lunarshade.boardgamediscordbot.boardgame_discord_bot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.dto.TeseraGame;
import com.lunarshade.boardgamediscordbot.boardgame_discord_bot.dto.TeseraUserCollectionGame;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeseraService {
    private final RestTemplate restTemplate;
    @Value("${tesera.url}")
    private String BASE_URL;
    @Value("${tesera.url.collection.count}")
    private String COLLECTION_COUNT_URL;
    @Value("${tesera.url.collection.collection}")
    private String COLLECTION_URL;
    @Value("${tesera.url.collection.game}")
    private String GAME_URL;

    private final String TOTAL_COUNT_FIELD = "totalCount";
    private final int LIMIT = 15;

    public List<TeseraGame.Game> getUserGames(String nickname) {
        List<TeseraUserCollectionGame> gameList = fetchUserGameCollection(nickname);
        List<String> aliases = gameList.stream()
                .map(TeseraUserCollectionGame::getAlias).toList();
        return aliases.parallelStream()
                .map(this::fetchGame).toList();
    }

    public List<TeseraUserCollectionGame> fetchUserGameCollection(String nickname) {
        List<TeseraUserCollectionGame> gameList = new ArrayList<>();
        int maxOffset = getPageCount(fetchOwnGameCount(nickname));
        IntStream.range(0, maxOffset)
                .forEach(offset -> gameList.addAll(fetchCollectionPartForOffset(nickname, offset)));
        return gameList;

    }

    protected TeseraGame.Game fetchGame(String alias) {
        return restTemplate.getForObject(formatUrl(GAME_URL, alias), TeseraGame.class).getGame();
    }

    protected List<TeseraUserCollectionGame> fetchCollectionPartForOffset(String nickname, int offset) {
        return List.of(restTemplate.getForObject(formatUrl(COLLECTION_URL, nickname, LIMIT, offset),
                TeseraUserCollectionGame[].class));
    }

    protected int fetchOwnGameCount(String nickname) {
        TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<>() {
        };
        String response = restTemplate.getForObject(formatUrl(COLLECTION_COUNT_URL, nickname),
                String.class);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Integer> countMap;

        try {
            countMap = mapper.readValue(response, typeRef);
            return countMap.get(TOTAL_COUNT_FIELD);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String formatUrl(String link, String alias) {
        return String.format(BASE_URL + link, alias);
    }

    private String formatUrl(String link, String alias, int limit, int offset) {
        return String.format(BASE_URL + link, alias, limit, offset);
    }

    private int getPageCount(int gameCount) {
        return (int) Math.ceil((double) gameCount / LIMIT);
    }
}
