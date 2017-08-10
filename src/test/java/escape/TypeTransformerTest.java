package escape;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class TypeTransformerTest {

    static TypeTransformer typeTransformer = new TypeTransformer();

    char[][] inputMatrix;
    String multiline;

    public TypeTransformerTest(char[][] inputMatrix, String multiline) {
        this.inputMatrix = inputMatrix;
        this.multiline = multiline;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {null, null},
                {new char[][]{{}}, ""},
                {new char[][]{{'a', 'b'}}, "ab"},
                {new char[][]{{'a', 'b'}, {'c', 'd'}}, "ab\ncd"},
        });

    }

    @Test
    public void toMultilineString() {

        String multilineString = typeTransformer.toMultilineString(inputMatrix);
        char[][] charMatrix = typeTransformer.toCharMatrix(multiline);

        assertThat(multilineString).isEqualTo(multilineString);
        assertThat(charMatrix).isEqualTo(inputMatrix);
    }

}