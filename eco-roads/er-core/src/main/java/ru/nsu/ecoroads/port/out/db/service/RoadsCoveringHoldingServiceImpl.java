package ru.nsu.ecoroads.port.out.db.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.ecoroads.exceptions.ServiceException;
import ru.nsu.ecoroads.mapper.roads_covering.RoadsCoveringMapper;
import ru.nsu.ecoroads.model.common.road.RoadsCovering;
import ru.nsu.ecoroads.port.out.db.repository.RoadsCoveringRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoadsCoveringHoldingServiceImpl implements RoadsCoveringHoldingService {

    private final RoadsCoveringMapper roadsCoveringMapper;
    private final RoadsCoveringRepository roadsCoveringRepository;

    @Override
    public void saveRoadsCovering(RoadsCovering roadsCovering) {
        log.info("Roads covering saving started!");
        roadsCoveringRepository.save(roadsCoveringMapper.mapM2E(roadsCovering));
        log.info("Roads covering saved successfully!");
    }

    @Override
    public RoadsCovering getRoadsCoveringById(UUID id) throws ServiceException {
        return roadsCoveringRepository.findById(id)
                .map(roadsCoveringMapper::mapE2M)
                .orElse(null);
    }

}
