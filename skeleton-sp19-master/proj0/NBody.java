public class NBody {
    public static double readRadius(String file_name) {
        In in = new In(file_name);
        int num_planet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String file_name) {
        In in = new In(file_name);
        int num_planet = in.readInt();
        double radius = in.readDouble();
        Body[] planets = new Body[num_planet];
        Body b;
        for(int i = 0; i < num_planet; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            b = new Body(xP,yP,xV,yV,m,img);
            planets[i] = b;
        }
        return planets;
    }

    public static void main(String[] args) {
        // collecting all needed input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] planets = readBodies(filename);
        double radius = readRadius(filename);

        // drawing the background
        StdDraw.setScale(-1*radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        // drawing the Body's
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }

        // creating an animation
        StdDraw.enableDoubleBuffering();
        // StdDraw.show();
        double time = 0;
        while (time <= T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            // calculate net forces in x and y directions
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            // update the position and velocity for each Body
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            // draw the picture
            StdDraw.setScale(-1*radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            // update time
            time += dt;            
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}