package escape;

import escape.exception.NoEscapeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static escape.LabEscape.Motion.*;

@Component
public class LabEscape {

    private static final char FREE = ' ';
    private static final char PATH = '•';

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

        throw new NoEscapeException("Could not find any escape route");

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
     * @param x         The x coordinate for the starting position
     * @param y         The y coordinate for the starting position
     * @return A set of possible move points
     */
    List<LabPoint> findMovePoints(char[][] labyrinth, int x, int y) {

        if (labyrinth == null) {
            return Collections.emptyList();
        }

        List<Motion> motions = Arrays.asList(NORTH, EAST, SOUTH, WEST);

        return motions.stream()
                .filter(motion -> {
                    int xMotion = x + motion.x;
                    return xMotion >= 0 && xMotion < labyrinth.length;
                })
                .filter(motion -> {
                    int yMotion = y + motion.y;
                    return yMotion >= 0 && yMotion < labyrinth[0].length;
                })
                .filter(motion -> labyrinth[x + motion.x][y + motion.y] == FREE)
                .map(motion -> new LabPoint(x + motion.x, y + motion.y))
                .collect(Collectors.toList());
    }

    public static class Motion {

        int x, y;

        static final Motion NORTH = new Motion(-1, 0);
        static final Motion SOUTH = new Motion(1, 0);
        static final Motion EAST = new Motion(0, 1);
        static final Motion WEST = new Motion(0, -1);

        public Motion(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
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
