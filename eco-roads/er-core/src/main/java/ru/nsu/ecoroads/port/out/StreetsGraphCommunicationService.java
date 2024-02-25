package ru.nsu.ecoroads.port.out;

import ru.nsu.ecoroads.model.common.configuration.RoadsCoveringBuildingConfig;

import java.util.UUID;

public interface StreetsGraphCommunicationService {

    void shareConfigurationForRoadCoveringBuilding(
            UUID requestId, RoadsCoveringBuildingConfig roadsCoveringBuildingConfig
    );

}
