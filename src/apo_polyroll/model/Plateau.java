package apo_polyroll.model;

import static apo_polyroll.model.Plateau.Jeton.EMPTY;
import static apo_polyroll.model.Plateau.Jeton.WHITE;
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
    
    /***
     * Check if the position is not out of bounds for the othellier and return the position selected
     * @param x x index
     * @param y y index
     * @return null if the position if out of bounds. The token set up for the position otherwise.
     */
    public Jeton getToken(int x, int y) {
        if(isOutOfBounds(x, y)) {
            return null;
        }
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
        return getToken(begin.x + index_X, begin.y + index_Y);
    }
    
    /***
     * Add a selected token on the selected position. Then reverse all tokens affected.
     * @param target the position selected
     * @param token the token to add
     */
    public void addAndReverse(Position target, Jeton token) {
        
        setToken(token, target.x, target.y);
        
        //reverse in all directions
        reverse(target, 0, -1, token);
        reverse(target, 0, 1, token);
        reverse(target, -1, 0, token);
        reverse(target, 1, 0, token);
        reverse(target, 1, -1, token);
        reverse(target, -1, -1, token);
        reverse(target, 1, 1, token);
        reverse(target, -1, 1, token);
    }
    
    /***
     * Reverse all enemy's token for a precise direction. 
     * @param init the position where the player played
     * @param vectorX 
     * @param vectorY 
     * @param tokenPlayer the player token
     */
    private void reverse(Position init, int vectorX, int vectorY, Jeton tokenPlayer){
        
        if(!isReversable(init, vectorX, vectorY, tokenPlayer)){
            return;
        }
        
        Position actualPos = new Position(init.x + vectorX, init.y + vectorY);
        Jeton tokenToReverse = getToken(init.x + vectorX, init.y + vectorY);
        
        while((tokenToReverse != null)){
            
            //si la case est vide ou on a croisé un jeton de la même couleur: 
            // -> aréter de retourner !
            if(tokenToReverse == EMPTY || tokenToReverse == tokenPlayer) {
                break;
            }
            
            setToken(tokenPlayer,actualPos.x,actualPos.y);
            actualPos = new Position(actualPos.x + vectorX ,actualPos.y + vectorY);
            tokenToReverse = getToken(actualPos.x, actualPos.y);
            
        }
    }
    
    /***
     * Check if a token can be reversed in a precise direction. 
     * This test is required to avoid reversing if enemy's token is not really 
     * "surround" by token of the player. 
     * @param init the initial position
     * @param vectorX
     * @param vectorY
     * @param tokenPlayer the token of the player
     * @return true if we can reverse all enemy's token for that direction.
     * false if enemy's token are not really "surround". 
     */
    public boolean isReversable(Position init, int vectorX, int vectorY, Jeton tokenPlayer){
        Position actualPos = new Position(init.x + vectorX, init.y + vectorY);
        Jeton tokenToReverse = getToken(init.x + vectorX, init.y + vectorY);
        
        boolean reversable = false;
        
        while((tokenToReverse != null)){
            
            //si la case est vide : il n'y a rien à retourner !
            if(tokenToReverse == EMPTY) {
                break;
            }

            if(tokenToReverse == tokenPlayer) {
                reversable = true;
                break;
            }
            actualPos = new Position(actualPos.x + vectorX ,actualPos.y + vectorY);
            tokenToReverse = getToken(actualPos.x, actualPos.y);
        }
        
        return reversable;
    }
    
    /***
     * Check if the othellier is Out of Bounds for a selected index
     * @param x 
     * @param y
     * @return true if the othellier is out of bounds for the position. False otherwise.
     */
    public boolean isOutOfBounds(int x, int y) {
        return x > PLATEAU_SIZE - 1
                || y > PLATEAU_SIZE - 1
                || x < 0
                || y < 0;
    }
    
    /***
    * Count the number of reversed token if the IA play at a selected position
    * IT'S ONLY WORK FOR IA PLAYER !
    * @param pos the position selected
    * @return a the number of reversed token
    */
    public int getNumberReversedToken(Position pos) {
        
        int nbReversedToken = 0;
        
        nbReversedToken += countReversedToken(pos, 0, -1);
        nbReversedToken += countReversedToken(pos, 0, 1);
        nbReversedToken += countReversedToken(pos, -1, 0);
        nbReversedToken += countReversedToken(pos, 1, 0);
        nbReversedToken += countReversedToken(pos, 1, -1);
        nbReversedToken += countReversedToken(pos, -1, -1);
        nbReversedToken += countReversedToken(pos, 1, 1);
        nbReversedToken += countReversedToken(pos, -1, 1);
        
        return nbReversedToken;
    }
    
    /***
     * Evaluate the number of token reversed IF the IAPlayer play at a selected position for a precise direction
     * @param pos the position selected
     * @param vectorX 
     * @param vectorY
     * @return the number of token reversed
     */
    private int countReversedToken(Position pos, int vectorX, int vectorY) {
        
        //Si il n'y a rien de reversible dans cette direction : retourne 0
        if(! isReversable(pos, vectorX, vectorY, WHITE)){
            return 0;
        }
        
        int nbReversedToken = 0;
        
        Position actualPos = new Position(pos.x + vectorX, pos.y + vectorY);
        Jeton tokenToReverse = getToken(pos.x + vectorX, pos.y + vectorY);
        
        while((tokenToReverse != null)){
            
            //si la case est vide ou on a croisé un jeton de la même couleur: 
            // -> arète de compter !
            if(tokenToReverse == EMPTY || tokenToReverse == WHITE) {
                break;
            }
            
            nbReversedToken++;
            actualPos = new Position(actualPos.x + vectorX ,actualPos.y + vectorY);
            tokenToReverse = getToken(actualPos.x, actualPos.y);
        }
        
        return nbReversedToken;
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
    
    /***
     * Get Number of token in the othellier for a selected color
     * @param token the color to evaluate
     * @return number of token in the othellier for the selected color
     */
    public int getNumberOfToken(Jeton token){
        int nOfTok = 0;
        for(int i = 0; i < PLATEAU_SIZE; i++) {
            for(int j = 0; j < PLATEAU_SIZE; j++) {
                if(othellier[i][j].equals(token)) {
                    nOfTok++;
                }
            }
        }
        return nOfTok;
    }
    
}
