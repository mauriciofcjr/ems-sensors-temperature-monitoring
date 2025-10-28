package com.mchaves.sensors.temperature.monitoring.infrastructure.rabbitmq;

import static com.mchaves.sensors.temperature.monitoring.infrastructure.rabbitmq.RabbitMQConfig.QUEUE;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mchaves.sensors.temperature.monitoring.api.model.TemperatureLogData;

import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    @RabbitListener(queues = QUEUE)
    public void handle(@Payload TemperatureLogData temperatureLogData) {
        TSID sensorId = temperatureLogData.getSensorId();
        Double temperature = temperatureLogData.getValue();
        log.info("Temperature updated: SensorId {} Temp {}", sensorId, temperature);

    }

}
