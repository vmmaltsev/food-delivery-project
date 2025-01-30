package com.maltsevtech.fooddelivery.order;

import com.maltsevtech.fooddelivery.order.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=" +
				"org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration," +
				"org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration," +
				"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})
@Import(TestConfig.class) // Подключаем тестовую конфигурацию
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
