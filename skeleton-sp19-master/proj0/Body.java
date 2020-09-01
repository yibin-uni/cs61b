public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = img;
                }
    
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx_sq = Math.pow((xxPos - b.xxPos), 2);
        double dy_sq = Math.pow((yyPos - b.yyPos), 2);
        double dist = Math.sqrt(dx_sq + dy_sq);
        return dist;
    }

    public double calcForceExertedBy(Body b) {
        double dist = calcDistance(b);
        final double G = 6.67e-11;
        double force = G * mass * b.mass / Math.pow(dist, 2);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double force = calcForceExertedBy(b);
        double dx = b.xxPos - xxPos;
        double dist = calcDistance(b);
        double force_x = force * dx / dist;
        return force_x;
    }

    public double calcForceExertedByY(Body b) {
        double force = calcForceExertedBy(b);
        double dy = b.yyPos - yyPos;
        double dist = calcDistance(b);
        double force_y = force * dy / dist;
        return force_y;
    }

    public double calcNetForceExertedByX(Body[] b_array) {
        double net_fx = 0;
        for (int i = 0; i < b_array.length; i++) {
            if (! this.equals(b_array[i])) {
                net_fx += calcForceExertedByX(b_array[i]);                
            }
        }
        return net_fx;
    }

    public double calcNetForceExertedByY(Body[] b_array) {
        double net_fy = 0;
        for (int i = 0; i < b_array.length; i++) {
            if (! this.equals(b_array[i])) {
                net_fy += calcForceExertedByY(b_array[i]);
            }    
        }
        return net_fy;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass, ay = fy / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }    

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}