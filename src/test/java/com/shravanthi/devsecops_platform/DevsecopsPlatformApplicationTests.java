package com.shravanthi.devsecops_platform;

import com.shravanthi.devsecops_platform.controller.HealthController;
import com.shravanthi.devsecops_platform.controller.HelloController;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DevsecopsPlatformApplicationTests {

    @Test
    void helloEndpointShouldReturnMessage() {
        HelloController controller = new HelloController();

        String response = controller.hello();

        assertThat(response).isEqualTo("Hello from DevSecOps Platform!");
    }

    @Test
    void healthEndpointShouldReturnUpStatus() {
        HealthController controller = new HealthController();

        Map<String, String> response = controller.health();

        assertThat(response)
                .containsEntry("status", "UP")
                .containsEntry("application", "devsecops-platform");
    }
}