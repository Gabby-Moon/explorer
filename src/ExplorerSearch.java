import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too
        int[] start = startingLocation(island);
        boolean[][] visited = new boolean[island.length][island[0].length];
        return reachableArea(island, start, visited);
    }
    public static int reachableArea(int[][] island, int[] current, boolean[][] visited) {
        int currR = current[0];
        int currC = current[1];
        int count = 0;

        if(visited[currR][currC]) return 0;

        visited[currR][currC] = true;
        count = 1;
        List<int[]> moves = possibleMoves(island, current);
        for(int[] move : moves) {
            count += reachableArea(island, move, visited);
        }
        return count;
    }

    public static int[] startingLocation(int[][] island) {
        for(int r = 0; r < island.length; r++) {
            for(int c = 0; c < island[r].length; c++) {
                if(island[r][c] == 0) {
                    int[] location = new int[]{r, c};
                    return location;
                }
            }
        }

        throw new IllegalArgumentException("No start present");
    }

    public static List<int[]> possibleMoves(int[][] island, int[] current) {
        List<int[]> moves = new ArrayList<>();

        int currR = current[0];
        int currC = current[1];

        // Up
        int newR = currR - 1;
        int newC = currC;
        if(newR >= 0 && (island[newR][newC] != 2 && island[newR][newC] != 3)) {
            moves.add(new int[]{newR, newC});
        }

        // Down
        newR = currR + 1;
        newC = currC;
        if(newR < island.length && (island[newR][newC] != 2 && island[newR][newC] != 3)) {
            moves.add(new int[]{newR, newC});
        }

        // Left
        newR = currR;
        newC = currC - 1;
        if(newC >= 0 && (island[newR][newC] != 2 && island[newR][newC] != 3)) {
            moves.add(new int[]{newR, newC});
        }

        // Right
        newR = currR;
        newC = currC + 1;
        if(newC < island[newR].length && (island[newR][newC] != 2 && island[newR][newC] != 3)) {
            moves.add(new int[]{newR, newC});
        }

        return moves;
    }
}
