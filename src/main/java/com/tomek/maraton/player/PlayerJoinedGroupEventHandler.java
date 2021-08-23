package com.tomek.maraton.player;

import com.tomek.maraton.commons.events.PlayerAddedToRaceGroup;
import com.tomek.maraton.group.domain.Player;
import com.tomek.maraton.group.infrastructure.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class PlayerJoinedGroupEventHandler {

    private final PlayerRepository playerRepository;

    @EventListener
    public void handle(PlayerAddedToRaceGroup event) {
        playerRepository.findById(event.getPlayerId()).ifPresent(
                player -> increaseGroupSwitchCounter(event, player));
    }

    private void increaseGroupSwitchCounter(PlayerAddedToRaceGroup event, Player player) {
        player.increaseSwitchesNumber();
        player.setDistance(event.getDistance());
        playerRepository.save(player);
    }
}
