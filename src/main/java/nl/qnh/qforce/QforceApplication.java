package nl.qnh.qforce;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Main method to run the REST-service on a tomcat server on port: 8080
 *
 * @author Jordi Jaspers
 */
@SpringBootApplication
public class QforceApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	/**
	 * Main method to run the service
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(QforceApplication.class, args);
	}
}
