package escape;

import escape.exception.NoEscapeException;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LabEscapeTest {

    TypeTransformer typeTransformer = new TypeTransformer();

    LabEscape labEscape = new LabEscape();

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

    @Test
    public void drawPathForEscape_found() throws NoEscapeException {

        int startX = 1, startY = 1;

        char[][] escapeRoute = labEscape.drawPathForEscape(labyrinth, startX, startY);

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

    @Test
    public void findMovePoints(){

        List<LabEscape.LabPoint> movePoints = labEscape.findMovePoints(labyrinth, 1, 1);

        assertThat(movePoints).hasSize(1);
        assertThat(movePoints.get(0)).isEqualToComparingFieldByField(new LabEscape.LabPoint(2,1));

        movePoints = labEscape.findMovePoints(labyrinth, 2, 2);

        assertThat(movePoints).hasSize(3);
        assertThat(movePoints.get(0)).isEqualToComparingFieldByField(new LabEscape.LabPoint(2,3));
        assertThat(movePoints.get(1)).isEqualToComparingFieldByField(new LabEscape.LabPoint(3,2));
        assertThat(movePoints.get(2)).isEqualToComparingFieldByField(new LabEscape.LabPoint(2,1));
    }

}