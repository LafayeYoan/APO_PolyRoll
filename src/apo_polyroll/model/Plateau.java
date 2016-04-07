package apo_polyroll.model;

/**
 * Plateau class
 * Othellier for the game
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class Plateau {
    
    public enum Jeton {
        
        BLACK, 
        WHITE,
        EMPTY
    }
    
    public static int PLATEAU_SIZE = 8;
    
    private Jeton[][] othellier;

    /***
     * Construct and initialize the "physic" othellier
     * Set Tokens to begin the game
     */
    public Plateau() {
        othellier = new Jeton[PLATEAU_SIZE][PLATEAU_SIZE];
        
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                othellier[i][j] = Jeton.EMPTY;
            }
        }
        
        setToken(Jeton.WHITE, 3, 3);
        setToken(Jeton.WHITE, 4, 4);
        setToken(Jeton.BLACK, 4, 3);
        setToken(Jeton.BLACK, 3, 4);
    }
    
    public void setToken(Jeton value, int x, int y) {
        othellier[x][y] = value;
    }
    
    public Jeton getToken(int x, int y) {
        return othellier[x][y];
    }
    
    /***
     * Check if the othellier is full (i.e if the game is ending)
     * @return true if the othellier is full. False otherwise.
     */
    public boolean isFull() {
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                if(othellier[i][j].equals(Jeton.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
