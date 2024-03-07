package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.repositoryTest;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.PlayerRepository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;



@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerRepositoryTest {

    @Autowired
    private  PlayerRepository playerRepository;



    @DisplayName("PlayerRepositoryUnitTest - Test return players list")
    @Test
    public void findAll_should_return_player_list() {

        Player player = Player.builder().id(1).name("Miquel").build();
        Player player2 = Player.builder().id(2).name("Maria").build();

        playerRepository.save(player);
        playerRepository.save(player2);

        List<Player> playerList = playerRepository.findAll();

        Assertions.assertThat(playerList).isNotNull();
        Assertions.assertThat(playerList.size()).isEqualTo(2);
    }



    @DisplayName("PlayerRepositoryUnitTest - Test return player by id")
    @Test
    public void findById_should_return_player() {

        Player player = Player.builder().id(1).name("Miquel").build();

        playerRepository.save(player);

        Player savedPlayer = playerRepository.findById(1).get();

        Assertions.assertThat(savedPlayer).isNotNull();
    }

    @DisplayName("PlayerRepositoryUnitTest - Test return player by name")
    @Test
    public void findByName_should_return_player() {

        Player player = Player.builder().id(1).name("Miquel").build();

        playerRepository.save(player);

        Player savedPlayer = playerRepository.findByName("Miquel").get();

        Assertions.assertThat(savedPlayer).isNotNull();
        Assertions.assertThat(savedPlayer.getName()).isEqualTo("Miquel");
    }

    @DisplayName("PlayerRepositoryUnitTest - Test for insert new player")
    @Test
    public void save_should_insert_new_player() {

        Player player = Player.builder().
                name("Laura")
                .build();

        Player savedPlayer = playerRepository.save(player);

        Assertions.assertThat(savedPlayer).isNotNull();
        Assertions.assertThat(savedPlayer.getId()).isGreaterThan(0);
    }

    @DisplayName("PlayerRepositoryUnitTest - Test for update player")
    @Test
    public void save_should_update_existing_player() {

        Player player = Player.builder().
                name("Laura")
                .build();

        playerRepository.save(player);

        Player savedPlayer = playerRepository.findById(1).get();

        savedPlayer.setName("Oriol");

        Player updatedPlayer = playerRepository.save(savedPlayer);

        Assertions.assertThat(updatedPlayer.getName()).isEqualTo("Oriol");

    }


}
