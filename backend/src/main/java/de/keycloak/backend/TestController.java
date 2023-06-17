
package de.keycloak.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping("/secured")
    public String test() {
        return "{\"message\": \"This is a secured endpoint!\"}";
    }

}
