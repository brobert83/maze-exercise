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
                "\tO    O   O\n" +
                "\tO OO O O O\n" +
                "\tO  O O O O\n" +
                "\tO OO   O  \n" +
                "\tO OOOOOOOO\n" +
                "\tO        O\n" +
                "\tOOOOOOOOOO";
        int startX = 3;
        int startY = 1;

        String expectedEscapeRoute
                = "OOOOOOOOOO\n" +
                "\tO1111O111O\n" +
                "\tO1OO1O1O1O\n" +
                "\tO1 O1O1O1O\n" +
                "\tO OO111O11\n" +
                "\tO OOOOOOOO\n" +
                "\tO        O\n" +
                "\tOOOOOOOOOO";

        String escapeRoute = Unirest.post("http://localhost:8080/escapeRoute")
                .field("labyrinth", labyrinth)
                .field("startX", startX)
                .field("startY", startY)
                .asString().getBody();

        assertThat(escapeRoute).isEqualTo(labyrinth + "\n" + startX + "\n" + startY);
    }
}
