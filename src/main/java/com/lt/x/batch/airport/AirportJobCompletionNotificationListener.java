package com.lt.x.batch.airport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Notification listener for airport job status updates.
 *
 * @author ffazil
 * @since 12/02/16
 */
@Slf4j
@Component
public class AirportJobCompletionNotificationListener extends JobExecutionListenerSupport{

    public AirportJobCompletionNotificationListener() {

    }

    /**
     * Invoked after airport import jon.
     *
     * @param jobExecution
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

        }
    }
}
