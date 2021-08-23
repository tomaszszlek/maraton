package com.tomek.maraton.group.domain;

import com.tomek.maraton.commons.Result;
import com.tomek.maraton.commons.events.PlayerAddedToRaceGroup;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Value
@Builder
public class RaceGroup {

    @NonNull
    RaceGroupId raceGroupId;

    @NonNull
    String distance;

    @NonNull
    LocalDateTime joinGroupDeadline;

    @NonNull
    int maxCapacity;

    @NonNull
    Members members = new Members();

    public Result join(PlayerId player) {
        if (joinGroupDeadline.isBefore(LocalDateTime.now())) {
            return Result.failure("Cannot join group after deadline: " + joinGroupDeadline);
        }
        if (members.size() >= maxCapacity) {
            return Result.failure("Group has already max number of members");
        }

        members.add(player);
        return Result.success(PlayerAddedToRaceGroup.of(player, raceGroupId, distance));
    }

    @Value
    private static class Members {
        List<PlayerId> members = new ArrayList<>();

        public boolean isMember(PlayerId playerId) {
            return members.stream().anyMatch(player ->
                    Objects.equals(playerId, player));
        }

        public int size() {
            return members.size();
        }

        public void add(PlayerId playerId) {
            members.add(playerId);
        }

    }

}
