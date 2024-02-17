package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer value;
    private Boolean win;
    private Integer playerId;

    public Game(int playerId, int value)
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
