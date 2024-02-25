package ru.nsu.ecoroads.configuration.postprocessors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQConsumerInterceptor implements MessagePostProcessor {

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        log.info("Consumed message's body is: {}", message.getBody());
        return message;
    }

}
