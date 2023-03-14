public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]),
                dt = Double.parseDouble(args[1]);
        int n = (new In(args[2])).readInt();
        Planet[] ps = new Planet[n];
        ps = readPlanets(args[2]);

        double scale = readRadius(args[2]);
        StdDraw.setScale(-scale, scale);
        StdDraw.clear();

        // drawing all of the planets
        StdDraw.picture(0, 0,"images/starfield.jpg");
        for (Planet p : ps) {
            p.draw();
        }

        StdDraw.enableDoubleBuffering();
        for (double t = 0; t <= T; t += dt) {
            double[] xForces = new double[n],
                     yForces = new double[n];
            for (int i = 0; i < n; i++) {
                xForces[i] = ps[i].calcNetForceExertedByX(ps);
                yForces[i] = ps[i].calcNetForceExertedByY(ps);
            }
            for (int i = 0; i < n; i++) {
                ps[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0,"images/starfield.jpg");
            for (Planet p : ps) {
                p.draw();
            }
            StdDraw.show();

            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", ps.length);
        StdOut.printf("%.2e\n", scale);
        for (int i = 0; i < ps.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
                    ps[i].yyVel, ps[i].mass, ps[i].imgFileName);
        }
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
        return ps;
    }
}
