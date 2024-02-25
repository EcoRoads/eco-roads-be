package ru.nsu.ecoroads.model.common.road;

import java.util.*;

public record RoadsGraph(Map<UUID, RoadPoint> roadPoints, Map<UUID, List<Edge>> adjMatrix) {

    public RoadsGraph() {
        this(new HashMap<>(), new HashMap<>());
    }

    public void connectVertices(RoadPoint pointTo, RoadPoint pointFrom, double distance, Road road) {
        roadPoints.putIfAbsent(pointTo.id(), pointTo);
        roadPoints.putIfAbsent(pointFrom.id(), pointFrom);

        adjMatrix.putIfAbsent(pointFrom.id(), new LinkedList<>());
        adjMatrix.get(pointFrom.id()).add(Edge.of(pointTo.id(), distance, road.id()));
    }

    private record Edge(UUID toRoadPointId, double distance, UUID roadId) {
        private static Edge of(UUID toRoadPointId, double distance, UUID roadId) {
            return new Edge(toRoadPointId, distance, roadId);
        }
    }
}
