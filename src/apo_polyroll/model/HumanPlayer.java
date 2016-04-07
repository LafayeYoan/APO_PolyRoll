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
                    //alors :
                    // si il y a un token enemy adjacent (vérif les 8 directions) : 
                    Position playerPosition = new Position(i, j);
                    Position enemyPosition = othellier.getNextToken_Up(playerPosition, getToken().getReverse());
                    if(enemyPosition == null) {
                        //no enemy token found
                    } else {
                        //enemy token found : trouver la prochaine case vide dans la même direction
                        Position playablePosition = othellier.getNextToken_Up(enemyPosition, Jeton.EMPTY);
                        if(playablePosition == null) {
                            //do nothing
                        } else {
                            allPositions.add(playablePosition);
                        }
                    }                    
                }
            }
        }
        return allPositions;
    }
    
}
