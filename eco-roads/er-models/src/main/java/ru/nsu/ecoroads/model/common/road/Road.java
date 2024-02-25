package ru.nsu.ecoroads.model.common.road;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public record Road(UUID id, String name, List<RoadPoint> roadPoints, Long lanes, String highwayType, Integer maxSpeed) {

    @Override
    public String toString() {
        var roadPointsString = roadPoints.stream()
                .map(RoadPoint::toString)
                .collect(Collectors.joining("\n"));
        return """
                Road ID: %s
                Name: %s
                Road Points: (%d elements) [
                %s
                ]
                """.formatted(id, name, roadPoints.size(), roadPointsString);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public double getLength() {
        double totalLength = 0;
        for (int i = 1; i < roadPoints.size(); i++) {
            totalLength += CalculationUtils.getDistanceBetweenCoordinatesInMeters(
                    roadPoints.get(i - 1).coordinates(),
                    roadPoints.get(i).coordinates()
            );
        }
        return totalLength;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public RoadPoint getStartPoint() {
        return CollectionUtils.isEmpty(roadPoints) ? null : roadPoints.get(0);
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public RoadPoint getEndPoint() {
        return CollectionUtils.isEmpty(roadPoints) ? null : roadPoints.get(roadPoints.size() - 1);
    }
}
