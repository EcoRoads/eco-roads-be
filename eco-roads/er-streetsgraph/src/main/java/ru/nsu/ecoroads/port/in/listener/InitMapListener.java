package ru.nsu.ecoroads.port.in.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nsu.ecoroads.exceptions.ServiceException;
import ru.nsu.ecoroads.model.ampq_messages.roads_covering.CreateRoadsCoveringBuildingMessage;
import ru.nsu.ecoroads.port.out.CoreCommunicationService;
import ru.nsu.ecoroads.services.osm.overpass.scrapper.contracts.RoadsCoveringProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitMapListener {

    private final RoadsCoveringProvider roadsCoveringProvider;
    private final CoreCommunicationService coreCommunicationService;

    @RabbitListener(queues = {"${eco-roads.configuration.rabbitmq.streets-graph.init-map.queue}"})
    public void handleMessage(CreateRoadsCoveringBuildingMessage message) throws ServiceException {
        log.info("Got message for map initialization core! {}", message);
        var roadsCovering = roadsCoveringProvider.provideRoadsAroundPoint(message.requestId(), message.config());
        coreCommunicationService.shareCreatedRoadsCovering(roadsCovering);
    }

}
