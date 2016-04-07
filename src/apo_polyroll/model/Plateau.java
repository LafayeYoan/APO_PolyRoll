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
     * Return the token above an initial position. 
     * @param begin The initial position
     * @return the token found in this direction
     */
    public Jeton getNextToken_Up(Position begin) {
        if(begin.y-- < 0) {
            return null;
        }
        return othellier[begin.x][begin.y--];
    }
    
    /***
     * Return the position of the next token from an initial position and going down. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_Down(Position begin, Jeton token) {
        return null;
    }
    
    /***
     * Return the position of the next token from an initial position and going left. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_Left(Position begin, Jeton token) {
        return null;
    }
    
    /***
     * Return the position of the next token from an initial position and going right. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_Right(Position begin, Jeton token) {
        return null;
    }
    
    /***
     * Return the position of the next token from an initial position and going diagonal Up Right. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_DiagonalUpRight(Position begin, Jeton token) {
        return null;
    }
    
    /***
     * Return the position of the next token from an initial position and going diagonal Up Left. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_DiagonalUpLeft(Position begin, Jeton token) {
        return null;
    }
    
    /***
     * Return the position of the next token from an initial position and going diagonal Down Right. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_DiagonalDownRight(Position begin, Jeton token) {
        return null;
    }
    
    /***
     * Return the position of the next token from an initial position and going diagonal Down Left. 
     * @param begin The initial position
     * @param token The token we looking for
     * @return the first position found in this direction of null if not found
     */
    public Position getNextToken_DiagonalDownLeft(Position begin, Jeton token) {
        return null;
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
