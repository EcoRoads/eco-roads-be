package ru.nsu.ecoroads.model.common.configuration;

import ru.nsu.ecoroads.model.common.base.Coordinates;

public record RoadsCoveringBuildingConfig(
        String name,
        Coordinates center,
        double radiusAround
) {
}
