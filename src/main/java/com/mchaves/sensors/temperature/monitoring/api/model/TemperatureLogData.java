package com.mchaves.sensors.temperature.monitoring.api.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import io.hypersistence.tsid.TSID;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperatureLogData {
    private UUID id;
    private TSID sensorId;
    private OffsetDateTime registeredAt;
    private Double value;
}
