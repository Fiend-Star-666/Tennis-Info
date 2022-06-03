package com.intern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.intern.data.CsvToDb;
import com.intern.tennis.repository.PlayerRepository;

@SpringBootTest
class TennisInfoApplicationTests {

	@Autowired
	PlayerRepository playerRepo;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void read() throws Exception {
		CsvToDb check = new CsvToDb();
		
		playerRepo.saveAll(check.reader());
	}
}
