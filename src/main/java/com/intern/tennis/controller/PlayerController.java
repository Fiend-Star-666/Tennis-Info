package com.intern.tennis.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.tennis.primary.Player;
import com.intern.tennis.repository.PlayerRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerController {
	@Autowired
	private PlayerRepository playerRepo;
	
	@GetMapping("/viewPlayers/all")
	public List<Player> listAllPlayers() {
		List<Player> players = playerRepo.findAll();
		
		return players;
	}
	
	@PostMapping("/search")
	public List<Player> listofPlayersByName(String name) {
		List<Player> players = playerRepo.findAllByName(name);
		List<Player> playersCI = playerRepo.findAllByNameIgnoreCase(name);

			//isko aur dekhna padega
		
		//use regular expression for substring search but for normal use the case insensitive one
		return players;
	}
	
	
	//date ka dekhna hai ki browser locale se kaise dekhega
	//https://stackoverflow.com/questions/2388115/get-locale-short-date-format-using-javascript
	// https://www.google.com/search?q=get+browser+locale+date+format+react+js&sxsrf=ALiCzsZw1TqAffBtY6kebjb6IyBt4BI1hQ%3A1654125901824&ei=TfWXYu_tMfvC3LUP_sKCqA0&oq=get+browser+locale+date+format+react&gs_lcp=Cgdnd3Mtd2l6EAMYATIFCCEQoAEyBQghEKABOgcIIxCwAxAnOgcIABBHELADOgYIABAeEBY6BwghEAoQoAFKBAhBGABKBAhGGABQ_QJYoAxg7BRoAXABeACAAcIBiAHxB5IBAzAuNpgBAKABAcgBCcABAQ&sclient=gws-wiz
}
