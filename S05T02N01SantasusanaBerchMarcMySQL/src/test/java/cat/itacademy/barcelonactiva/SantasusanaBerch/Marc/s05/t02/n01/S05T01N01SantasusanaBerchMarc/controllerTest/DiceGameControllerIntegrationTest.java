package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.controllerTest;


import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class DiceGameControllerIntegrationTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("DiceGameControllerIntegrationTest - Test return players list")
    @Test
    void whenListPlayers_thenReturnStatusOkAndPlayersList() throws Exception {
        mvc.perform(get("/api/v1/diceGame/players/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @DisplayName("DiceGameControllerIntegrationTest - Test for insert new player")
    @Test
    void whenCreatePlayer_thenReturnStatusCreatedAndPlayer() throws Exception {
        Player newPlayer = new Player("Anna");
        mvc.perform(post("/api/v1/diceGame/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Anna"));
    }


    @DisplayName("SuperheroControllerIntegrationTest - Test for save player return already exist exception")
    @Test
    void givenPlayerExist_whenCreatePlayer_thenReturnAlreadyExistException() throws Exception {
        Player newPlayer = new Player("Marc");
        mvc.perform(post("/api/v1/diceGame/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isImUsed());
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for update player")
    @Test
    void whenUpdatePlayer_thenReturnStatusOkAndUpdatedPlayer() throws Exception {
        Player updatedPlayer = Player.builder().id(1).name("Joan").build();
        mvc.perform(put("/api/v1/diceGame/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Joan"));
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for update player return PlayerNotFoundException")
    @Test
    void givenPlayerNotExist_whenUpdatePlayer_thenReturnNotFoundException() throws Exception {
        Player updatedPlayer = Player.builder().id(4).name("Miquel").build();
        mvc.perform(put("/api/v1/diceGame/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }




}
