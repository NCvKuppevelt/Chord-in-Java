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
        var expected = "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3";

        var actual = Sha1Hasher.encrypt(input);

        Assertions.assertEquals(expected, actual);
    }
}