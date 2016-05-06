/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apo_polyroll.model;

import static apo_polyroll.model.Plateau.Jeton.EMPTY;
import static apo_polyroll.model.Plateau.Jeton.WHITE;
import static apo_polyroll.model.Plateau.PLATEAU_SIZE;
import apo_polyroll.utils.Position;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Darkos
 */
public class Basic2IAPlayer extends IAPlayer {

    /**
     * Give a value to each position
     */
    public static final int VALUES[][]=
    {{1024,64,256,128,128,256,64,1024},
    {64,64,96,96,128,64,64},
    {256,128,128,64,64,128,128,256},
    {128,96,64,64,64,64,96,128},
    {128,96,64,64,64,64,96,128},
    {256,128,128,64,64,128,128,256},
    {64,64,128,96,96,128,64,64},
    {1024,64,256,128,128,256,64,1024}};

    public Basic2IAPlayer() {
        super("Basic2");
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
    
    /***
     * find all spots possible for a position given 
     * @param x 
     * @param y
     * @return a list of all Positions possible for the IA with a value
     */
    private HashMap<Position, Integer> findAllPossibilities(Plateau othellier, int x, int y) {

        HashMap allPositions = new HashMap<Position, Integer>();
        ArrayList<Position> allPositionsUnclean = new ArrayList<Position>();
        
        Position position = new Position(x, y);
        
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 0, -1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 0, 1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, -1, 0));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 1, 0));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 1, -1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, -1, -1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, 1, 1));
        allPositionsUnclean.add(getSpotIfExist(othellier, position, -1, 1));
        
        for(Position pos : allPositionsUnclean) {
            if(pos == null) {
                //do nothing
            } else {
                int value = getSumReversedToken(othellier, pos);
                allPositions.put(pos, value);

            }
        }
        
        return allPositions;
    }
    
    
    /***
    * Sum the value of reversed token if the IA play at a selected position
    * IT'S ONLY WORK FOR IA PLAYER !
    * @param pos the position selected
    * @return a the value of reversed token
    */
    private int getSumReversedToken(Plateau othellier,Position pos) {
        
        int nbReversedToken = 0;
        
        nbReversedToken += sumReversedToken(othellier,pos, 0, -1);
        nbReversedToken += sumReversedToken(othellier,pos, 0, 1);
        nbReversedToken += sumReversedToken(othellier,pos, -1, 0);
        nbReversedToken += sumReversedToken(othellier,pos, 1, 0);
        nbReversedToken += sumReversedToken(othellier,pos, 1, -1);
        nbReversedToken += sumReversedToken(othellier,pos, -1, -1);
        nbReversedToken += sumReversedToken(othellier,pos, 1, 1);
        nbReversedToken += sumReversedToken(othellier,pos, -1, 1);
        
        return nbReversedToken;
    }
    
    /***
     * Sum the value of token reversed IF the IAPlayer play at a selected position for a precise direction
     * @param pos the position selected
     * @param vectorX 
     * @param vectorY
     * @return the value of token reversed
     */
    private int sumReversedToken(Plateau othellier,Position pos, int vectorX, int vectorY) {
        
        //Si il n'y a rien de reversible dans cette direction : retourne 0
        if(! othellier.isReversable(pos, vectorX, vectorY, WHITE)){
            return 0;
        }
        
        int sumReversedToken = 0;
        
        Position actualPos = new Position(pos.x + vectorX, pos.y + vectorY);
        Plateau.Jeton tokenToReverse = othellier.getToken(pos.x + vectorX, pos.y + vectorY);
        
        while((tokenToReverse != null)){
            
            //si la case est vide ou on a croisé un jeton de la même couleur: 
            // -> arète de compter !
            if(tokenToReverse == EMPTY || tokenToReverse == WHITE) {
                break;
            }
            try{
                sumReversedToken+= VALUES [actualPos.x][actualPos.y];
            }catch(ArrayIndexOutOfBoundsException ex){
                
            }            
            actualPos = new Position(actualPos.x + vectorX ,actualPos.y + vectorY);
            tokenToReverse = othellier.getToken(actualPos.x, actualPos.y);
        }
        
        return sumReversedToken;
    }
    
    
}
