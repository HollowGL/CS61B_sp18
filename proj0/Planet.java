public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    public double calcDistance(Planet p) {
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double dr = calcDistance(p);
        return G * mass * p.mass / (dr * dr);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double dr = calcDistance(p);
        return calcForceExertedBy(p) * dx / dr;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double dr = calcDistance(p);
        return calcForceExertedBy(p) * dy / dr;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double sum = 0;
        for (Planet p : ps) {
            if (this.equals(p)) {
                continue;
            }
            sum += calcForceExertedByX(p);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double sum = 0;
        for (Planet p : ps) {
            if (this.equals(p)) {
                continue;
            }
            sum += calcForceExertedByY(p);
        }
        return sum;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
