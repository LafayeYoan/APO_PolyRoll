package apo_polyroll.model;

import apo_polyroll.utils.Position;

/**
 * IA Player
 * JETON BLANC
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public abstract class IAPlayer extends Player {

    public IAPlayer() {
        super("Ordinateur", Plateau.Jeton.WHITE);
    }
    
    public abstract Position getChoice(Plateau othellier);

}
