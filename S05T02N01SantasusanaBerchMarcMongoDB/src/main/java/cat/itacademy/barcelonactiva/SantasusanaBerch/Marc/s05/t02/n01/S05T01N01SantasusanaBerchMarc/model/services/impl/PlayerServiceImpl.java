package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.impl;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.request.PlayerDTORequest;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.response.PlayerDTOResponse;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.exceptions.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.Mappers.PlayerDTOMapper;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerDTOMapper playerDTOMapper;




    @Override
    public void add(PlayerDTORequest playerDTORequest) {

        String name = playerDTORequest.getName();

        if(name == null || name.isEmpty())
        {
            name = "ANONYMOUS";
        }

        else {

            if (playerRepository.findByName(name).isPresent()) {
                throw new PlayerAlreadyExistsException("Player with name " + name + " already exists");
            }

        }

        Player player = new Player(name);

        this.playerRepository.save(player);

    }

    @Override
    public void update(String id, String name) {

        Player player = playerRepository.findById(id)
                .orElseThrow(()-> new PlayerNotFoundException("Player with id " + id + " is not found"));   ;


        if(name != null && !name.isEmpty())
        {
            if(playerRepository.findByName(name).isPresent())
            {
                throw new PlayerAlreadyExistsException("Player with name " + name + " already exists");

            }

            player.setName(name);

        }

        playerRepository.save(player);


    }



    @Override
    public List<PlayerDTOResponse> getAll() {
        return playerRepository.findAll()
                .stream()
                .map(playerDTOMapper).collect(Collectors.toList());
    }

    @Override
    public PlayerDTOResponse getLoser() {
        return getAll().stream().min(Comparator.comparing(PlayerDTOResponse::getWinPercentage))
                .orElseThrow(()-> new PlayerNotFoundException("No players found"));
    }

    @Override
    public PlayerDTOResponse getWinner() {
        return getAll().stream().max(Comparator.comparing(PlayerDTOResponse::getWinPercentage))
                .orElseThrow(()-> new PlayerNotFoundException("No players found"));
    }

    @Override
    public void updateWinPercentage(String playerId, Boolean win) {

        Player player = playerRepository.findById(playerId).
                orElseThrow(()-> new PlayerNotFoundException("Player with id " + playerId + " is not found"));

        if(win)
        {
            player.incWonGames();

        }


        player.incTotalGames();
        player.calculateWinPercentage();

        playerRepository.save(player);

    }

    @Override
    public void deleteAllPlayerGames(String playerId) {

        Player player = playerRepository.findById(playerId).orElseThrow(()-> new PlayerNotFoundException("Player is not found"));

        player.deleteAllGames();

        playerRepository.save(player);
    }


    @Override
    public float getAverageRanking() {

        List<Player> playerList = playerRepository.findAll();

        float winPercentage;
        long numPlayers;
        float averageWinPercentage;

        winPercentage = (float) playerList.stream().mapToDouble(Player::getWinPercentage).sum();

      numPlayers = playerRepository.count();

      averageWinPercentage = winPercentage / numPlayers;


        return averageWinPercentage;
    }


}
