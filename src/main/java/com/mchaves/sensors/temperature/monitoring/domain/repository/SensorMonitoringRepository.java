package com.mchaves.sensors.temperature.monitoring.domain.repository;

import com.mchaves.sensors.temperature.monitoring.domain.model.SensorId;
import com.mchaves.sensors.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
}
