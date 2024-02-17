package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.request.PlayerDTORequest;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.response.PlayerDTOResponse;

import java.util.List;

public interface PlayerService {

    void add(PlayerDTORequest player);
    void update(String id, String name);

    List<PlayerDTOResponse> getAll();

    PlayerDTOResponse getLoser();
    PlayerDTOResponse getWinner();

    void updateWinPercentage(String playerId, Boolean win);

    void deleteAllPlayerGames(String playerId);


    float getAverageRanking();
}
