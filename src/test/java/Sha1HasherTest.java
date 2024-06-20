import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sha1HasherTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void stringIsEncryptedWithSha1() {
        var input = "test";
        var expected = -1741702189;

        var actual = Sha1Hasher.hash(input);

        Assertions.assertEquals(expected, actual);
    }
}