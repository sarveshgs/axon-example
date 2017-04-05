package com.tw.axonbootcamp.conf;

import com.tw.axonbootcamp.domain.Account;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.repository.Repository;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;


@Configuration

public class AxonConf {

    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus());
        return processor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
        processor.setCommandBus(commandBus());
        return processor;
    }

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        return commandBus;
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }


    @Bean
    public CommandGateway commandGateway() {
        return new DefaultCommandGateway(commandBus());
    }


    @Bean
    public Repository accountRepository() {
        FileSystemEventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("data/eventstore")));
        EventSourcingRepository repository = new EventSourcingRepository(Account.class, eventStore);
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }


}
