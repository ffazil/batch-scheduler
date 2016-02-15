package com.lt.x.batch.airport;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Definition of batch job to import airports from json file and publish to RabbitMQ.
 *
 * @author ffazil
 * @since 15/02/16
 */
@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableBatchProcessing
public class AirportBatchConfiguration {

    @NonNull
    private AirportReader airportReader;
    @NonNull
    private AirportProcessor airportProcessor;
    @NonNull
    private AirportWriter airportWriter;

    public AirportBatchConfiguration(){

    }

    /**
     * Execute airport import job. Job consists of multiple steps. Sets the incrementer and status listener.
     *
     * @param jobs
     * @param s1
     * @param listener
     * @return
     */
    @Bean
    public Job importAirportJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
        return jobs.get("importAirportJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    /**
     * Each step consists of a read, process and write(Similar to ETL). Sets the airport reader, processor and writer.
     *
     * @param stepBuilderFactory
     * @param airportReader
     * @param airportWriter
     * @param airportProcessor
     * @return
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, AirportReader airportReader,
                      AirportWriter airportWriter, AirportProcessor airportProcessor) {
        return stepBuilderFactory.get("step1")
                .<Airport, Airport> chunk(1000)
                .reader(airportReader)
                .processor(airportProcessor)
                .writer(airportWriter)
                .build();
    }
}
