package escape;

import escape.exception.NoEscapeException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LabEscapeTest {

    TypeTransformer typeTransformer = new TypeTransformer();

    LabEscape labEscape = new LabEscape();

    @Test
    public void drawPathForEscape_found() throws NoEscapeException {

        char[][] expectedRoute = typeTransformer
                .toCharMatrix("OOOOOOOOOO\n" +
                        "\tO1111O111O\n" +
                        "\tO1OO1O1O1O\n" +
                        "\tO1 O1O1O1O\n" +
                        "\tO OO111O11\n" +
                        "\tO OOOOOOOO\n" +
                        "\tO        O\n" +
                        "\tOOOOOOOOOO");

        char[][] labyrinth = typeTransformer
                .toCharMatrix("OOOOOOOOOO\n" +
                        "\tO    O   O\n" +
                        "\tO OO O O O\n" +
                        "\tO  O O O O\n" +
                        "\tO OO   O  \n" +
                        "\tO OOOOOOOO\n" +
                        "\tO        O\n" +
                        "\tOOOOOOOOOO");

        int startX = 3, startY = 1;

        char[][] escapeRoute = labEscape.drawPathForEscape(labyrinth, startX, startY);

        assertThat(escapeRoute).isEqualTo(expectedRoute);
    }

}