import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    // --------------------------------------
    //        Test possibleMoves()
    // --------------------------------------

    @Test
    public void testPossibleMoves_AllOpen() {
        int[][] island = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,2"));
        assertTrue(moveSet.contains("1,0"));
    }

    @Test
    public void testPossibleMoves_AllWater() {
        int[][] island = {
            {2,2,2},
            {2,0,2},
            {2,2,2}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertTrue(moveSet.isEmpty());
    }

    @Test
    public void testPossibleMoves_AllMountains() {
        int[][] island = {
            {3,3,3},
            {3,0,3},
            {3,3,3}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertTrue(moveSet.isEmpty());
    }

    @Test
    public void testPossibleMoves_AllClosed() {
        int[][] island = {
            {3,3,2},
            {2,0,3},
            {3,2,3}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertTrue(moveSet.isEmpty());
    }

    @Test
    public void testPossibleMoves_AtEdgeOneOpen() {
        int[][] island = {
            {0,1,3}
        };
        int[] location = {0,0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1"));
    }

    @Test
    public void testPossibleMoves_TwoOpen() {
        int[][] island = {
            {1,0,1}
        };
        int[] location = {0,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("0,0"));
        assertTrue(moveSet.contains("0,2"));
    }

    @Test
    public void testPossibleMoves_TopBlocked() {
        int[][] island = {
            {1,3,1},
            {1,0,1},
            {1,1,1}
        };

        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
        assertTrue(moveSet.contains("2,1"));
    }

    @Test
    public void testPossibleMoves_BottomBlocked() {
        int[][] island = {
            {1,1,1},
            {1,0,1},
            {1,2,1}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void testPossibleMoves_RightBlocked() {
        int[][] island = {
            {1,1,1},
            {1,0,3},
            {1,1,1}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("2,1"));
    }

    @Test
    public void testPossibleMoves_LeftBlocked() {
        int[][] island = {
            {1,1,1},
            {2,0,1},
            {1,1,1}
        };
        int[] location = {1,1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("1,2"));
        assertTrue(moveSet.contains("1,2"));
    }

    @Test
    public void testPossibleMoves_DiagonalOpen() {
        int[][] island = {
            {0,2},
            {3,1}
        };
        int[] location = {0,0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertTrue(moveSet.isEmpty());
    }

    @Test
    public void testPossibleMoves_onlyStart() {
        int[][] island = {
            {0}
        };
        int[] location = {0,0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertTrue(moveSet.isEmpty());
    }

    // --------------------------------------
    //        More Test reachableArea()
    // --------------------------------------

    @Test
    public void testReachableArea_allReachable() {
        int[][] island = {
            {1,1,0,1},
            {1,1,1,1},
            {1,1,1,1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(12, actual);
    }

    @Test
    public void testReachableArea_onlyStart() {
        int[][] island = {
            {3,1,3,2},
            {2,2,2,2},
            {2,2,0,2},
            {1,2,2,2}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }


    // -----------------------------
    // Copied from matrix-livecode
    // to make testing easier
    // -----------------------------
    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
