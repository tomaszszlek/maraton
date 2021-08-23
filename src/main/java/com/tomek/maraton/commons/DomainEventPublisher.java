package com.tomek.maraton.commons;

import com.tomek.maraton.commons.events.MaratonEvent;

public interface DomainEventPublisher {

    void publish(MaratonEvent event);
}
