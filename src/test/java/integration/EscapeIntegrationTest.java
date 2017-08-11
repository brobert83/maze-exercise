package integration;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import web.LabEscapeSpringBootApplication;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = LabEscapeSpringBootApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class EscapeIntegrationTest {

    public static final String URL = "http://localhost:8080/escapeRoute";

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
                  "O••••O•••O\n" +
                  "O•OO•O•O•O\n" +
                  "O• O•O•O•O\n" +
                  "O OO•••O••\n" +
                  "O OOOOOOOO\n" +
                  "O        O\n" +
                  "OOOOOOOOOO";

        HttpResponse<String> escapeRoute = Unirest.post(URL)
                .field("labyrinth", labyrinth)
                .field("startX", startX)
                .field("startY", startY)
                .asObject(String.class);

        assertThat(escapeRoute.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(escapeRoute.getBody()).isEqualTo(expectedEscapeRoute);
    }

    @Test
    public void drawPathForEscape_backtrack() throws UnirestException {

        String labyrinth
                =
                "OOOOOOOOOO\n" +
                "O    O   O\n" +
                "O OO O O O\n" +
                "O  O O O O\n" +
                "O OO   O O\n" +
                "O OOO OOOO\n" +
                "O        O\n" +
                "OOOOOOOO O";

        int startX = 3;
        int startY = 1;

        String expectedEscapeRoute
                =
                "OOOOOOOOOO\n" +
                "O••••O   O\n" +
                "O•OO•O O O\n" +
                "O• O•O O O\n" +
                "O OO•• O O\n" +
                "O OOO•OOOO\n" +
                "O    ••••O\n" +
                "OOOOOOOO•O";

        HttpResponse<String> escapeRoute = Unirest.post(URL)
                .field("labyrinth", labyrinth)
                .field("startX", startX)
                .field("startY", startY)
                .asObject(String.class);

        assertThat(escapeRoute.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(escapeRoute.getBody()).isEqualTo(expectedEscapeRoute);
    }

    @Test
    public void noEscape() throws UnirestException {

        String noEscapelabyrinth =
                "OOOOOOOOOO\n" +
                "O    O   O\n" +
                "O OO O O O\n" +
                "O  O O O O\n" +
                "O OO   O O\n" +
                "O OOOOOOOO\n" +
                "O        O\n" +
                "OOOOOOOOOO";

        HttpResponse<String> escapeRoute = Unirest.post(URL)
                .field("labyrinth", noEscapelabyrinth)
                .field("startX", 3)
                .field("startY", 1)
                .asObject(String.class);

        assertThat(escapeRoute.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(escapeRoute.getBody()).isEqualTo("Could not find any escape route");
    }

}
