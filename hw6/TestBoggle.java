import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class TestBoggle {
    @Test
    public void test1() {
        List<String> actual = Boggle.solve(7, "exampleBoard.txt");
        List<String> expect = List.of(new String[]
                {"thumbtacks", "thumbtack", "setbacks", "setback", "ascent", "humane", "smacks"});
        assertEquals(expect, actual);
    }

    @Test
    public void test2() {
        Boggle.dictPath = "trivial_words.txt";
        List<String> actual = Boggle.solve(20, "exampleBoard2.txt");
        List<String> expect = List.of(new String[]{"aaaaa", "aaaa"});
        assertEquals(expect, actual);
    }
}
