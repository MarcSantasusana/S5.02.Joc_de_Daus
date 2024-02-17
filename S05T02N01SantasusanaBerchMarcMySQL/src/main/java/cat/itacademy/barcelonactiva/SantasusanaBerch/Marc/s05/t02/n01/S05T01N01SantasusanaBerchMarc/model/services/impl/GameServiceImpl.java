package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.impl;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Game;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.GameService;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements  GameService{

    private final GameRepository gameRepository;
    private final PlayerService playerService;


    @Override
    public Game add(Integer playerId) {

        Random rand = new Random();

        int max= 12;
        int min = 1;

        int value = rand.nextInt(max - min + 1) + min;

        Game game = new Game(playerId, value);

        boolean win = game.isWin();

        gameRepository.save(game);

        playerService.updateWinPercentage(playerId, win);

        return game;




    }

    @Override
    @Transactional
    public void delete(Integer playerId) {

      gameRepository.deleteAllByPlayerId(playerId);

      playerService.deleteAllPlayerGames(playerId);


    }

    @Override
    public List<Game> getAll(Integer playerId) {

        return gameRepository.findAllByPlayerId(playerId);

    }
}
