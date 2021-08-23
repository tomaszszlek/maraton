package com.tomek.maraton.group.infrastructure;

import com.tomek.maraton.group.domain.Player;
import com.tomek.maraton.group.domain.PlayerId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PlayerRepository {
    private final Map<PlayerId, Player> players = new HashMap<>();

    public Optional<Player> findById(PlayerId playerId) {
        return Optional.ofNullable(players.get(playerId));
    }

    public void save(Player player) {
        players.put(player.getPlayerId(), player);
    }

}
