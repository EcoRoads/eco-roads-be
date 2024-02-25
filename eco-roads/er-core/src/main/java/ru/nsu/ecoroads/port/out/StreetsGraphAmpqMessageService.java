package ru.nsu.ecoroads.port.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nsu.ecoroads.model.ampq_messages.roads_covering.CreateRoadsCoveringBuildingMessage;
import ru.nsu.ecoroads.model.common.configuration.RoadsCoveringBuildingConfig;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StreetsGraphAmpqMessageService implements StreetsGraphCommunicationService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${eco-roads.configuration.rabbitmq.streets-graph.init-map.exchange}")
    private String createGraphStreetsGraphExchange;
    @Value("${eco-roads.configuration.rabbitmq.streets-graph.init-map.routing}")
    private String createGraphStreetsGraphRouting;

    @Override
    public void shareConfigurationForRoadCoveringBuilding(
            UUID requestId, RoadsCoveringBuildingConfig roadsCoveringBuildingConfig
    ) {
        log.info("Sending configuration to MS Streets Graph");
        rabbitTemplate.convertAndSend(
                createGraphStreetsGraphExchange,
                createGraphStreetsGraphRouting,
                new CreateRoadsCoveringBuildingMessage(requestId, roadsCoveringBuildingConfig)
        );
    }

}
