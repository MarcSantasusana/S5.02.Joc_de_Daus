package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void isWinShouldBeTrueIfValueIs7()
    {
        Game game = new Game("543", 7);

       assertEquals(true, game.getWin());

    }

    @Test
    public void isWinShouldBeFalseIfValueIsNot7()
    {
        Game game = new Game("543", 4);

        assertEquals(false, game.getWin());

    }
}
