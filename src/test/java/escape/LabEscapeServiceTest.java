package escape;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LabEscapeServiceTest {

    LabEscapeService labEscapeService;

    @Mock TypeTransformer typeTransformer;
    @Mock LabEscape labEscape;

    @Before
    public void setUp() {
        labEscapeService = new LabEscapeService(typeTransformer, labEscape);
    }

    @Test
    public void testFindEscapeRoute() throws Exception {

        String labyrinth = "";
        int startX = 0;
        int startY = 0;

        char[][] matrixLabyrinth = new char[][]{{'0'}};
        char[][] expectedEscape = new char[][]{{'1'}};
        String expectedEscapeString = "1";

        when(typeTransformer.toCharMatrix(labyrinth)).thenReturn(matrixLabyrinth);
        when(labEscape.drawPathForEscape(matrixLabyrinth, startX, startY)).thenReturn(expectedEscape);
        when(typeTransformer.toMultilineString(expectedEscape)).thenReturn(expectedEscapeString);

        String escapeRoute = labEscapeService.findEscapeRoute(labyrinth, startX, startY);

        assertThat(escapeRoute).isEqualTo(expectedEscapeString);

        verify(typeTransformer).toCharMatrix(labyrinth);
        verify(typeTransformer).toMultilineString(expectedEscape);
        verify(labEscape).drawPathForEscape(matrixLabyrinth, startX, startY);
    }

}