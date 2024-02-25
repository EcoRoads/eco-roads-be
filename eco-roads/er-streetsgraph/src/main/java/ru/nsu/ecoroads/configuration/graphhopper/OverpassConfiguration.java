package ru.nsu.ecoroads.configuration.graphhopper;

import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.overpass.OverpassMapDataApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OverpassConfiguration {

    @Bean
    public OverpassMapDataApi overpassMapDataApi() {
        return new OverpassMapDataApi(
                new OsmConnection("https://overpass-api.de/api/", "my user agent")
        );
    }

}
