package com.lididimi.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"smtp_username=dummyUsername",
		"smtp_password=dummyPassword",
})
class RestaurantManagementSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
