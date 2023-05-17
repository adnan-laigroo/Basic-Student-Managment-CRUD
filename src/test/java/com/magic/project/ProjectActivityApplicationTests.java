package com.magic.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProjectActivityApplication.class)
class ProjectActivityApplicationTests {

	@Test
	void contextLoads() {
		String[] args = {};
		ProjectActivityApplication.main(args);
	}

}
