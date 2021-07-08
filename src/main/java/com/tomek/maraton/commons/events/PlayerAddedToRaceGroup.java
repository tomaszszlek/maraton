package com.tomek.maraton.commons.events;

import com.tomek.maraton.group.domain.Player;
import com.tomek.maraton.group.domain.PlayerId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class PlayerAddedToRaceGroup implements MaratonEvent {

    @NonNull
    UUID eventId = UUID.randomUUID();

    @NonNull
    PlayerId playerId;

    @NonNull
    Integer raceGroupId;

    public static PlayerAddedToRaceGroup of(PlayerId playerId, Integer raceGroupId) {
        return new PlayerAddedToRaceGroup(playerId, raceGroupId);
    }
}
