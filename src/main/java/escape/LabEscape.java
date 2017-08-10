package escape;

import escape.exception.NoEscapeException;
import org.springframework.stereotype.Component;

@Component
public class LabEscape {

    private static final char WALL = 'O';
    private static final char FREE = ' ';

//    compilation issue, will fix later
//    private static final char PATH = '•';
    private static final char PATH = '1';

    /**
     * @param labyrinth A labyrinth drawn on a matrix of characters. It's at least 4x4, can be a rectangle or a square.
     *                  Walkable areas are represented with a space character, walls are represented with a big O character.
     *                  The escape point is always on the border (see README)
     * @param startX    Starting row number for the escape. 0 based.
     * @param startY    Starting column number for the escape. 0 based.
     * @return          A char matrix with the same labyrinth and a path drawn from the starting point to the escape
     * @throws          NoEscapeException when no path exists to the outside, from the selected starting point
     */
    public char[][] drawPathForEscape(
            char[][] labyrinth,
            int startX,
            int startY) throws NoEscapeException {

        // continuing
        return new char[][]{};
    }
}
