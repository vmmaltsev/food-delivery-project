package com.maltsevtech.fooddelivery.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration")
class NotificationServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
