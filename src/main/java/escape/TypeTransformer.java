package escape;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

@Component
public class TypeTransformer {

    public String toMultilineString(char[][] matrix) {

        if (matrix == null) {
            return null;
        }

        return Arrays.stream(matrix)
                .map(String::new)
                .reduce("", (s, s2) -> s + "\n" + s2)
                .trim();
    }

    public char[][] toCharMatrix(String multilineString) {

        if (multilineString == null) {
            return null;
        }

        String[] lines = multilineString.split("\n");

        char[][] charMatrix = new char[lines.length][lines[0].length()];

        IntStream.range(0, lines.length)
                .forEach(index -> charMatrix[index] = lines[index].toCharArray());

        return charMatrix;
    }

}
