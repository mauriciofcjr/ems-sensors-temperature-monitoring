package com.mchaves.sensors.temperature.monitoring.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;

import com.mchaves.sensors.temperature.monitoring.api.model.TemperatureLogData;
import com.mchaves.sensors.temperature.monitoring.domain.model.SensorId;
import com.mchaves.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import com.mchaves.sensors.temperature.monitoring.domain.model.TemperatureLog;
import com.mchaves.sensors.temperature.monitoring.domain.model.TemperatureLogId;
import com.mchaves.sensors.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import com.mchaves.sensors.temperature.monitoring.domain.repository.TemperatureLogRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemperatureMonitoringService {

    private final SensorMonitoringRepository sensorMonitoringRepository;
    private final TemperatureLogRepository temperatureLogRepository;


    @Transactional
    public void proccessTemperatureReading(TemperatureLogData temperatureLogData) {
        
        sensorMonitoringRepository.findById(new SensorId(temperatureLogData.getSensorId()))
            .ifPresentOrElse(                
            sensor -> handleSensorMonitoring(temperatureLogData, sensor), 
            () -> logIgnoredTemperature(temperatureLogData));
            
    }


    private void handleSensorMonitoring(TemperatureLogData temperatureLogData, SensorMonitoring sensor) {
        if (sensor.isEnable()) {
            sensor.setLastTemperature(temperatureLogData.getValue());
            sensor.setUpdatedAt(OffsetDateTime.now());
            sensorMonitoringRepository.save(sensor);

            TemperatureLog temperatureLog = TemperatureLog.builder()
                .id(new TemperatureLogId(temperatureLogData.getId()))
                .value(temperatureLogData.getValue())
                .registeredAt(temperatureLogData.getRegisteredAt())
                .sensorId(new SensorId(temperatureLogData.getSensorId()))
                .build();

            temperatureLogRepository.save(temperatureLog);
             log.info("Temperature Updated: SensorId {} Temp {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
        } else {
            logIgnoredTemperature(temperatureLogData);
        }
    }

    private void logIgnoredTemperature(TemperatureLogData temperatureLogData) {
        log.info("Temperature Igonred: SensorId {} Temp {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
        
    }


    

}
