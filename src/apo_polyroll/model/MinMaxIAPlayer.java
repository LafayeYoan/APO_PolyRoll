
package apo_polyroll.model;

import apo_polyroll.utils.Position;
import java.util.HashMap;

/**
 * Min Max IA
 * Play at the spot get by a min max method
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class MinMaxIAPlayer extends IAPlayer {
    
    public MinMaxIAPlayer() { 
        super();
    }

    @Override
    public HashMap<Position, Integer> getPlayableSpots(Plateau othellier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
