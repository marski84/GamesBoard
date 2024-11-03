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

import java.time.Instant;
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
    private Instant gameStartDate;
    private Instant gameFinishDate;

    @Column(columnDefinition = "jsonb")
    @Type(JsonType.class)
    private List<PlayerScore> gameScore;

    private Instant createdAt;

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