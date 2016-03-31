package apo_polyroll.model;

/**
 * Player class 
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class Player {
    
    private String name;
    private Plateau.Jeton token;
    
    public Player(String name, Plateau.Jeton token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plateau.Jeton getToken() {
        return token;
    }

    public void setToken(Plateau.Jeton token) {
        this.token = token;
    }
    
    
    
}
