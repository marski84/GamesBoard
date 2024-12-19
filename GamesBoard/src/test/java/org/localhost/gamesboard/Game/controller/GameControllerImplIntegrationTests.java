package org.localhost.gamesboard.Game.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Game.model.Game;
import org.localhost.gamesboard.Game.service.GameService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class GameControllerImplIntegrationTests {

    private MockMvc mockMvc;
    private GameService gameService;

    private final String API_URL = "/games/";
    private final int TEST_GAME_ID = 1;
    private final int NON_EXISTING_GAME_ID = 500;
    private final String NON_EXISTING_GAME_NAME = "non existing game";
    private final String TEST_GAME_NAME = "Poker Night";


    @BeforeEach()
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.gameService = webApplicationContext.getBean(GameService.class);
    }

    @Test
    void getGamesShouldReturn200() throws Exception {
        Game testGameData = gameService.getGameById(1);
        mockMvc.perform(get(API_URL + TEST_GAME_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerScores", hasSize(2)))
                .andExpect(jsonPath("$.playerScores[0].playerName").value(testGameData.getGameScore().get(0).getPlayerName()))
                .andExpect(jsonPath("$.playerScores[0].score").value(testGameData.getGameScore().get(0).getScore()))
                .andExpect(jsonPath("$.playerScores[1].playerName").value(testGameData.getGameScore().get(1).getPlayerName()))
                .andExpect(jsonPath("$.playerScores[1].score").value(testGameData.getGameScore().get(1).getScore()));
    }

    @Test
    void getGameShouldReturn400() throws Exception {
        mockMvc.perform(get(API_URL + NON_EXISTING_GAME_ID))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getGameByNameShouldReturn200() throws Exception {
        Game testGameData = gameService.getGameByName(TEST_GAME_NAME);


        mockMvc.perform(get("/games/get-game-by-name/" + TEST_GAME_NAME))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameName").value(testGameData.getGameName()))
                .andExpect(jsonPath("$.creationDate").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.finishDate").exists())
                .andExpect(jsonPath("$.playerScores").exists())
                .andExpect(jsonPath("$.playerScores", hasSize(testGameData.getGameScore().size())))
                .andExpect(jsonPath("$.playerScores[0].playerName").value(testGameData.getGameScore().get(0).getPlayerName()))
                .andExpect(jsonPath("$.playerScores[0].score").value(testGameData.getGameScore().get(0).getScore()));
    }

    @Test
    void createGameShouldReturn201() throws Exception {
        String testGameName = "test";

        mockMvc.perform(post(API_URL + "create/" + testGameName))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.gameName").value(testGameName))
                .andExpect(jsonPath("$.playerScores", hasSize(0)))
                .andExpect(jsonPath("$.playerScores").isEmpty());
    }

    @Test
    void deleteGameShouldReturn200() throws Exception {
        mockMvc.perform(delete(API_URL + TEST_GAME_ID))
                .andExpect(status().isNoContent());
    }
}
