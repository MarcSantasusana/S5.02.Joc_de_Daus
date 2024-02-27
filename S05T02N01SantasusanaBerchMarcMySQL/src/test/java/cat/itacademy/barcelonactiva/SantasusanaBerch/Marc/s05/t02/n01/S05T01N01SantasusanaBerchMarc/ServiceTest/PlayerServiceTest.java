package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.ServiceTest;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain.Player;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.response.PlayerDTOResponse;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.exceptions.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.Mappers.PlayerDTOMapper;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.services.impl.PlayerServiceImpl;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.dto.request.PlayerDTORequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Spy
    private PlayerDTOMapper playerDTOMapper;


    @BeforeEach
    public void setUp()
    {

        playerDTOMapper = new PlayerDTOMapper();


    }





    @Test
    public void PlayerService_addPlayer_withValidName(){

        PlayerDTORequest playerDTORequest = PlayerDTORequest.builder()
                .name("Marc")
                .build();


        when(playerRepository.findByName("Marc")).thenReturn(Optional.empty());

        playerService.add(playerDTORequest);

        verify(playerRepository).save(argThat(player -> player.getName().equals("Marc")));



    }

    @Test
    public void PlayerService_addPlayer_withNoName(){

        PlayerDTORequest playerDTORequest = PlayerDTORequest.builder()
                .build();


        playerService.add(playerDTORequest);

        verify(playerRepository).save(argThat(player -> player.getName().equals("ANONYMOUS")));

    }



    @Test
    public void PlayerService_addPlayer_withExistingName(){

        PlayerDTORequest playerDTORequest = PlayerDTORequest.builder()
                .name("Marc")
                .build();


        when(playerRepository.findByName("Marc")).thenReturn(Optional.of(new Player("Marc")));

        assertThrows(PlayerAlreadyExistsException.class, ()-> playerService.add(playerDTORequest));



        verify(playerRepository, never()).save(any());


    }

    @Test
    public void PlayerService_updatePlayer_withValidName(){

        Player player = Player.builder()
                .name("Marc").build();


        when(playerRepository.findById(1)).thenReturn(Optional.of(player));

        when(playerRepository.findByName("Pere")).thenReturn(Optional.empty());

        playerService.update(1, "Pere");


        assertEquals("Pere", player.getName());


    }

    @Test
    public void PlayerService_getAllPlayers(){

        Player player = Player.builder()
                .name("Marc")
                .winPercentage(20f)
                .build();

        Player player2 = Player.builder()
                .name("Anna")
                .winPercentage(10f)
                .build();

        List<Player> playerList = new ArrayList<>();

        playerList.add(player);

        playerList.add(player2);


        when(playerRepository.findAll()).thenReturn(playerList);

        List<PlayerDTOResponse> result = playerService.getAll();

        verify(playerRepository).findAll();

        List<PlayerDTOResponse> expected = playerList.stream()
                        .map(playerDTOMapper)
                                .toList();

        assertEquals(expected, result);





    }

}
