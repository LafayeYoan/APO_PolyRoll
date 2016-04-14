
package apo_polyroll.model;

import static apo_polyroll.model.Plateau.PLATEAU_SIZE;
import apo_polyroll.utils.Position;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Basic IA PLAYER
 * Play at the spot where most of tokens can be reversed
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class BasicIAPlayer extends IAPlayer {
    
    public BasicIAPlayer() {
        super();
    }

    @Override
    public HashMap<Position, Integer> getPlayableSpots(Plateau othellier) {
        
        HashMap allPositions = new HashMap<Position, Integer>();
        
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                
                //Si l'IA possède un pion sur cette case 
                if((othellier.getToken(i, j)).equals(getToken())) {
                    
                    allPositions.putAll(findAllPossibilities(othellier, i, j)); 
                }
            }
        }
        return allPositions;
    }
    
    /***
     * find all spots possible for a position given 
     * @param x 
     * @param y
     * @return a list of all Positions possible for the IA with a value
     */
    private HashMap<Position, Integer> findAllPossibilities(Plateau othellier, int x, int y) {

        HashMap allPositions = new HashMap<Position, Integer>();
        ArrayList<Position> allPositionsUnclean = new ArrayList<Position>();
        
        Position position = new Position(x, y);
        
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 0, -1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 0, 1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, -1, 0));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 1, 0));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 1, -1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, -1, -1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 1, 1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, -1, 1));
        
        for(Position pos : allPositionsUnclean) {
            if(pos == null) {
                //do nothing
            } else {
                int value = othellier.getNumberReversedToken(pos);
                allPositions.put(pos, value);
            }
        }
        
        return allPositions;
    }
}
