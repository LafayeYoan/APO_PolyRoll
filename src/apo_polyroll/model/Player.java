package apo_polyroll.model;

import apo_polyroll.utils.Position;
import java.util.ArrayList;

/**
 * Player class 
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public abstract class Player {
    
    private String name;
    private Plateau.Jeton token;
    
    public Player(String name, Plateau.Jeton token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plateau.Jeton getToken() {
        return token;
    }

    public void setToken(Plateau.Jeton token) {
        this.token = token;
    }
    
    /***
     * Get all playable positions on the othellier for the current player
     * @param othellier the board
     * @return an arrayList of all Positions
     */
    public abstract ArrayList<Position> getPlayableSpots(Plateau othellier);
}
