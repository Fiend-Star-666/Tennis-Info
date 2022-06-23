package com.intern.tennis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intern.tennis.primary.Player;
import com.intern.tennis.repository.PlayerRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000") 
public class PlayerController {
	@Autowired
	private PlayerRepository playerRepo;
	
	@CrossOrigin
	@GetMapping("/viewPlayers/all")
	public List<Player> listAllPlayers() {
		List<Player> players = playerRepo.findAll();
		return players;
	}
	
	
	@GetMapping("/playerpage")
	public Page<Player> findPages(Pageable page){
		return playerRepo.findAll(page);
	}
	
	@CrossOrigin
	@GetMapping("/players")
	public ResponseEntity<Map<String, Object>> getAllPlayersPage(
		@RequestParam(required = false) String string,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "3") int size,
		//@RequestParam(required = false) int size,
		@RequestParam(defaultValue = "id,asc") String[] sort){
			try {
				List<Order> orders =  new ArrayList<>();
				if(sort[0].contains(",")) {
					for( String sortOrder : sort) {
						String[] sortsplit = sortOrder.split(",");
						Sort.Direction dir = sortsplit[1].contains("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
						Order order = new Order(dir, sortsplit[0]);
						orders.add(order);
					}
				}
				else {
					Sort.Direction dir = sort[1].contains("desc")?Sort.Direction.DESC:Sort.Direction.ASC;
					Order order = new Order(dir, sort[0]);
					orders.add(order);
				}
				
				List<Player> players = new ArrayList<>();
				Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
				
				Page<Player> pagePlayers;
				
				if(string == null) {
					pagePlayers = playerRepo.findAll(pagingSort);
				}
				else {
					pagePlayers = playerRepo.findByNameIgnoreCaseContaining(string, pagingSort);
				}
				players=pagePlayers.getContent();
				
				if(players.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				
				Map<String, Object> response = new HashMap<>();
				
				response.put("players", players);
				response.put("currentPage", pagePlayers.getNumber());
				response.put("totalItems", pagePlayers.getTotalElements());
				response.put("totalPages", pagePlayers.getTotalPages());
				return new ResponseEntity<>(response,HttpStatus.OK);
				
				
			} catch(Exception e) {
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

	//method to convert a list to a page
	private Page<Player> listToPage(List<Player> resultPlayers, Pageable pageable) {
		System.out.println("ListToPagePageable");
	    int leftIndex = pageable.getPageNumber() * pageable.getPageSize();
	    int rightIndex = Math.min(leftIndex + pageable.getPageSize(), resultPlayers.size());

	    List<Player> subList = resultPlayers.subList(leftIndex, rightIndex);

	    
	    return new PageImpl<Player>(subList, pageable, resultPlayers.size());
	};
	
	@CrossOrigin
	@GetMapping("/searchName/{str}")
	public Page<Player> listofPlayersByName(@PathVariable String str, Pageable pageable) {
		System.out.println("string:  "+str);
		List<Player> result= new ArrayList<>();
			Page<Player> playersPages = playerRepo.findAll(pageable);
			List<Player> players = new ArrayList<>();
			//List<Player> players = playerRepo.findAll();
			
			players.addAll(playersPages.getContent());
			while (playersPages.hasNext()) {
		        Page<Player> nextPageOfPlayers = playerRepo.findAll(playersPages.nextPageable());
		        players.addAll(nextPageOfPlayers.getContent());

		        // update the page reference to the current page
		        playersPages = nextPageOfPlayers;
		    }
			
		for (Player player : players) {
			if(player.getName().toLowerCase().contains(str.toLowerCase())) {
				result.add(player);
			}
		}
		return listToPage(result,pageable);
	}
	
	@CrossOrigin
	@GetMapping("/searchRank/{rank}")
	public List<Player> listofPlayersByName(@PathVariable int rank) {
		List<Player> result = new ArrayList<>();
		result.add(playerRepo.findByRankOfPlayer(rank));
		return result;
	}

}


//date: new SimpleDateFormat("yyyy-MM-dd").parse((String)payload.get("taskDeadline"))

//https://stackoverflow.com/questions/2388115/get-locale-short-date-format-using-javascript
// https://www.google.com/search?q=get+browser+locale+date+format+react+js&sxsrf=ALiCzsZw1TqAffBtY6kebjb6IyBt4BI1hQ%3A1654125901824&ei=TfWXYu_tMfvC3LUP_sKCqA0&oq=get+browser+locale+date+format+react&gs_lcp=Cgdnd3Mtd2l6EAMYATIFCCEQoAEyBQghEKABOgcIIxCwAxAnOgcIABBHELADOgYIABAeEBY6BwghEAoQoAFKBAhBGABKBAhGGABQ_QJYoAxg7BRoAXABeACAAcIBiAHxB5IBAzAuNpgBAKABAcgBCcABAQ&sclient=gws-wiz

//use jackson for proper json functioning


/*
 * Failed Try
@CrossOrigin
@GetMapping("/search/{str}")
public Page<Player> listofPlayersByName(@PathVariable String str, Pageable pageable) {
	List<Player> result = new ArrayList<>();	
	List<Player> players = playerRepo.findAll();
		for (Player player : players) {
			if(player.getName().toLowerCase().contains(str.toLowerCase())) {
				result.add(player);
			}
		}
System.out.println(pageable.getPageNumber());
	//	List<Player> finalResult= result.subList(pageable.getPageNumber()*pageable.getPageSize(), (pageable.getPageNumber()+1)*pageable.getPageSize());
	return new PageImpl<Player>(result,pageable, pageable.getOffset());
}
*/