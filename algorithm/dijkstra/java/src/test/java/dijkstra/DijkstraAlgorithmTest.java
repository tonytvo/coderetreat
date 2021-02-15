package java.dijkstra;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tony on 1/15/2016.
 */
public class DijkstraAlgorithmTest {

    // TODO
    // Any live cell with fewer than two live neighbours dies, as if caused by under-population
    // Any live cell with two or three live neighbours lives on to the next generation.
    // Any live cell with more than three live neighbours dies, as if by over-population.
    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

    @Test
    public void sampleTest() throws Exception {
        Assert.assertTrue("First dummy test", false);
    }
}
