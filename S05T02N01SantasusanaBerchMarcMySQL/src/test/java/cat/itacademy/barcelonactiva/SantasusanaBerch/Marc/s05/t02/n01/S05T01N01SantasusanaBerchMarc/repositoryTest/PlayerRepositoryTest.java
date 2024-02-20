package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.repositoryTest;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.PlayerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    private  PlayerRepository playerRepository;



    @DisplayName("PlayerRepositoryUnitTest - Test return players list")
    @Test
    void findAll_should_return_player_list() {

        List<Player> playerList = playerRepository.findAll();
        assertEquals(playerList.size(),3);
    }

    @DisplayName("PlayerRepositoryUnitTest - Test return player by id")
    @Test
    void findById_should_return_player() {
        Optional<Player> player = playerRepository.findById(1);
        assertTrue(player.isPresent());
    }

    @DisplayName("PlayerRepositoryUnitTest - Test for insert new player")
    @Test
    void save_should_insert_new_player() {
        Player saved = Player.builder().build();
        saved.setName("Laura");
        Player returnedPlayer = playerRepository.save(saved);
        assertNotNull(returnedPlayer);
        assertEquals(4, returnedPlayer.getId());
        assertEquals(saved.getName(), returnedPlayer.getName());
    }

    @DisplayName("PlayerRepositoryUnitTest - Test for update player")
    @Test
    void save_should_update_existing_player() {
        Optional<Player> existingPlayer = playerRepository.findById(1);
        assertTrue(existingPlayer.isPresent());
        existingPlayer.get().setName("Oriol");
        Player updatedPlayer = playerRepository.save(existingPlayer.get());
        assertNotNull(updatedPlayer);
        assertEquals("Oriol", updatedPlayer.getName());
    }

    @DisplayName("PlayerRepositoryUnitTest - Test for delete player by id")
    @Test
    void deleteById_should_delete_superHero() {
        playerRepository.deleteById(2);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            playerRepository.findById(2).get();
        });
        assertNotNull(exception);
        assertEquals(exception.getClass(), NoSuchElementException.class);
        assertEquals(exception.getMessage(),"No value present");
    }

}
