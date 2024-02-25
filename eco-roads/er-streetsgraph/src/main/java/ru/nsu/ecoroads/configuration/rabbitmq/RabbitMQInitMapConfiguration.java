package ru.nsu.ecoroads.configuration.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMQInitMapConfiguration {

    @Value("${eco-roads.configuration.rabbitmq.streets-graph.init-map.queue}")
    private String initMapStreetsGraphQueue;
    @Value("${eco-roads.configuration.rabbitmq.streets-graph.init-map.exchange}")
    private String initMapStreetsGraphExchange;
    @Value("${eco-roads.configuration.rabbitmq.streets-graph.init-map.routing}")
    private String initMapStreetsGraphRouting;

    @Bean
    public Queue initMapStreetsGraphQueue() {
        return new Queue(initMapStreetsGraphQueue, true);
    }

    @Bean
    public DirectExchange initMapStreetsGraphExchange() {
        return new DirectExchange(initMapStreetsGraphExchange);
    }

    @Bean
    public Binding initMapStreetsGraphRouting(Queue initMapStreetsGraphQueue,
                                              DirectExchange initMapStreetsGraphExchange) {
        return BindingBuilder
                .bind(initMapStreetsGraphQueue)
                .to(initMapStreetsGraphExchange)
                .with(initMapStreetsGraphRouting);
    }
}
