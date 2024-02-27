package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="`Id`")
    private Integer id;
    @Column(name="`Value`")
    private Integer value;
    @Column(name="`Win`")
    private Boolean win;
    @Column(name="`PlayerId`")
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
