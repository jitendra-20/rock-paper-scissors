package com.servicenow.game.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This is the model class for the given json file.
 */
public class Players {

    @JsonProperty("Player1")
    private List<String> player1 = null;
    @JsonProperty("Player2")
    private List<String> player2 = null;

    public List<String> getPlayer1() {
        return player1;
    }

    public void setPlayer1(List<String> player1) {
        this.player1 = player1;
    }

    public List<String> getPlayer2() {
        return player2;
    }

    public void setPlayer2(List<String> player2) {
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return "Players [player1=" + player1 + ", player2=" + player2 + "]";
    }


}
