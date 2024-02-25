package ru.nsu.ecoroads.model.common.road;

import ru.nsu.ecoroads.model.common.base.Coordinates;

import java.util.UUID;

public record RoadPoint(UUID id, Coordinates coordinates) {

    @Override
    public String toString() {
        return "RP[lat: %f, long: %f]".formatted(coordinates.latitude(), coordinates.longitude());
    }
}
