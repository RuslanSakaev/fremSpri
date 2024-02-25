package ru.sakaev.apiGateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiGatewayApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private RouteDefinitionLocator routeDefinitionLocator;

	@Test
	public void testRoutes() {
		RouteDefinition route1 = new RouteDefinition();
		route1.setId("route1");
		route1.setUri(URI.create("http://localhost:8888"));

		when(routeDefinitionLocator.getRouteDefinitions())
				.thenReturn(Flux.fromIterable(List.of(route1)));

		webClient.get().uri("/route1")
				.exchange()
				.expectStatus().isOk();
	}

}
