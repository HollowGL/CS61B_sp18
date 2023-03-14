/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) { // notice that int != Integer
        return a == b;
    }

    public static void main(String[] args) {
        System.out.println(isSameNumber(125, 125));
        System.out.println(isSameNumber(127, 127));
        System.out.println(isSameNumber(128, 128));
        System.out.println(isSameNumber(129, 129));
    }
}
