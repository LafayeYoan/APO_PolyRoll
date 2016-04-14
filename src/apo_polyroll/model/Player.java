package apo_polyroll.model;

import apo_polyroll.utils.Position;

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
     * Get a playable spot if exist in a precise direction. 
     * @param othellier the board
     * @param initPos the initial position for checking
     * @param index_X vector x
     * @param index_Y vector y
     * @return a position if exist. Null otherwise
     */
    protected Position getSpotIfExist(Plateau othellier, Position initPos, int index_X, int index_Y) {
        
        Plateau.Jeton adjacentToken = othellier.getToken(initPos.x + index_X, initPos.y + index_Y);
        
        if(adjacentToken != null && adjacentToken.equals(token.getReverse())) {
        
            while(adjacentToken != null && (! adjacentToken.equals(Plateau.Jeton.EMPTY))) {
                
                if(adjacentToken.equals(getToken())){
                    adjacentToken = null;
                    break;
                }
                
                initPos = new Position(initPos.x + index_X, initPos.y + index_Y);                
                adjacentToken = othellier.getNextToken(initPos, index_X, index_Y);
            }
            
            if(adjacentToken == null) {
                //do nothing
            } else {
                //on peut jouer ici :
                return new Position(initPos.x + index_X, initPos.y + index_Y);
            }    
        }
        return null;
    }
}
