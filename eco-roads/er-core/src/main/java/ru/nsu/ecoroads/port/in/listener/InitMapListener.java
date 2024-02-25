package ru.nsu.ecoroads.port.in.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nsu.ecoroads.model.ampq_messages.roads_covering.CreateRoadsCoveringSavingMessage;
import ru.nsu.ecoroads.port.out.db.service.RoadsCoveringHoldingService;

@Slf4j
@Service
@AllArgsConstructor
public class InitMapListener {

    private final RoadsCoveringHoldingService roadsCoveringHoldingService;

    @RabbitListener(queues = {"${eco-roads.configuration.rabbitmq.core.init-map.queue}"})
    public void handleMessage(CreateRoadsCoveringSavingMessage message) throws Exception {
        log.info("Got message with ready for saving roads covering {}", message.roadsCovering());
        roadsCoveringHoldingService.saveRoadsCovering(message.roadsCovering());
    }

}
