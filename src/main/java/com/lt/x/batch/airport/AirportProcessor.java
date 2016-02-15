package com.lt.x.batch.airport;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Process airport data.
 *
 * @author ffazil
 * @since 15/02/16
 */
@Component
public class AirportProcessor implements ItemProcessor<Airport, Airport>{
    /**
     * Implement transformation logic here. No real transformation happens in the below scenario.
     *
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public Airport process(Airport item) throws Exception {
        return item;
    }
}
