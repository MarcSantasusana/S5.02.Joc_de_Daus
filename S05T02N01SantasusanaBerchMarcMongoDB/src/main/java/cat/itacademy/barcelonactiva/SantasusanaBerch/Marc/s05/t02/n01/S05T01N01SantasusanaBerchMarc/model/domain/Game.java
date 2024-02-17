package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("games")
@Getter
@Setter
@NoArgsConstructor
public class Game {

    @Id
    @Indexed(unique=true)
    private String id;
    private Integer value;
    private Boolean win;
    private String playerId;

    public Game(String playerId, int value)
    {
        this.playerId = playerId;
        this.value = value;
        setWin();
    }



    public boolean isWin() {
        return win;
    }

    private void setWin()
    {
        win = value == 7;
    }


}
