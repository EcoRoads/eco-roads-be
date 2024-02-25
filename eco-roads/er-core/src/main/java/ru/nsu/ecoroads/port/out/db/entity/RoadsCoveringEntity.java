package ru.nsu.ecoroads.port.out.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.nsu.ecoroads.model.common.road.Road;
import ru.nsu.ecoroads.model.common.road.RoadsGraph;

import java.util.Map;
import java.util.UUID;

@Entity
public class RoadsCoveringEntity {

    @Id
    public UUID id;

    @Column
    public String name;

    @JdbcTypeCode(SqlTypes.JSON)
    public Map<UUID, Road> roadsMap;

    @JdbcTypeCode(SqlTypes.JSON)
    public RoadsGraph roadsGraph;

    @Column
    public Integer totalRoadsNumber;

}
