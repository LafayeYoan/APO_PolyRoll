package apo_polyroll.model;

import static apo_polyroll.model.Plateau.PLATEAU_SIZE;
import apo_polyroll.utils.Position;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * IA Player
 * JETON BLANC
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class IAPlayer extends Player {

    public IAPlayer() {
        super("Ordinateur", Plateau.Jeton.WHITE);
    }
    
    /***
     * Final position chosen by the IA 
     * @param othellier the board
     * @return the position to put a token
     */
    public Position getChoice(Plateau othellier){
        
        HashMap<Position, Integer> playableSpots = getPlayableSpots(othellier);
        HashMap.Entry<Position, Integer> finalposition = null;

        for (HashMap.Entry<Position, Integer> entry : playableSpots.entrySet()) {
            
            if (finalposition == null || entry.getValue().compareTo(finalposition.getValue()) > 0) {
                finalposition = entry;
            }
        }
        
        //NB : si, après la boucle, on a récupérer aucun choix : 
        // -> le jeu est fini car l'IA ne peut plus jouer !        
        return finalposition.getKey();
    }

    /***
     * Get all playable positions on the othellier for the IA. All position have an associate value. 
     * The bigger is the value associate, the better is the move for the IA
     * @param othellier the board
     * @return an Map of all positions with a value
     */
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
