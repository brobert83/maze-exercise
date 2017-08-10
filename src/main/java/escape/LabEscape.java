package escape;

import escape.exception.NoEscapeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class LabEscape {

    private static final char WALL = 'O';
    private static final char FREE = ' ';

    //    compilation issue, will fix later
//    private static final char PATH = '•';
    private static final char PATH = 'X';

    /**
     * @param labyrinth A labyrinth drawn on a matrix of characters. It's at least 4x4, can be a rectangle or a square.
     *                  Walkable areas are represented with a space character, walls are represented with a big O character.
     *                  The escape point is always on the border (see README)
     * @param startX    Starting row number for the escape. 0 based.
     * @param startY    Starting column number for the escape. 0 based.
     * @return A char matrix with the same labyrinth and a path drawn from the starting point to the escape
     * @throws NoEscapeException when no path exists to the outside, from the selected starting point
     */
    public char[][] drawPathForEscape(
            char[][] labyrinth,
            int startX,
            int startY) throws NoEscapeException {

        char[][] newLabyrinth = moveToPoint(labyrinth, startX, startY);

        if (isOnTheBorder(newLabyrinth, startX, startY)) {
            return newLabyrinth;
        }

        List<LabPoint> movePoints = findMovePoints(newLabyrinth, startX, startY);

        for (LabPoint movePoint : movePoints) {

            int x = movePoint.getX();
            int y = movePoint.getY();

            return drawPathForEscape(newLabyrinth, x, y);
        }

        throw new NoEscapeException();

    }

    /**
     * Returns true if the point is on one of the borders
     *
     * @param labyrinth The labyrinth model
     * @param x         The x coordinate
     * @param y         The y coordinate
     * @return True is the point is on the border, false otherwise
     */
    boolean isOnTheBorder(char[][] labyrinth, int x, int y) {

        if (labyrinth == null) {
            return false;
        }

        int xLength = labyrinth.length;
        int yLength = labyrinth[0].length;

        boolean onXBorder = x == 0 || x == xLength - 1;
        boolean onYBorder = y == 0 || y == yLength - 1;

        return onXBorder || onYBorder;
    }

    /**
     * This method should return the labyrinth with the coordinates marked as 'visited'
     *
     * @param labyrinth The labyrinth model
     * @param x         The x coordinate
     * @param y         The y coordinate
     * @return A new structure with the value at position (x,y) masked as 'visited'
     */
    char[][] moveToPoint(char[][] labyrinth, int x, int y) {

        if (labyrinth == null) {
            return null;
        }

        char[][] motion = new char[labyrinth.length][labyrinth[0].length];

        for (int i = 0; i < labyrinth.length; i++) {
            motion[i] = Arrays.copyOf(labyrinth[i], labyrinth[i].length);
        }

        motion[x][y] = PATH;

        return motion;
    }


    /**
     * This method should return all possible move points around the starting position
     * The points are ordered from in a clockwise perspective
     * The north point is the starting position, so the order is (NORTH, EST, SOUTH, WEST)
     *
     * @param labyrinth The labyrinth model
     * @param startX    The x coordinate for the starting position
     * @param startY    The y coordinate for the starting position
     * @return A set of possible move points
     */
    List<LabPoint> findMovePoints(char[][] labyrinth, int startX, int startY) {

        return Collections.emptyList();
    }

    public static class LabPoint {

        private int x, y;

        public LabPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
