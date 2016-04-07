package apo_polyroll.model;

import apo_polyroll.utils.Position;

/**
 * Plateau class
 * Othellier for the game
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class Plateau {
    
    public enum Jeton {
        
        BLACK, 
        WHITE,
        EMPTY;

        /***
         * Get the reverse color of the token
         * @return WHITE if the token is BLACK, BLACK if it is WHITE. EMPTY otherwise.
         */
        Jeton getReverse() {
            if(this.equals(BLACK)) {
                return WHITE;
            }
            if(this.equals(WHITE)) {
                return BLACK;
            }
            return EMPTY;
        }
    }
    
    public static int PLATEAU_SIZE = 8;
    
    public Jeton[][] othellier;

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
     * Return the token in a special direction from an initial position. 
     * @param begin The initial position
     * @param index_X vector x
     * @param index_Y vector y
     * @return the token found in this direction
     */
    public Jeton getNextToken(Position begin, int index_X, int index_Y) {
        if(begin.y + index_Y < 0 || begin.x + index_X < 0) {
            return null;
        }
        return othellier[begin.x + index_X][begin.y + index_Y];
    }
    
    public void addAndReverse(Position target, Jeton token) {
        //todo
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
