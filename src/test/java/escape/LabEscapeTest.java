package escape;

import escape.exception.NoEscapeException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class LabEscapeTest {

    TypeTransformer typeTransformer = new TypeTransformer();

    @Spy LabEscape labEscapeSpy;
    LabEscape labEscape;

    char[][] expectedRoute = typeTransformer
            .toCharMatrix(
                    "OOOO\n" +
                            "OXO \n" +
                            "OXXX\n" +
                            "OO O\n" +
                            "OOOO");

    char[][] labyrinth = typeTransformer
            .toCharMatrix(
                    "OOOO\n" +
                            "O O \n" +
                            "O   \n" +
                            "OO O\n" +
                            "OOOO");

    @Before
    public void setUp() throws Exception {
        labEscapeSpy = spy(LabEscape.class);
        labEscape = new LabEscape();
    }

    @Test
    public void drawPathForEscape_found() throws NoEscapeException {

        int startX = 1, startY = 1;

        char[][] step1 = doStep(labyrinth, startX, startY);
        char[][] step2 = doStep(step1, 2, 1);
        char[][] step3 = doStep(step2, 2, 2);
        char[][] step4a = doStep(step3, 2, 3);
        char[][] step4b = doStep(step3, 3, 2);

        when(labEscapeSpy.moveToPoint(any(char[][].class), eq(startX), eq(startY))).thenReturn(step1);
        when(labEscapeSpy.moveToPoint(any(char[][].class), eq(2), eq(1))).thenReturn(step2);
        when(labEscapeSpy.moveToPoint(any(char[][].class), eq(2), eq(2))).thenReturn(step3);
        when(labEscapeSpy.moveToPoint(any(char[][].class), eq(2), eq(3))).thenReturn(step4a);
        when(labEscapeSpy.moveToPoint(any(char[][].class), eq(3), eq(2))).thenReturn(step4b);

        when(labEscapeSpy.isOnTheBorder(any(char[][].class), eq(2), eq(3))).thenReturn(true);

        when(labEscapeSpy.findMovePoints(any(char[][].class), eq(1), eq(1))).thenReturn(asList(new LabEscape.LabPoint(2, 1)));
        when(labEscapeSpy.findMovePoints(any(char[][].class), eq(2), eq(1))).thenReturn(asList(new LabEscape.LabPoint(2, 2)));
        when(labEscapeSpy.findMovePoints(any(char[][].class), eq(2), eq(2))).thenReturn(asList(new LabEscape.LabPoint(2, 3), new LabEscape.LabPoint(3, 2)));

        char[][] escapeRoute = labEscapeSpy.drawPathForEscape(labyrinth, startX, startY);

        assertThat(typeTransformer.toMultilineString(escapeRoute))
                .isEqualTo(typeTransformer.toMultilineString(expectedRoute));
    }

    char[][] doStep(char[][] labyrinth, int x, int y) {

        char[][] step = new char[labyrinth.length][labyrinth[0].length];

        for (int i = 0; i < labyrinth.length; i++) {
            step[i] = Arrays.copyOf(labyrinth[i], labyrinth[i].length);
        }

        step[x][y] = 'X';

        return step;
    }

    @Test
    public void isOnTheBorder() {

        for (int x = 0; x <= labyrinth.length; x++) {
            for (int y = 0; y <= labyrinth[0].length; y++) {
                boolean onTheBorder = labEscape.isOnTheBorder(labyrinth, x, y);

                if (x == 0 || y == 0 || x == 4 || y == 3) {
                    assertThat(onTheBorder).as("Cell at [%d,%d] should be an on the border", x, y).isTrue();
                } else {
                    assertThat(onTheBorder).as("Cell at [%d,%d] should NOT be an on the border", x, y).isFalse();
                }
            }
        }

    }

}