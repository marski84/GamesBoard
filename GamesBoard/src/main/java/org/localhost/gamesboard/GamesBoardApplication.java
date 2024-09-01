package org.localhost.gamesboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.localhost.gamesboard"})
public class GamesBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesBoardApplication.class, args);
	}

}
