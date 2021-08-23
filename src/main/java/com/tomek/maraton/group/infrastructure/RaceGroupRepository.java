package com.tomek.maraton.group.infrastructure;

import com.tomek.maraton.group.domain.RaceGroup;
import com.tomek.maraton.group.domain.RaceGroupId;

import java.util.HashMap;
import java.util.Map;

public class RaceGroupRepository {
    private final Map<RaceGroupId, RaceGroup> groups = new HashMap<>();

    public RaceGroup findById(RaceGroupId raceGroupId) {
        return groups.get(raceGroupId);
    }

    public void save(RaceGroup raceGroup) {
        groups.put(raceGroup.getRaceGroupId(), raceGroup);
    }

}
