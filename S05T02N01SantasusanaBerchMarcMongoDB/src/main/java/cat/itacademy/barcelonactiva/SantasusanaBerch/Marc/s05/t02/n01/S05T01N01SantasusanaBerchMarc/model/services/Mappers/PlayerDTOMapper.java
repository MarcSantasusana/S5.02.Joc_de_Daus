package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.Mappers;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.response.PlayerDTOResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class PlayerDTOMapper implements Function<Player, PlayerDTOResponse> {

    @Override
    public PlayerDTOResponse apply(Player player) {

        return new PlayerDTOResponse(player.getName(), player.getWinPercentage());
    }
}
