package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.repositoryTest;


import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Game;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @DisplayName("GameRepositoryUnitTest - Test return game list")
    @Test
    public void findAll_should_return_game_list() {

        Game game = Game.builder().id(1).build();
        Game game2 = Game.builder().id(2).build();

        gameRepository.save(game);
        gameRepository.save(game2);

        List<Game> gameList = gameRepository.findAll();

        Assertions.assertThat(gameList).isNotNull();
        Assertions.assertThat(gameList.size()).isEqualTo(2);
    }

    @DisplayName("GameRepositoryUnitTest - Test return game list by playerId")
    @Test
    public void findAllByPlayerId_should_return_game_list() {

        Game game = Game.builder().id(1).playerId(1).build();
        Game game2 = Game.builder().id(2).playerId(1).build();
        Game game3 = Game.builder().id(3).playerId(2).build();

        gameRepository.save(game);
        gameRepository.save(game2);
        gameRepository.save(game3);

        List<Game> gameList = gameRepository.findAllByPlayerId(1);

        Assertions.assertThat(gameList).isNotNull();
        Assertions.assertThat(gameList.size()).isEqualTo(2);
    }



    @DisplayName("GameRepositoryUnitTest - Test delete game by playerId")
    @Test
    public void deleteByPlayerId_should_return_empty() {

        Game game = Game.builder().id(1).playerId(1).build();
        Game game2 = Game.builder().id(2).playerId(1).build();
        Game game3 = Game.builder().id(3).playerId(2).build();

        gameRepository.save(game);
        gameRepository.save(game2);
        gameRepository.save(game3);

        gameRepository.deleteAllByPlayerId(1);

        List<Game> deletedGameList = gameRepository.findAllByPlayerId(1);
        List<Game> savedGameList = gameRepository.findAllByPlayerId(2);

        Assertions.assertThat(deletedGameList).isEmpty();
        Assertions.assertThat(savedGameList).isNotNull();


    }



    @DisplayName("GameRepositoryUnitTest - Test for insert new game")
    @Test
    public void save_should_insert_new_Game() {

        Game game = Game.builder().id(1).build();

        Game savedGame = gameRepository.save(game);

        Assertions.assertThat(savedGame).isNotNull();
        Assertions.assertThat(savedGame.getId()).isGreaterThan(0);
    }



}
