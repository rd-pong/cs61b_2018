import java.lang.Math;

/**
 * Body
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /* all methods should be non-static. */

    public double calcDistance(Planet b) {
        double xdis = Math.pow(this.xxPos - b.xxPos, 2);
        double ydis = Math.pow(this.yyPos - b.yyPos, 2);
        double distance = Math.sqrt(xdis + ydis);
        return distance;
    }

    public double calcForceExertedBy(Planet b) {
        double G_const = 6.67e-11;
        double force = G_const * b.mass * this.mass / Math.pow(this.calcDistance(b), 2);
        return force;
    }

    public double calcForceExertedByX(Planet b) {
        double force_total = calcForceExertedBy(b);
        double force_x = force_total * (b.xxPos - this.xxPos) / calcDistance(b);
        return force_x;
    }

    public double calcForceExertedByY(Planet b) {
        double force_total = calcForceExertedBy(b);
        double force_y = force_total * (b.yyPos - this.yyPos) / calcDistance(b);
        return force_y;
    }

    public double calcNetForceExertedByX(Planet[] b_array) {

        double net_force_x = 0;
        for (Planet b_element : b_array) {
            if (!this.equals(b_element)) {
                net_force_x += this.calcForceExertedByX(b_element);
            }
        }
        return net_force_x;
    }

    public double calcNetForceExertedByY(Planet[] b_array) {
        double net_force_y = 0;
        for (Planet b_element : b_array) {
            /*check self. Bodys cannot exert gravitational forces on themselves!*/
            if (!this.equals(b_element)) {
                net_force_y += this.calcForceExertedByY(b_element);
            }
        }
        return net_force_y;
    }

    public void update(double dt, double fX, double fY){
        double a_x = fX/this.mass;
        double a_y = fY/this.mass;
        this.xxVel += a_x * dt;
        this.yyVel += a_y * dt;
        this.xxPos += this.xxVel*dt;
        this.yyPos += this.yyVel*dt;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }

}
