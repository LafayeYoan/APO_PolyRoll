package apo_polyroll.model;

import apo_polyroll.utils.Position;
import java.util.HashMap;

/**
 * IA Player
 * JETON BLANC
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public abstract class IAPlayer extends Player {
    public String levelName;

    public IAPlayer(String levelName) {
        super("Ordinateur", Plateau.Jeton.WHITE);
        this.levelName = levelName;
    }
    
    /***
     * Final position chosen by the IA 
     * @param othellier the board
     * @return the position to put a token
     */
    public Position getChoice(Plateau othellier) {
        HashMap<Position, Integer> playableSpots = getPlayableSpots(othellier);
        HashMap.Entry<Position, Integer> finalposition = null;

        for (HashMap.Entry<Position, Integer> entry : playableSpots.entrySet()) {
            
            if (finalposition == null 
                    || finalposition.getKey() == null 
                    || entry.getValue().compareTo(finalposition.getValue()) > 0) {
                
                finalposition = entry;
            }
        }
        
        //NB : si, après la boucle, on a récupérer aucun choix : 
        // -> le jeu est fini car l'IA ne peut plus jouer ! 
        if(finalposition == null) {
            System.err.println("JE SUIS NULL WALA");
            return null;
        }
        
        return finalposition.getKey();
    }

    /***
     * Get all playable positions on the othellier for the IA. All position have an associate value. 
     * The bigger is the value associate, the better is the move for the IA
     * @param othellier the board
     * @return an Map of all positions with a value
     */
    public abstract HashMap<Position, Integer> getPlayableSpots(Plateau othellier);
}
