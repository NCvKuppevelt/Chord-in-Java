import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdentifiableTest {
    Identifiable sut;

    @BeforeEach
    void setUp() {
        sut = new Identifiable("") {
        };
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0, 0, false",
            "2, 0, 0, false",
            "3, 0, 0, true ",
            "4, 0, 0, false",
            "5, 0, 0, false",

            "1, 1, 1, false",
            "2, 1, 1, true ",
            "3, 1, 1, true ",
            "4, 1, 1, true ",
            "5, 1, 1, false",
    })
    void betweenTestSuite(String sutIdent, int lowerInclIndex, int upperInclIndex, boolean expected) {
        sut.setUnhashedIdentifier(sutIdent);
        var lower = new Identifiable("") {
        };
        var upper = new Identifiable("") {
        };
        var lowerIncl = BoundIncl.values()[lowerInclIndex];
        var upperIncl = BoundIncl.values()[upperInclIndex];
        lower.setUnhashedIdentifier("2");
        upper.setUnhashedIdentifier("4");

        var actual = sut.between(lowerIncl, "2", "4", upperIncl);

        assertEquals(expected, actual);
    }

    @Test
    void test() {
        Comparable<String> test = "987654321";
        System.out.println(test.compareTo("7"));
    }
}