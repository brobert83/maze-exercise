package escape;

import escape.exception.NoEscapeException;
import org.springframework.stereotype.Service;

@Service
public class LabEscapeService {

    private TypeTransformer typeTransformer;
    private LabEscape labEscape;

    public LabEscapeService(TypeTransformer typeTransformer, final LabEscape labEscape) {
        this.typeTransformer = typeTransformer;
        this.labEscape = labEscape;
    }

    public String findEscapeRoute(
            String labyrinth,
            int startX,
            int startY) throws NoEscapeException {

        final char[][] labyrinthCharMatrix = typeTransformer.toCharMatrix(labyrinth);

        char[][] escape = labEscape.drawPathForEscape(labyrinthCharMatrix, startX, startY);

        return typeTransformer.toMultilineString(escape);
    }

}
