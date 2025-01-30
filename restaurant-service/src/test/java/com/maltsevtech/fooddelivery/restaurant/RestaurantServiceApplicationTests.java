package com.maltsevtech.fooddelivery.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootTest(classes = RestaurantServiceApplicationTests.TestConfig.class)
class RestaurantServiceApplicationTests {

	@Test
	void contextLoads() {
		// Smoke test: проверяем, что контекст загружается без сервисов и базы
	}

	@SpringBootApplication(exclude = {
			org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
			org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class,
			org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration.class
	})
	@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\.maltsevtech\\.fooddelivery\\.restaurant\\..*"))
	static class TestConfig {
		// Пустая конфигурация, исключающая все сервисы и компоненты приложения
	}
}
