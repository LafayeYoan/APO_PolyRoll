
package apo_polyroll.model;

import static apo_polyroll.model.Plateau.PLATEAU_SIZE;
import apo_polyroll.utils.Position;
import java.util.HashMap;

/**
 * Min Max IA
 * Play at the spot get by a min max method
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class MinMaxIAPlayer extends IAPlayer {
    
    //Nombres de positions différentes pour une case donnée. 
    public final int NB_FILS = 8;
    public final int MAX_VALUE = 1000;
    public final int MIN_VALUE = -1000;
    
    public MinMaxIAPlayer() { 
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
    
    private HashMap<Position, Integer> findAllPossibilities(Plateau othellier, int i, int j) {
        
        /*??? Position p1 = othellier.get(target, 0, -1, token);
        reverse(target, 0, 1, token);
        reverse(target, -1, 0, token);
        reverse(target, 1, 0, token);
        reverse(target, 1, -1, token);
        reverse(target, -1, -1, token);
        reverse(target, 1, 1, token);
        reverse(target, -1, 1, token);*/
        return null;
    }
    
    /*fonction MINIMAX( p) est 
     maxime = -MAX_VAL
     pour *) chacune fils = successeur(p) faire
          val_fils = ValeurMINI(fils)
          si val_fils > maxime alors
               maxime = val_fils
               coup = fils 
     fin pour
     retourner coup
Fin


fonction ValeurMINI ( n ) est
     si  Feuille( n )= vrai alors
          retourner f(n) 
     sinon 
          v_min = MAX_VAL
          pour *) chacune fils = successeur(p) faire
               v_fils = ValeurMAX(fils)
               v_min = min (v_min, v_fils) 
          fin pour
     fin si  
     retourner v_min
Fin


fonction ValeurMAX ( n ) est
     si  Feuille( n )= vrai alors
          retourner f(n) 
     sinon 
          v_max = -MAX_VAL
          pour *) chacune fils = successeur(p) faire
               v_fils = ValeurMINI(fils)
               v_max= max(v_max,v_fils)
          fin pour
     fin si 
     retourner v_max
Fin*/

    
           
}