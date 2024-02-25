package ru.nsu.ecoroads.model.ampq_messages.roads_covering;

import ru.nsu.ecoroads.model.common.configuration.RoadsCoveringBuildingConfig;

import java.util.UUID;

public record CreateRoadsCoveringBuildingMessage(UUID requestId, RoadsCoveringBuildingConfig config) {
}
