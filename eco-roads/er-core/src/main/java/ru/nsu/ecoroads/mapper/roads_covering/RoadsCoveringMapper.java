package ru.nsu.ecoroads.mapper.roads_covering;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;
import ru.nsu.ecoroads.port.out.db.entity.RoadsCoveringEntity;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RoadsCoveringMapper {

    RoadsCoveringEntity mapM2E(RoadsCovering roadsCovering);

    RoadsCovering mapE2M(RoadsCoveringEntity roadsCoveringEntity);

}
