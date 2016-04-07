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

    @Override
    public ArrayList<Position> getPlayableSpots(Plateau othellier) {
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                //Si 
                if((othellier.getToken(i, j)).equals(this.getToken())) {
                    
                }
            }
        }
        return null;
    }
    
}
