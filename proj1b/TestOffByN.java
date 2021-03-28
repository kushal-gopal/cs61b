import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    CharacterComparator offByN = new OffByN(5);

    /** Tests the OffByN.equalChars */
    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertFalse(offByN.equalChars('A', 'a'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('f', 'h'));
        assertFalse(offByN.equalChars('%', '&'));
    }
}
