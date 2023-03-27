import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRandomly() {
        int check = 1000; // check should be big enough
        ArrayDequeSolution<Integer> cor = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> act = new StudentArrayDeque<>();
        String message = "";

        for (int i = 0; i < check; i++) {
            int flag = StdRandom.uniform(0, 4);
            Integer item = StdRandom.uniform(100);
            if (flag == 0) {
                if (!cor.isEmpty()) {
                    message = message + "removeFirst()\n";
                    assertEquals(message, cor.removeFirst(), act.removeFirst());
                }
            } else if (flag == 1) {
                if (!cor.isEmpty()) {
                    message = message + "removeLast()\n";
                    assertEquals(message, cor.removeLast(), act.removeLast());
                }
            } else if (flag == 2) {
                cor.addFirst(item);
                act.addFirst(item);
                message = message + "addFirst(" + item + ")\n";
            } else if (flag == 3) {
                cor.addLast(item);
                act.addLast(item);
                message = message + "addLast(" + item + ")\n";
            }
            // System.out.println(flag);
            // System.out.println(message);
        }
    }
}
