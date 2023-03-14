package javaclub.modulith.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class ModulithIT {

	@Test
	void verify_modules() {
		ApplicationModules.of(ShopApplication.class).forEach(System.out::println);
//		 ApplicationModules.of(ShopApplication.class).verify();
	}

}
