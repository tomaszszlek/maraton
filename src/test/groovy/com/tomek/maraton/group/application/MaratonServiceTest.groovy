package com.tomek.maraton.group.application

import com.tomek.maraton.group.domain.Player
import com.tomek.maraton.group.domain.PlayerId
import com.tomek.maraton.group.domain.RaceGroup
import com.tomek.maraton.group.domain.RaceGroupId
import com.tomek.maraton.group.infrastructure.PlayerRepository
import com.tomek.maraton.group.infrastructure.RaceGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
class MaratonServiceTest extends Specification {

    @Autowired
    MaratonService maratonService

    @Autowired
    RaceGroupRepository groupRepository

    @Autowired
    PlayerRepository playerRepository

    def "should successfully join group if player join for a first time"() {
        given:
        def raceGroupId = new RaceGroupId(UUID.randomUUID())
        def playerId = new PlayerId(UUID.randomUUID())
        groupRepository.save(createRaceGroup(raceGroupId))
        playerRepository.save(createPlayer(playerId))

        when:
        maratonService.joinMaraton(raceGroupId, playerId)

        then:
        playerShouldJoinGroup(raceGroupId, playerId)
    }

    def "shouldn't join group more than once"() {
        given:
        def raceGroupId = new RaceGroupId(UUID.randomUUID())
        def playerId = new PlayerId(UUID.randomUUID())
        groupRepository.save(createRaceGroup(raceGroupId))
        playerRepository.save(createPlayer(playerId))

        when:
        maratonService.joinMaraton(raceGroupId, playerId)
        maratonService.joinMaraton(raceGroupId, playerId)

        then:
        thrown(IllegalArgumentException)
    }

    private Player createPlayer(PlayerId playerId) {
        Player.builder()
                .playerId(playerId)
                .currentDistance("100km")
                .noOfGroupSwitches(0)
                .build()
    }

    private RaceGroup createRaceGroup(RaceGroupId raceGroupId) {
        RaceGroup.builder()
                .raceGroupId(raceGroupId)
                .distance("100km")
                .joinGroupDeadline(LocalDateTime.now().plusDays(7))
                .maxCapacity(5)
                .build()
    }

    boolean playerShouldJoinGroup(RaceGroupId raceGroupId, PlayerId playerId) {
        return groupRepository.findById(raceGroupId).members.isMember(playerId)
    }
}
