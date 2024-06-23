package com.zencatify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class WebConfig {

	@Bean
	OpenAPI openApiInformation() {
		Server localServer = new Server().url("http://localhost:8082").description("Localhost Server URL");

		Contact contact = new Contact().email("contact@zencatify.com").name("ZenCatify inc");

		Info info = new Info().contact(contact).description("ZenCatify SpringBoot App").title("ZenCatify - ")
				.version("V1.0.0")
				.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.txt"));
		return new OpenAPI().info(info).addServersItem(localServer);
	}
}
