package com.mchaves.sensors.temperature.monitoring.api.model;

import lombok.Builder;
import lombok.Data;

@Data
public class SensorAlertInput {

    private Double maxTemperature;
    private Double minTemperature;
}
