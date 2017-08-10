package escape;

import escape.exception.NoEscapeException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

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

        when(labEscapeSpy.isOnTheBorder(any(char[][].class), eq(2), eq(3))).thenReturn(true);

        when(labEscapeSpy.findMovePoints(any(char[][].class), eq(1), eq(1))).thenReturn(asList(new LabEscape.LabPoint(2, 1)));
        when(labEscapeSpy.findMovePoints(any(char[][].class), eq(2), eq(1))).thenReturn(asList(new LabEscape.LabPoint(2, 2)));
        when(labEscapeSpy.findMovePoints(any(char[][].class), eq(2), eq(2))).thenReturn(asList(new LabEscape.LabPoint(2, 3), new LabEscape.LabPoint(3, 2)));

        char[][] escapeRoute = labEscapeSpy.drawPathForEscape(labyrinth, startX, startY);

        assertThat(typeTransformer.toMultilineString(escapeRoute))
                .isEqualTo(typeTransformer.toMultilineString(expectedRoute));
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

    @Test
    public void moveToPoint(){

        char[][] moved = labEscape.moveToPoint(labyrinth, 1, 1);

        final char[][] expected = typeTransformer
                .toCharMatrix(
                        "OOOO\n" +
                                "OXO \n" +
                                "O   \n" +
                                "OO O\n" +
                                "OOOO");

        assertThat(typeTransformer.toMultilineString(moved)).isEqualTo(typeTransformer.toMultilineString(expected));
    }

}