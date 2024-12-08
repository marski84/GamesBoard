package org.localhost.gamesboard.Player.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.localhost.gamesboard.Player.model.Player;
import org.localhost.gamesboard.Player.service.PlayerService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class PlayerControllerIntegrationTests {

    private MockMvc mockMvc;
    private PlayerService playerService;


    @BeforeEach()
    public void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.playerService = webApplicationContext.getBean(PlayerService.class);
    }

    private final String API_URL = "/player";

    @Test
    void addPlayerShouldReturn200() throws Exception {
        mockMvc.perform(post(API_URL + "/testPlayerName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value("testPlayerName"));
    }

    @Test
    void removePlayerShouldReturn200() throws Exception {
        Player testPlayer = playerService.getPlayerData(1);

        mockMvc.perform(delete(API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value(testPlayer.getPlayerNickname()))
                .andExpect(jsonPath("$.playerId").value(testPlayer.getId()));
    }

    @Test
    void getPlayerShouldReturn200WhenPlayerExistsAndSearchByPlayerName() throws Exception {
        Player testPlayer = playerService.getPlayerData(1);

        mockMvc.perform(get(API_URL + "/" + testPlayer.getPlayerNickname())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value(testPlayer.getPlayerNickname()))
                .andExpect(jsonPath("$.playerId").value(testPlayer.getId()));
    }

    @Test
    void getPlayerShouldReturn200WhenPlayerExistsAndSearchByPlayerId() throws Exception {
        Player testPlayer = playerService.getPlayerData(1);

        mockMvc.perform(get(API_URL + "/id/" + testPlayer.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerName").value(testPlayer.getPlayerNickname()))
                .andExpect(jsonPath("$.playerId").value(testPlayer.getId()));
    }

    @Test
    void getPlayerShouldReturn400WhenPlayerDoesNotExist() throws Exception {
        mockMvc.perform(get(API_URL + "/testPlayerName")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}
