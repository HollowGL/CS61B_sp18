public class NBody {

    public static final String file = "data/planets";

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]),
                dt = Double.parseDouble(args[1]);
        Planet[] ps = new Planet[5];
        ps = readPlanets(args[2]);

        double scale = readRadius(file);
        StdDraw.setScale(-scale, scale);
        StdDraw.clear();
    }

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        in.readDouble();
        Planet[] ps = new Planet[n];
        for (int i = 0; i < n; i++) {
            Planet newp = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
            ps[i] = newp;
        }
        for (Planet p : ps) {
            System.out.println(p.imgFileName);
        }
        return ps;
    }
}
