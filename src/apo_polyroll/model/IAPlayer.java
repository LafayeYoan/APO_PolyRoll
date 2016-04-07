package apo_polyroll.model;

import apo_polyroll.utils.Position;
import java.util.ArrayList;

/**
 * IA Player
 * JETON BLANC
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class IAPlayer extends Player {

    public IAPlayer() {
        super("Ordinateur", Plateau.Jeton.WHITE);
    }
    
    public Position getChoice(Plateau othelier){
        return null;
    }

    @Override
    public ArrayList<Position> getPlayableSpots(Plateau othellier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
