package com.intern.tennis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.intern.tennis.primary.Player;


public interface PlayerRepository extends JpaRepository<Player, Integer>{
	Player findByRankOfPlayer(Integer rankOfPlayer);
	List<Player> findAllByName(String name);
	List<Player> findAllByNameIgnoreCase(String name);

}
