package ru.nsu.ecoroads.configuration.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RabbitMQInitMapConfiguration {

    @Value("${eco-roads.configuration.rabbitmq.core.init-map.queue}")
    private String initMapCoreQueue;
    @Value("${eco-roads.configuration.rabbitmq.core.init-map.exchange}")
    private String initMapCoreExchange;
    @Value("${eco-roads.configuration.rabbitmq.core.init-map.routing}")
    private String initMapCoreRouting;

    @Bean
    public Queue initMapCoreQueue() {
        return new Queue(initMapCoreQueue, true);
    }

    @Bean
    public DirectExchange initMapCoreExchange() {
        return new DirectExchange(initMapCoreExchange);
    }

    @Bean
    public Binding initCoreMapBinding(Queue initMapCoreQueue,
                                      DirectExchange initMapCoreExchange) {
        return BindingBuilder
                .bind(initMapCoreQueue)
                .to(initMapCoreExchange)
                .with(initMapCoreRouting);
    }

}
