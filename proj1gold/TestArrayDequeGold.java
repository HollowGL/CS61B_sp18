import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void TestRandomly() {
        ArrayDequeSolution<Integer> cor = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> act = new StudentArrayDeque<>();

        Integer r1 = StdRandom.uniform(1000);
        cor.addFirst(r1);
        cor.addFirst(r1);
        act.addFirst(r1);
        act.addFirst(r1);
        assertEquals(cor.removeLast(), act.removeLast());

        Integer r2 = StdRandom.uniform(1000);
        act.addLast(r1);
        act.addLast(r1);
        cor.addLast(r2);
        act.addLast(r2);
        assertEquals(cor.removeLast(), act.removeLast());
        assertEquals(cor.removeFirst(), act.removeFirst());

        for (int i = 0; i < 10; i++) {
            Integer r3 = StdRandom.uniform(-1000, 1000);
            act.addFirst(r3);
            cor.addFirst(r3);
            assertEquals(cor.removeLast(), act.removeLast());
        }

        for (int i = 0; i < 10; i++) {
            Integer r4 = StdRandom.uniform(-1000, 1000);
            act.addLast(r4);
            cor.addLast(r4);
            assertEquals(cor.removeFirst(), act.removeFirst());
        }
    }
}
