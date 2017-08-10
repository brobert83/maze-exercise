package integration;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EscapeIntegrationTest {

    @Test
    public void drawPathForEscape() throws UnirestException {

        String labyrinth
                = "OOOOOOOOOO\n" +
                  "O    O   O\n" +
                  "O OO O O O\n" +
                  "O  O O O O\n" +
                  "O OO   O  \n" +
                  "O OOOOOOOO\n" +
                  "O        O\n" +
                  "OOOOOOOOOO";
        int startX = 3;
        int startY = 1;

        String expectedEscapeRoute
                = "OOOOOOOOOO\n" +
                  "OXXXXOXXXO\n" +
                  "OXOOXOXOXO\n" +
                  "OX OXOXOXO\n" +
                  "O OOXXXOXX\n" +
                  "O OOOOOOOO\n" +
                  "O        O\n" +
                  "OOOOOOOOOO";

        String escapeRoute = Unirest.post("http://localhost:8080/escapeRoute")
                .field("labyrinth", labyrinth)
                .field("startX", startX)
                .field("startY", startY)
                .asString().getBody();

        assertThat(escapeRoute).isEqualTo(expectedEscapeRoute);
    }
}
