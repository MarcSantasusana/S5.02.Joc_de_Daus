package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;



@Document("players")
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @Indexed(unique=true)
    private String id;
    private String name;
    private Date creation_Time;
    private Float winPercentage;
    private Integer totalGames;
    private Integer wonGames;



    public Player(String name)
    {
        this.name = name;

        creation_Time = new Date();

        totalGames = 0;

        wonGames = 0;

        winPercentage = 0f;

    }


    public void deleteAllGames()
    {
        totalGames=0;
        wonGames = 0;

        winPercentage = 0f;

    }

    public void incTotalGames()
    {
        totalGames++;
    }

    public void incWonGames()
    {
        wonGames++;

    }

    public void calculateWinPercentage() {

        winPercentage = (float) wonGames*100 / totalGames;

    }


}
