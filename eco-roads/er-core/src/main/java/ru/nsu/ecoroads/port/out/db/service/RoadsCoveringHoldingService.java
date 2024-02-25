package ru.nsu.ecoroads.port.out.db.service;

import ru.nsu.ecoroads.exceptions.ServiceException;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;

import java.util.UUID;

public interface RoadsCoveringHoldingService {
    void saveRoadsCovering(RoadsCovering roadsCovering);

    RoadsCovering getRoadsCoveringById(UUID id) throws ServiceException;

}
