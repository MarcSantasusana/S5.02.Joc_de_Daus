package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.ServiceTest;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Game;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.Mappers.PlayerDTOMapper;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.impl.GameServiceImpl;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.impl.PlayerServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameServiceTest {


    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerServiceImpl playerService;

    @InjectMocks
    private GameServiceImpl gameService;

    @Spy
    private PlayerDTOMapper playerDTOMapper;


    @BeforeEach
    public void setUp()
    {

        playerDTOMapper = new PlayerDTOMapper();


    }


    @Test
    public void GameService_getAllPlayerGames(){

        Game game1 = Game.builder()
                .id(1)
                .playerId(1)
                .build();

        Game game2 = Game.builder()
                .id(2)
                .playerId(1)
                .build();

        List<Game> gameList = new ArrayList<>();

        gameList.add(game1);
        gameList.add(game2);

        when(gameRepository.findAllByPlayerId(1)).thenReturn(gameList);

       List<Game> returnedList =  gameService.getAll(1);

       assertEquals(2, returnedList.size());

    }




    }


