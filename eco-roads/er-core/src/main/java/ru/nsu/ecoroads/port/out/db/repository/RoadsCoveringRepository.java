package ru.nsu.ecoroads.port.out.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ecoroads.port.out.db.entity.RoadsCoveringEntity;

import java.util.UUID;

public interface RoadsCoveringRepository extends JpaRepository<RoadsCoveringEntity, UUID> {
}
