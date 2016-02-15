package com.lt.x.batch.airport;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Writes airport data
 *
 * @author ffazil
 * @since 15/02/16
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AirportWriter implements ItemWriter<Airport>{
    @NonNull
    private RabbitTemplate airportTemplate;

    /**
     * Write to RabbitMQ one airport at a time.
     *
     * @param items
     * @throws Exception
     */
    @Override
    public void write(List<? extends Airport> items) throws Exception {
        items.forEach(i -> {
            airportTemplate.convertAndSend(i);
        });
    }
}
