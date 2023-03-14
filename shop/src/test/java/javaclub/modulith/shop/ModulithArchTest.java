package javaclub.modulith.shop;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModulithArchTest {

    ApplicationModules modules = ApplicationModules.of(ShopApplication.class);

    @Test
    void verifyConnections() {
        modules.verify();
    }

}
