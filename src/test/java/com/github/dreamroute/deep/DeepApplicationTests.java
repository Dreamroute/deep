package com.github.dreamroute.deep;

import com.github.dreamroute.deep.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeepApplicationTests {

	@Autowired
	private User user;

	@Test
	void contextLoads() {
		System.err.println(user);
	}

}
