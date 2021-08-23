package com.tomek.maraton.group.infrastructure;

import com.tomek.maraton.commons.DomainEventPublisher;
import com.tomek.maraton.commons.SpringDomainEventPublisher;
import com.tomek.maraton.group.application.MaratonService;
import com.tomek.maraton.player.PlayerJoinedGroupEventHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaratonConfiguration {

    @Bean
    public MaratonService maratonService(RaceGroupRepository raceGroupRepository, PlayerRepository playerRepository,
                                         DomainEventPublisher domainEventPublisher) {
        return new MaratonService(raceGroupRepository, playerRepository, domainEventPublisher);
    }

    @Bean
    public RaceGroupRepository raceGroupRepository() {
        return new RaceGroupRepository();
    }

    @Bean
    public PlayerRepository playerRepository() {
        return new PlayerRepository();
    }

    @Bean
    public PlayerJoinedGroupEventHandler playerJoinedGroupEventHandler(PlayerRepository playerRepository) {
        return new PlayerJoinedGroupEventHandler(playerRepository);
    }

    @Bean
    public SpringDomainEventPublisher springDomainEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new SpringDomainEventPublisher(applicationEventPublisher);
    }
}
