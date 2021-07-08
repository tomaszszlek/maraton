package com.tomek.maraton.group.domain;

import com.tomek.maraton.commons.Result;
import com.tomek.maraton.commons.events.PlayerAddedToRaceGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
class RaceGroup {

    @NonNull
    private final Integer raceId;

    @NonNull
    private final String distance;

    @NonNull
    private final LocalDateTime switchGroupDeadline;

    @NonNull
    private final int maxCapacity;

    @NonNull
    private final List<PlayerId> members;

    public Result join(Player player) {
        if (switchGroupDeadline.isAfter(LocalDateTime.now())) {
            return Result.failure("Cannot join group after deadline: " + switchGroupDeadline);
        }
        if (members.size() >= maxCapacity) {
            return Result.failure("Group has already max number of members");
        }
        if (player.getNoOfGroupSwitches() > 0) {
            return Result.failure("Player switched groups too many times already");
        }
        if (!Objects.equals(player.getCurrentDistance(), this.distance)) {
            return Result.failure("Player cannot swap between distances");
        }

        members.add(player.getPlayerId());
        return Result.success(PlayerAddedToRaceGroup.of(player.getPlayerId(), raceId));
    }
}
