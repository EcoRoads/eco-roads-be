package ru.nsu.ecoroads.services.osm.overpass.scrapper.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.overpass.OverpassMapDataApi;
import org.junit.jupiter.api.Test;
import ru.nsu.ecoroads.model.common.base.Coordinates;
import ru.nsu.ecoroads.model.common.configuration.RoadsCoveringBuildingConfig;
import ru.nsu.ecoroads.services.osm.overpass.scrapper.contracts.RoadsCoveringProvider;

import java.util.UUID;

class RoadsCoveringProviderImplTest {

    private final OverpassMapDataApi overpassMapDataApi = new OverpassMapDataApi(
            new OsmConnection("https://overpass-api.de/api/", "my user agent")
    );

    private final RoadsCoveringProvider roadsCoveringProvider = new RoadsCoveringProviderImpl(
            overpassMapDataApi
    );

    @Test
    void test() {
        var roadsCovering = roadsCoveringProvider.provideRoadsAroundPoint(
                UUID.randomUUID(),
                new RoadsCoveringBuildingConfig(
                        "name", new Coordinates(55.751244, 37.618423), 500.0
                ));

        try {
            System.out.println(new ObjectMapper().writeValueAsString(roadsCovering));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}