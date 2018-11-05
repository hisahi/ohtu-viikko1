package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Statistics {

    private List<Player> players;

    public Statistics(Reader reader) {
        players = reader.getPlayers();       
    }

    public Player search(String name) {
        for (Player player : players) {
            if (player.getName().contains(name)) {
                return player;
            }
        }

        return null;
    }

    public List<Player> team(String teamName) {
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();
        
        for (Player player : players) {
            if ( player.getTeam().equals(teamName)) {
                playersOfTeam.add(player);
            }
        }
        
        return playersOfTeam;
    }

    public List<Player> topScorers(int howMany) {
        int remaining = howMany;
        Collections.sort(players);
        ArrayList<Player> topScorers = new ArrayList<Player>();
        Iterator<Player> playerIterator = players.iterator();
        
        while (remaining>0) { /* oli remaining>=0 mutta tämä rikkoi kaiken; bugi alkuperäisessä koodissa! */
            topScorers.add( playerIterator.next() );            
            remaining--;
        }
        
        return topScorers;
    }

}
