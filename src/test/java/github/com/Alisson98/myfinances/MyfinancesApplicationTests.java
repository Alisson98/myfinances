package github.com.Alisson98.myfinances;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class MyfinancesApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
