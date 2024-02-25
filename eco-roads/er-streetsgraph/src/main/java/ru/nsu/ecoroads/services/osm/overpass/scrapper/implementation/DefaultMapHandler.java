package ru.nsu.ecoroads.services.osm.overpass.scrapper.implementation;

import de.westnordost.osmapi.map.data.BoundingBox;
import de.westnordost.osmapi.map.data.Node;
import de.westnordost.osmapi.map.data.Relation;
import de.westnordost.osmapi.map.data.Way;
import de.westnordost.osmapi.map.handler.MapDataHandler;
import org.apache.logging.log4j.util.Strings;
import ru.nsu.ecoroads.model.common.base.Coordinates;
import ru.nsu.ecoroads.model.common.road.Road;
import ru.nsu.ecoroads.model.common.road.RoadPoint;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;
import ru.nsu.ecoroads.model.common.road.RoadsGraph;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultMapHandler implements MapDataHandler {

    private static final String NAME_TAG = "name";
    private static final String LANES_TAG = "lanes";
    private static final String HIGHWAY = "highway";
    private static final String MAX_SPEED = "maxspeed";
    private static final String DEFAULT_NAME = "unknown name";
    private static final Long DEFAULT_LANES = 1L;

    private final Map<Long, Node> allNodes = new HashMap<>();
    private final Map<Long, Way> allWays = new HashMap<>();

    private final Converter converter = new Converter();

    private static final Pattern DECIMAL_NUMBER = Pattern.compile("^\\d*\\.?\\d*$");

    public RoadsCovering buildRoadsCovering(UUID roadsCoveringId, String roadsCoveringName) {
        return converter.buildRoadsCovering(roadsCoveringId, roadsCoveringName);
    }

    @Override
    public void handle(Node node) {
        allNodes.put(node.getId(), node);
    }

    @Override
    public void handle(Way way) {
        allWays.put(way.getId(), way);
    }

    @Override
    public void handle(BoundingBox bounds) {
    }

    @Override
    public void handle(Relation relation) {
    }

    private class Converter {

        private RoadsCovering buildRoadsCovering(UUID roadsCoveringId, String roadsCoveringName) {
            Map<Coordinates, UUID> coordinatesUUIDMap = new HashMap<>();
            Map<UUID, Road> roads = allWays.values().stream()
                    .map(way -> convertWay2Road(way, coordinatesUUIDMap))
                    .collect(Collectors.toMap(Road::id, Function.identity()));

            return RoadsCovering.builder()
                    .id(roadsCoveringId)
                    .name(roadsCoveringName)
                    .roadsMap(roads)
                    .roadsGraph(getRoadsGraph(roads.values()))
                    .totalRoadsNumber(roads.values().size())
                    .build();
        }

        private RoadsGraph getRoadsGraph(Collection<Road> roads) {
            RoadsGraph graph = new RoadsGraph();

            for (var road : roads) {
                var startPoint = road.getStartPoint();
                var endPoint = road.getEndPoint();

                graph.connectVertices(startPoint, endPoint, road.getLength(), road);
                graph.connectVertices(endPoint, startPoint, road.getLength(), road);
            }

            return graph;
        }

        private Road convertWay2Road(Way way, Map<Coordinates, UUID> coordinatesUUIDMap) {
            List<RoadPoint> roadPoints = way.getNodeIds().stream()
                    .map(allNodes::get)
                    .map(node -> convertNode2RoadPoint(node, coordinatesUUIDMap))
                    .toList();

            return Road.builder()
                    .id(UUID.randomUUID())
                    .name(getName(way))
                    .roadPoints(roadPoints)
                    .lanes(getLanes(way))
                    .highwayType(getHighwayType(way))
                    .maxSpeed(getMaxSpeed(way))
                    .build();
        }

        private String getName(Way way) {
            return Optional.ofNullable(way.getTags().get(NAME_TAG))
                    .orElse(DEFAULT_NAME);
        }

        private Long getLanes(Way way) {
            return Optional.ofNullable(way.getTags().get(LANES_TAG))
                    .map(Long::valueOf)
                    .orElse(DEFAULT_LANES);
        }

        private String getHighwayType(Way way) {
            return Optional.ofNullable(way.getTags().get(HIGHWAY))
                    .orElse(Strings.EMPTY);
        }

        private Integer getMaxSpeed(Way way) {
            return Optional.ofNullable(way.getTags().get(MAX_SPEED))
                    .filter(number -> DECIMAL_NUMBER.matcher(number).matches())
                    .map(Integer::valueOf)
                    .orElse(null);
        }

        private RoadPoint convertNode2RoadPoint(Node node, Map<Coordinates, UUID> coordinatesUUIDMap) {
            var coordinates = Coordinates.of(node.getPosition().getLatitude(), node.getPosition().getLongitude());
            coordinatesUUIDMap.putIfAbsent(coordinates, UUID.randomUUID());
            return new RoadPoint(coordinatesUUIDMap.get(coordinates), coordinates);
        }

    }
}
