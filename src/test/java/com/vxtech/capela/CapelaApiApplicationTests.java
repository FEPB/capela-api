package com.vxtech.capela;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)
@Log4j2
class CapelaApiApplicationTests {

	@Test
	@Order(1)
	void contextLoads() {
	}

}
