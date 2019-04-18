package LogicSection;

import java.util.Comparator;

/**
 * A comparator used to rank Players.
 */

public class PlayerComparator implements Comparator<Player> {


    /**
     * Sorts Players based on their score and in the case that their score would be identical it sort them alphabeticly.
     * @param o1 the first Player.
     * @param o2 the second Player.
     * @return the Player whose score is higher.
     */
    @Override
    public int compare(Player o1, Player o2) {

        if(o1.score==o2.score){
        return o1.name.compareTo(o2.name);
        }
        else if(o1.score>o2.score){
            return -1;
        }else if (o2.score>o1.score){
            return 1;
        }
        return Double.compare(o1.score,o2.score);
    }

   }

