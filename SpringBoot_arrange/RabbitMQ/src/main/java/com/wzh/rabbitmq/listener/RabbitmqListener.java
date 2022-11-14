package com.wzh.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangzouhuax
 */
@Component
public class RabbitmqListener {

	@RabbitListener(queues = "boot_queue")
	public void listenerQueue(Message Message) {
		System.out.println(Message);
	}
}
