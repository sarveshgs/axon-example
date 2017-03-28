package com.tw.axonbootcamp.domain;

import org.axonframework.domain.AbstractAggregateRoot;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;

public class Account extends AbstractAnnotatedAggregateRoot<String> {

    @Override
    public String getIdentifier() {
        return null;
    }


}
