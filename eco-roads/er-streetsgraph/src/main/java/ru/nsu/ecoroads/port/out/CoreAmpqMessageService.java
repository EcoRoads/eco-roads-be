package ru.nsu.ecoroads.port.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.nsu.ecoroads.model.ampq_messages.roads_covering.CreateRoadsCoveringSavingMessage;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreAmpqMessageService implements CoreCommunicationService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${eco-roads.configuration.rabbitmq.core.init-map.exchange}")
    private String initMapCoreExchange;
    @Value("${eco-roads.configuration.rabbitmq.core.init-map.routing}")
    private String initMapCoreRouting;

    @Override
    public void shareCreatedRoadsCovering(RoadsCovering roadsCovering) {
        log.info("Sending roadsMap covering with paths to MS Core");

        rabbitTemplate.convertAndSend(
                initMapCoreExchange,
                initMapCoreRouting,
                new CreateRoadsCoveringSavingMessage(roadsCovering)
        );
    }

}
