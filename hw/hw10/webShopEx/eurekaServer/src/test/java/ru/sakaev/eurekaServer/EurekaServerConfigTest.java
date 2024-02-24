package ru.sakaev.eurekaServer;

import com.netflix.eureka.EurekaServerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
		"spring.cloud.config.enabled=false", // Отключаем загрузку конфигурации для теста
		"eureka.client.register-with-eureka=false",
		"eureka.client.fetch-registry=false"
})
public class EurekaServerConfigTest {

	@Autowired
	private EurekaServerConfig eurekaServerConfig;

	@Test
	public void contextLoads() {
		assertThat(eurekaServerConfig).isNotNull();
	}
}
