package com.intern;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.intern.data.CsvToDb;
import com.intern.tennis.primary.Player;
import com.intern.tennis.repository.PlayerRepository;

@SpringBootTest
class TennisInfoApplicationTests {

	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	CsvToDb csvToDb;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void read() throws Exception {
		
		playerRepo.saveAll(csvToDb.reader());
	}
	
	@Test
	void date() throws Exception {
		Player player= new Player();
		player.setDateOfBirth(playerRepo.getById(1).getDateOfBirth());
		System.out.println(player.getDateOfBirth());
	}
}
