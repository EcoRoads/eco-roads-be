package ru.nsu.ecoroads.model.common.road;

import lombok.Builder;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record RoadsCovering(
        UUID id,
        String name,
        Map<UUID, Road> roadsMap,
        RoadsGraph roadsGraph,
        Integer totalRoadsNumber
) {

    @Override
    public String toString() {
        var roadsString = roadsMap.values().stream()
                .map(Road::toString)
                .collect(Collectors.joining("\n"));
        return """
                Covering ID: %s
                Roads: (%d elements) [
                %s
                ]
                """.formatted(id, roadsMap.size(), roadsString);
    }
}
