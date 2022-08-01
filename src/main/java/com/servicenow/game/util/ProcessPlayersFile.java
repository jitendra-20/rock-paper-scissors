package com.servicenow.game.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servicenow.game.model.Players;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * This class process the josn file and populates the Player's object.
 */
public class ProcessPlayersFile {
    private Players players;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * This method process the json input stream
     *
     * @param jsonData
     * @return players object
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Players processPlayersFile(InputStream jsonData) throws JsonParseException, JsonMappingException, IOException {
        players = mapper.readValue(jsonData, Players.class);
        return players;

    }


    /**
     * This method validate the players input
     *
     * @param players
     * @return ture| false
     */
    public boolean validatedPlayersAndItsInput(Players players) {

        boolean isInputCorrect = false;
        String[] supportedInput = {"rock", "paper", "scissors"};
        List<String> player1Input;
        List<String> player2Input;
        if (players != null) {
            player1Input = players.getPlayer1();
            player2Input = players.getPlayer2();
            if (player1Input != null && !player1Input.isEmpty() && player2Input != null && !player2Input.isEmpty()) {
                if (player1Input.size() == player2Input.size()) {
                    for (int i = 0; i < player1Input.size(); i++) {
                        if (Arrays.asList(supportedInput).contains(player1Input.get(i).toLowerCase()) &&
                                Arrays.asList(supportedInput).contains(player2Input.get(i).toLowerCase())) {
                            isInputCorrect = true;
                        } else {
                            isInputCorrect = false;
                            return isInputCorrect;
                        }
                    }
                } else {
                    isInputCorrect = false;
                }
            } else {
                isInputCorrect = false;
            }
            return isInputCorrect;
        }
        return isInputCorrect;

    }

}
