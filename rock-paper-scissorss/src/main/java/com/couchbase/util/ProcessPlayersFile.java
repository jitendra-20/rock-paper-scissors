package com.couchbase.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.couchbase.model.Players;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessPlayersFile {
	private Players players;
	private ObjectMapper mapper = new ObjectMapper();
	
	//Read the jsonFile
	public Players processPlayersFile(File jsonFile) throws JsonParseException, JsonMappingException, IOException{
		players=mapper.readValue(jsonFile, Players.class);
		return players;
		
	}

	
	//This method validate the input
	public boolean validatedPlayersAndItsInput(Players players) {
		
		boolean isInputCorrect=false;
		String [] supportedInput={"rock","paper","scissors"};
		List<String> player1Input;
		List<String> player2Input;
		if (players!=null){
		   
			player1Input=players.getPlayer1();
			player2Input=players.getPlayer2();
			if(player1Input!=null  && player2Input!=null){
				if(!player1Input.isEmpty() && !player2Input.isEmpty()){
					if(player1Input.size()==3 && player2Input.size()==3){
						for(int i=0;i<3;i++){
							if(Arrays.asList(supportedInput).contains(player1Input.get(i).toLowerCase()) && Arrays.asList(supportedInput).contains(player2Input.get(i).toLowerCase())){
								isInputCorrect=true;
							}else{
								isInputCorrect=false;
								return isInputCorrect;
								
							}
						}
						
					}else{
						isInputCorrect=false;
					}
					
				}else{
					isInputCorrect=false;
				}
				
			}else{
				isInputCorrect=false;
			}
			return isInputCorrect;
		}
		return isInputCorrect;
		
	}

}
