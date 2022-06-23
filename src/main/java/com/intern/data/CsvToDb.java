package com.intern.data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.intern.tennis.primary.Player;
@Service
public class CsvToDb {
	
	List<Player> players= new ArrayList<>();
	
		public List<Player> reader() throws Exception{ 
			

			File dataList = new File("tennis_Data.csv");			
			
				Scanner inptData = new Scanner(dataList);
				inptData.hasNextLine();
				while(inptData.hasNextLine()){
					String fullData=inptData.nextLine();
					String[] splitData = fullData.split(",");
					
					String srank=splitData[0].trim();
					
					Integer rank = Integer.parseInt(srank);
					
					String playerId = (splitData[1].trim());
					
					String name = splitData[2].trim()+" "+splitData[3].trim();
					
					String dateOfBirth = splitData[4].trim();
					
					Integer points = Integer.parseInt(splitData[5].trim());
									
						Player newplayer = new Player();
								
						newplayer.setRankOfPlayer(rank);
							
						newplayer.setName(name);
							
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
							
						Date dateDOB = formatter.parse(dateOfBirth);
						
						System.out.println(dateDOB.getTime());
						
						newplayer.setDateOfBirth(dateDOB);
						
						newplayer.setPoints(points);
							
						players.add(newplayer);
						
					}
				inptData.close();
				
				return players;

			}

}