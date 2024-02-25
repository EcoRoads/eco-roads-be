package ru.nsu.ecoroads.services.osm.overpass.scrapper.implementation;

import de.westnordost.osmapi.overpass.OverpassMapDataApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ecoroads.model.common.base.Coordinates;
import ru.nsu.ecoroads.model.common.configuration.RoadsCoveringBuildingConfig;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;
import ru.nsu.ecoroads.services.osm.overpass.scrapper.contracts.RoadsCoveringProvider;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoadsCoveringProviderImpl implements RoadsCoveringProvider {

    private final OverpassMapDataApi overpassMapDataApi;
    private final OverpassRequestBuilder overpassRequestBuilder = new OverpassRequestBuilder();

    @Override
    public RoadsCovering provideRoadsAroundPoint(UUID roadsCoveringId, RoadsCoveringBuildingConfig config) {
        var mapHandler = new DefaultMapHandler();
        overpassMapDataApi.queryElements(
                overpassRequestBuilder.buildRequest(config.center(), config.radiusAround()), mapHandler
        );
        return mapHandler.buildRoadsCovering(roadsCoveringId, config.name());
    }

    private static class OverpassRequestBuilder {

        private String buildRequest(Coordinates centerCoordinates, double radius) {
            return """
                    <query type="way">
                        <around lat="%f" lon="%f" radius="%f"/>
                      <has-kv k="highway" regv="secondary|primary|tertiary|trunk|residential|unclassified"/>
                    </query>
                    <union>
                      <item/>
                      <recurse type="down"/>
                    </union>
                    <print/>
                    """.formatted(centerCoordinates.latitude(), centerCoordinates.longitude(), radius);
        }

    }

}
