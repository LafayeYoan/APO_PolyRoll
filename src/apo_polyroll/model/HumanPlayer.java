package apo_polyroll.model;

import apo_polyroll.model.Plateau.Jeton;
import static apo_polyroll.model.Plateau.PLATEAU_SIZE;
import apo_polyroll.utils.Position;
import java.util.ArrayList;

/**
 * Human Player
 * JETON NOIR
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL 
 */
public class HumanPlayer extends Player {

    public HumanPlayer() {
        super("Joueur", Plateau.Jeton.BLACK);
    }

    @Override
    public ArrayList<Position> getPlayableSpots(Plateau othellier) {
        
        ArrayList<Position> allPositions = new ArrayList<Position>();
        
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                
                //Si le joueur possède un pion sur cette case 
                if((othellier.getToken(i, j)).equals(getToken())) {
                    
                    allPositions.addAll(findAllPossibilities(othellier, i, j)); 
                }
            }
        }
        return allPositions;
    }

    /***
     * find all spots possible for a position given 
     * @param x 
     * @param y
     * @return a list of all Positions possible for the player
     */
    private ArrayList<Position> findAllPossibilities(Plateau othellier, int x, int y) {

        ArrayList<Position> allPositions = new ArrayList<Position>();
        Position position = new Position(x, y);
        
        if(getSpotIfExist(othellier, position, 0, -1) == null) {
            //do nothing
        } else {
            allPositions.add(getSpotIfExist(othellier, position, 0, -1));
        }
        
        return allPositions;
    }
    
    /***
     * Get a playable spot if exist in a precise direction. 
     * @param othellier the board
     * @param initPos the initial position for checking
     * @param index_X vector x
     * @param index_Y vector y
     * @return a position if exist. Null otherwise
     */
    private Position getSpotIfExist(Plateau othellier, Position initPos, int index_X, int index_Y) {
        
        Jeton adjacentToken = othellier.getToken(initPos.x + index_X, initPos.y + index_Y);
        
        if(adjacentToken.equals(getToken().getReverse())) {
        
            while((! adjacentToken.equals(Jeton.EMPTY))) {

                initPos = new Position(initPos.x + index_X, initPos.y + index_Y);
                adjacentToken = othellier.getNextToken(initPos, index_X, index_Y);

                if(adjacentToken == null) {
                    break;
                }
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
