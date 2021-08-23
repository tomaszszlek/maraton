package com.tomek.maraton.commons;

import com.tomek.maraton.commons.events.MaratonEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class SpringDomainEventPublisher implements DomainEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(MaratonEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
