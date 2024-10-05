package org.localhost.gamesboard.Game.model.exampleFacade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestFacade {

    @Bean
    public TestService testService() {
        return new TestService();
    }
}
