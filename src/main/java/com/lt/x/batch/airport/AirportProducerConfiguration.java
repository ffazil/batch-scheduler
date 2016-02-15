package com.lt.x.batch.airport;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * A Producer publishes data to RabbitMQ. Configuration of essential RabbitMQ artifacts like exchange and queue.
 *
 * @author ffazil
 * @since 15/02/16
 */
@Configuration
public class AirportProducerConfiguration {
    /**
     * Caching connection factory to connect to RabbitMQ.
     *
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("127.0.0.1");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        //cachingConnectionFactory.setConnectionCacheSize(100);
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        cachingConnectionFactory.setChannelCacheSize(8);
        return cachingConnectionFactory;
    }

    /**
     * Definition of airport queue.
     *
     * @return
     */
    @Bean
    public Queue airportQueue(){
        return new Queue("airport.queue", false, false, false);
    }

    /**
     * Definition of airport exchange.
     *
     * @return
     */
    @Bean
    public DirectExchange airportExchange(){
        return new DirectExchange("airport.exchange", false, false);
    }

    /**
     * Definition of binding a queue to an exchange.
     *
     * @param airportExchange
     * @param airportQueue
     * @return
     */
    @Bean
    public Binding airportyBinding(DirectExchange airportExchange, Queue airportQueue){
        return BindingBuilder.bind(airportQueue())
                .to(airportExchange)
                .with("airport.key");
    }

    @Bean
    @Primary
    public RabbitTemplate airportTemplate() {
        RabbitTemplate r = new RabbitTemplate(connectionFactory());
        r.setExchange(airportExchange().getName());
        r.setChannelTransacted(true);
        r.setRoutingKey("airport.key");
        return r;
    }


}
