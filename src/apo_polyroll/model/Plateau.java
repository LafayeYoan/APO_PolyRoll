package apo_polyroll.model;

/**
 *
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class Plateau {
    
    public enum Jeton {
        
        BLANC, 
        NOIR,
        EMPTY
        
    }
    
    public static int PLATEAU_SIZE = 8;
    
    private Jeton[][] othellier;

    /***
     * Construct and initialize le plateau
     * Set Tokens to begin the game
     */
    public Plateau() {
        othellier = new Jeton[PLATEAU_SIZE][PLATEAU_SIZE];
        
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                othellier[i][j] = Jeton.EMPTY;
            }
        }
        
        setToken(Jeton.BLANC, 4, 4);
        setToken(Jeton.BLANC, 5, 5);
        setToken(Jeton.NOIR, 5, 4);
        setToken(Jeton.NOIR, 4, 5);
    }
    
    public void setToken(Jeton value, int x, int y) {
        othellier[x][y] = value;
    }
    
    public Jeton getToken(int x, int y) {
        return othellier[x][y];
    }
    
}
