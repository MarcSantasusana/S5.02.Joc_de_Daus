package cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.domain;

import cat.itacademy.barcelonactiva.SantasusanaBerch.Marc.s05.t02.n01.S05T01N01SantasusanaBerchMarc.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="`Id`")
    private Integer id;
    @Column(name="`Name`")
    private String name;
    @Column(name="`Creation_Time`")
    private Date creation_Time;
    @Column(name="`Win_Percentage`")
    private Float winPercentage;
    @Column(name="`Total_Games`")
    private Integer totalGames;
    @Column(name="`WonGames`")
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
