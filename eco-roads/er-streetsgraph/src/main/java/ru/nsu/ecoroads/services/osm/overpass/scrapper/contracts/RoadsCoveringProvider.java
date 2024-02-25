package ru.nsu.ecoroads.services.osm.overpass.scrapper.contracts;

import ru.nsu.ecoroads.model.common.configuration.RoadsCoveringBuildingConfig;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;

import java.util.UUID;

public interface RoadsCoveringProvider {

    RoadsCovering provideRoadsAroundPoint(UUID roadsCoveringId, RoadsCoveringBuildingConfig config);

}
