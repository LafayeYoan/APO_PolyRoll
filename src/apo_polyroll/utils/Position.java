
package apo_polyroll.utils;

/**
 * Util class for get a position in the othellier
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class Position {
    
    public int x;
    public int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    
}
