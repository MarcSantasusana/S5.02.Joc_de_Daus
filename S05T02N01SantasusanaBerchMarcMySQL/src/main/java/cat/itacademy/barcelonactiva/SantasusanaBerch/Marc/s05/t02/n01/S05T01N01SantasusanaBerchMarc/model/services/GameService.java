package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services;


import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Game;

import java.util.List;

public interface GameService {

   Game add(Integer playerId);
    void delete(Integer playerId);
    List<Game> getAll(Integer playerId);

}
