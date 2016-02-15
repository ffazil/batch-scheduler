package com.lt.x.batch.airport;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Read airport data.
 *
 * @author ffazil
 * @since 12/02/16
 */
@Slf4j
@Component
@StepScope
public class AirportReader implements ItemReader<Airport>{

    private List<Airport> airports;
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Read airport data from json file.
     *
     * @param stepExecution
     * @throws IOException
     */
    @BeforeStep
    public void readData(final StepExecution stepExecution) throws IOException {
        String home=System.getProperty("user.home");
        String filePath=home+"/airports.json";
        log.info(filePath);
        File file=new File(filePath);
        airports= new LinkedList<>(Arrays.asList(objectMapper.readValue(file,Airport[].class)));
    }

    /**
     * Return one airport at a time.
     *
     * @return
     * @throws Exception
     * @throws UnexpectedInputException
     * @throws ParseException
     * @throws NonTransientResourceException
     */
    @Override
    public Airport read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!airports.isEmpty())
            return airports.remove(0);
        return null;
    }
}
