package com.klef.fsad.sdp;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBackendApplication {

	public static void main(String[] args) {
		configureDatabaseUrl();
		SpringApplication.run(SpringBootBackendApplication.class, args);
	}

	private static void configureDatabaseUrl() {
		String databaseUrl = System.getenv("DATABASE_URL");
		if (databaseUrl == null || databaseUrl.isBlank() || System.getProperty("spring.datasource.url") != null) {
			return;
		}

		if (databaseUrl.startsWith("jdbc:")) {
			System.setProperty("spring.datasource.url", databaseUrl);
			return;
		}

		URI uri = URI.create(databaseUrl);
		String[] credentials = uri.getUserInfo() == null ? new String[0] : uri.getUserInfo().split(":", 2);
		String username = credentials.length > 0 ? decode(credentials[0]) : "";
		String password = credentials.length > 1 ? decode(credentials[1]) : "";
		int port = uri.getPort() == -1 ? 5432 : uri.getPort();
		String database = uri.getPath() == null ? "" : uri.getPath().replaceFirst("^/", "");

		System.setProperty("spring.datasource.url", "jdbc:postgresql://" + uri.getHost() + ":" + port + "/" + database);
		System.setProperty("spring.datasource.username", username);
		System.setProperty("spring.datasource.password", password);
	}

	private static String decode(String value) {
		return URLDecoder.decode(value, StandardCharsets.UTF_8);
	}

}
