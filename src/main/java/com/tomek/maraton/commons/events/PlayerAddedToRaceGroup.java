package com.tomek.maraton.commons.events;

import com.tomek.maraton.group.domain.Player;
import com.tomek.maraton.group.domain.PlayerId;
import com.tomek.maraton.group.domain.RaceGroup;
import com.tomek.maraton.group.domain.RaceGroupId;
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
    RaceGroupId raceGroupId;

    @NonNull
    String distance;

    public static PlayerAddedToRaceGroup of(PlayerId playerId, RaceGroupId raceGroupId, String distance) {
        return new PlayerAddedToRaceGroup(playerId, raceGroupId, distance);
    }
}
