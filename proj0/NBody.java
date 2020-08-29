/**
 * The goal of this class is to simulate a universe specified in one of the data
 * files.
 */

public class NBody {
    /*
     * all methods are static, it is meaning less to have instance variable. Cannot
     * make a static reference to the non-static field R.
     */

    // public int N;
    // public double R;
    private static String imageToDraw = "images/starfield.jpg";

    public static double readRadius(String fileName) {
        final In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        final In in = new In(fileName);
        int num_of_body = in.readInt();
        double radius = in.readDouble();
        Planet[] bodies = new Planet[num_of_body];

        for (int i = 0; i < num_of_body; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = "images/" + in.readString();

            bodies[i] = new Planet(xP, yP, xV, yV, m, img);

        }

        /** for each is not working */
        // for (Body bodies_element : bodies) {
        // double xP = in.readDouble();
        // double yP = in.readDouble();
        // double xV = in.readDouble();
        // double yV = in.readDouble();
        // double m = in.readDouble();
        // String img = in.readString();

        // bodies_element = new Body(xP, yP, xV, yV, m, img);
        // }
        return bodies;
    }

    // Use "javac -encoding utf-8 NBody.java" to compile the method
    // "java NBody 157788000.0 25000.0 data/planets.txt"
    public static void main(String[] args) {
        // Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] bodies = readPlanets(filename);

        // Drawing the Background
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        // StdDraw.picture(0, 0, imageToDraw);

        // // Drawing More than One Body
        // for (Body bodies_elem : in_bodies) {
        // bodies_elem.draw();
        // }
        // StdDraw.show();
        // StdDraw.pause(2);

        // Creating an Animation
        for (double time = 0; time < T; time++) {
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];

            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            // After calculating the net forces for every Body, call update
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, imageToDraw);
            for (Planet in_bodies_elem : bodies) {
                in_bodies_elem.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", bodies[i].xxPos, bodies[i].yyPos,
                    bodies[i].xxVel, bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }

}