package com.tomek.maraton.group.application;

import com.tomek.maraton.commons.DomainEventPublisher;
import com.tomek.maraton.commons.Result;
import com.tomek.maraton.group.domain.Player;
import com.tomek.maraton.group.domain.PlayerId;
import com.tomek.maraton.group.domain.RaceGroup;
import com.tomek.maraton.group.domain.RaceGroupId;
import com.tomek.maraton.group.infrastructure.PlayerRepository;
import com.tomek.maraton.group.infrastructure.RaceGroupRepository;

import java.util.Objects;

public class MaratonService {

    private final RaceGroupRepository raceGroupRepository;
    private final PlayerRepository playerRepository;
    private final DomainEventPublisher domainEventPublisher;

    public MaratonService(RaceGroupRepository raceGroupRepository, PlayerRepository playerRepository, DomainEventPublisher domainEventPublisher) {
        this.raceGroupRepository = raceGroupRepository;
        this.playerRepository = playerRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    void joinMaraton(RaceGroupId raceGroupId, PlayerId playerId) {
        RaceGroup raceGroup = raceGroupRepository.findById(raceGroupId);
        if (raceGroup == null) {
            throw new IllegalStateException("Group has not been generated yet");
        }
        Player player = playerRepository.findById(playerId).orElseThrow();

        if (player.getNoOfGroupSwitches() > 0) {
            throw new IllegalArgumentException("Player switched groups too many times already");
        }
        if (player.getCurrentDistance().isEmpty() ||
                !Objects.equals(player.getCurrentDistance(), raceGroup.getDistance())) {
            throw new IllegalArgumentException("Player cannot swap between distances");
        }

        Result result = raceGroup.join(player.getPlayerId());

        result.events().forEach(domainEventPublisher::publish);
        raceGroupRepository.save(raceGroup);
    }
}
