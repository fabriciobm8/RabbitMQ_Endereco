package com.multiversa.endereco;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaRabbitMQConfig {

  public static final String QUEUE_NAME = "endereco";

  @Bean
  public Queue enderecoQueue() {
    return new Queue(QUEUE_NAME, false);
  }
}
