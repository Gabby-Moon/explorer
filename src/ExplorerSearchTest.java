import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases

    // --------------------------------------
    //        Test startingLocation()
    // --------------------------------------
    @Test
    public void testStartingLocation_missing() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,1},
            {1,1,1,2,1,1},
        };

        assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.startingLocation(island);
        });
    }

    @Test
    public void testStartingLocation_startInTopLeft() {
        int[][] island = {
            {0,1,1,1,2,2},
            {1,1,3,3,2,2},
            {2,1,1,2,2,2},
            {2,3,3,1,1,1},
            {2,2,2,2,2,2}
        };

        int[] expected = {0, 0};
        assertArrayEquals(expected, ExplorerSearch.startingLocation(island));
    }

    @Test
    public void testStartingLocation_largeStartInMiddle() {
        int[][] island = {
            {2,1,1,1,1,1,1,1,1,2,2},
            {2,2,1,1,1,1,1,1,2,2,2},
            {2,2,2,2,1,1,1,1,1,3,3},
            {2,2,2,1,1,1,1,1,3,3,3},
            {2,1,1,1,1,0,2,3,2,1,1},
            {2,2,1,1,1,1,1,1,1,2,2},
            {2,1,1,1,1,3,3,3,3,2,2},
            {1,1,1,1,3,3,3,3,2,2,2},
            {3,3,3,3,3,3,3,2,2,2,2}
        };

        int[] expected = {4, 5};
        assertArrayEquals(expected, ExplorerSearch.startingLocation(island));
    }

    @Test
    public void testStartingLocation_startAtEnd() {
        int[][] island = {
            {1,1,2,2,2,1,1},
            {1,1,1,1,2,2,2},
            {3,3,1,1,1,1,1},
            {3,3,3,1,1,1,1},
            {3,3,3,2,2,1,0}
        };

        int[] expected = {4, 6};
        assertArrayEquals(expected, ExplorerSearch.startingLocation(island));
    }

    @Test
    public void testStartingLocation_smallStartTopRight() {
        int[][] island = {
            {2,3,1,0},
            {1,1,1,2},
            {1,2,1,1},
        };

        int[] expected = {0, 3};
        assertArrayEquals(expected, ExplorerSearch.startingLocation(island));
    }
}
