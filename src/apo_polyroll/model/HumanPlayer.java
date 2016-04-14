package apo_polyroll.model;

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

    /***
     * Get all playable positions on the othellier for the current player
     * @param othellier the board
     * @return an arrayList of all Positions
     */
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
                allPositions.add(pos);
            }
        }
        
        return allPositions;
    }
}
