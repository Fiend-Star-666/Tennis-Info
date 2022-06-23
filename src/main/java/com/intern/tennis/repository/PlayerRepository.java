package com.intern.tennis.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intern.tennis.primary.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	Player findByRankOfPlayer(Integer rankOfPlayer);
	List<Player> findAllByName(String str);
	List<Player> findAllByNameIgnoreCase(String name);
	Page<Player> findByNameIgnoreCaseContaining(String string, Pageable pageable);
	List<Player> findByNameIgnoreCaseContaining(String string, Sort sort);

}
