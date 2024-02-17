package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.controllers;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Game;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.request.PlayerDTORequest;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.response.PlayerDTOResponse;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.request.PlayerUpdateRequest;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.GameService;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/diceGame")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;




    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;

        this.playerService = playerService;
    }

    @GetMapping()
    public ResponseEntity<String> viewHomePage()
    {
        return new ResponseEntity<>("Dice game home page", HttpStatus.OK);
    }


    @PostMapping("/players/{id}/games/")
    public ResponseEntity<Game> play(@PathVariable(name = "id") Integer playerId)
    {

         Game game =  gameService.add(playerId);

         return new ResponseEntity<>(game, HttpStatus.CREATED);


    }



    @DeleteMapping("/players/{id}/games")
    public ResponseEntity<String> deletePlayerGames(@PathVariable(name = "id") Integer playerId)
    {
        gameService.delete(playerId);

        return new ResponseEntity<>("All games by player with id " + playerId + " have been deleted", HttpStatus.OK);

    }



    @GetMapping("/players/{id}/games")
    public ResponseEntity<List<Game>> getAllPlayerGames(@PathVariable(name = "id") Integer playerId, Model model)
    {
       List<Game> gameList = gameService.getAll(playerId);

       model.addAttribute("gameList", gameList);

        return new ResponseEntity<>(gameList, HttpStatus.OK);

    }

    @PostMapping("/players")
    public ResponseEntity<String> addPlayer(@RequestBody PlayerDTORequest player)
    {
        playerService.add(player);

        return new ResponseEntity<>("Player has been added", HttpStatus.CREATED);
    }


    @PutMapping("/players")
    public ResponseEntity<String> updatePlayer(@RequestBody PlayerUpdateRequest playerUpdateRequest)
    {
        Integer id = playerUpdateRequest.id();
        String name = playerUpdateRequest.name();

        playerService.update(id, name);


        return new ResponseEntity<>("Player has been updated", HttpStatus.OK);


    }


    @GetMapping("/players/")
    public ResponseEntity<List<PlayerDTOResponse>> getAllPlayers()
    {
        List<PlayerDTOResponse> playerList = playerService.getAll();

        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @GetMapping("/players/ranking")
    public ResponseEntity<Float> getRanking()
    {
        float averageWinPercentage = playerService.getAverageRanking();

        return new ResponseEntity<>(averageWinPercentage, HttpStatus.OK);
    }

    @GetMapping("/players/ranking/loser")
    public ResponseEntity<PlayerDTOResponse> getLoser()
    {
        PlayerDTOResponse player = playerService.getLoser();

        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @GetMapping("/players/ranking/winner")
    public ResponseEntity<PlayerDTOResponse> getWinner()
    {
        PlayerDTOResponse player = playerService.getWinner();


        return new ResponseEntity<>(player, HttpStatus.OK);
    }

}
