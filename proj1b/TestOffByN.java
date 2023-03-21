import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testOffBy1() {
        OffByN offByN = new OffByN(1);
        assertTrue(offByN.equalChars('a', 'b'));
        assertTrue(offByN.equalChars('z', 'y'));
        assertTrue(offByN.equalChars('&', '%'));
    }

    @Test
    public void testOffBy2() {
        OffByN offByN = new OffByN(2);
        assertTrue(offByN.equalChars('c', 'a'));
        assertTrue(offByN.equalChars('z', 'x'));
        assertFalse(offByN.equalChars('a', 'a'));
    }
}
