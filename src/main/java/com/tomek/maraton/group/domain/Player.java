package com.tomek.maraton.group.domain;

import lombok.Getter;
import lombok.Value;

@Value
public class Player {
    PlayerId playerId;
    int noOfGroupSwitches;
    String currentDistance;
}
