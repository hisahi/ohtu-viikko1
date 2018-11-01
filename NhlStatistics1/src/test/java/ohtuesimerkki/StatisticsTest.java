package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    static Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    Statistics stats;
    public StatisticsTest() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void playerSearchByName() {
        assertEquals(4, stats.search("Semenko").getGoals());
        assertNull(stats.search("null"));
    }

    @Test
    public void playerSearchByTeam() {
        List<Player> playersInTeam = stats.team("EDM");
        
        assertEquals(3, playersInTeam.size());
        assertEquals(12 + 53 + 89, playersInTeam.stream().mapToInt(p -> p.getAssists()).sum());
    }

    @Test
    public void topScorers() {
        List<Player> topScorers = stats.topScorers(2);
        
        assertEquals(2, topScorers.size());
        assertEquals(124, topScorers.get(0).getPoints());
        assertEquals(99, topScorers.get(1).getPoints());
    }
}
