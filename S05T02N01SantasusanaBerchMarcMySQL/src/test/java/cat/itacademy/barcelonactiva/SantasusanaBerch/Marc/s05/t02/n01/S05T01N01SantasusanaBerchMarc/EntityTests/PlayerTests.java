package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.EntityTests;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {

    @Test
    public void winPercentageShouldBe25WhenTotalGamesAre4AndWonGamesIs1()
    {
        Player player = new Player();

        player.setTotalGames(4);
        player.setWonGames(1);

        player.calculateWinPercentage();

        assertEquals(25, player.getWinPercentage());

    }
}
