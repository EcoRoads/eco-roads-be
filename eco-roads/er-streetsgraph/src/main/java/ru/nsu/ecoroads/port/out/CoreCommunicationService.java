package ru.nsu.ecoroads.port.out;

import ru.nsu.ecoroads.model.common.road.RoadsCovering;

public interface CoreCommunicationService {

    void shareCreatedRoadsCovering(RoadsCovering roadsCovering);

}
