package com.tomek.maraton.group.domain;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
public class Player {
    private final PlayerId playerId;
    private int noOfGroupSwitches = 0;
    private String currentDistance;

    public void increaseSwitchesNumber() {
        noOfGroupSwitches++;
    }

    public void setDistance(String newDistance) {
        this.currentDistance = newDistance;
    }
}
