package org.localhost.gamesboard.Game.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.localhost.gamesboard.GameManager.model.PlayerScore;
import org.localhost.gamesboard.Player.model.Player;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@ToString
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String gameName;
    private ZonedDateTime gameStartDate;
    private ZonedDateTime gameFinishDate;

    @Column(columnDefinition = "jsonb")
    @Type(JsonType.class)
    private List<PlayerScore> gameScore;

    private ZonedDateTime createdAt;

    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonManagedReference
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setGame(null);
    }
}