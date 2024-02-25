package ru.nsu.ecoroads.port.in.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ecoroads.exceptions.ServiceException;
import ru.nsu.ecoroads.port.in.controller.api.RoadsCoveringCreateRequest;
import ru.nsu.ecoroads.port.in.controller.api.RoadsCoveringCreateResponse;
import ru.nsu.ecoroads.port.in.controller.api.RoadsCoveringFetchResponse;
import ru.nsu.ecoroads.port.out.StreetsGraphCommunicationService;
import ru.nsu.ecoroads.port.out.db.service.RoadsCoveringHoldingService;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/roads-covering")
public class RoadsCoveringAdminController {

    private final RoadsCoveringHoldingService roadsCoveringHoldingService;
    private final StreetsGraphCommunicationService streetsGraphCommunicationService;

    @GetMapping("/fetch/id/{id}")
    public RoadsCoveringFetchResponse fetchRoadsCoveringById(@PathVariable UUID id) throws ServiceException {
        return new RoadsCoveringFetchResponse(roadsCoveringHoldingService.getRoadsCoveringById(id));
    }

    @PostMapping("/create")
    public RoadsCoveringCreateResponse createRoadsCovering(@RequestBody RoadsCoveringCreateRequest request)
            throws ServiceException {
        var roadsCoveringId = UUID.randomUUID();
        streetsGraphCommunicationService.shareConfigurationForRoadCoveringBuilding(roadsCoveringId, request.config());
        return new RoadsCoveringCreateResponse(roadsCoveringId);
    }

}
