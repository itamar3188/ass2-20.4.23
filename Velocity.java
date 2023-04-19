/**
 * The Velocity class represents a two-dimensional vector that describes the change in position over time.
 * It contains methods for creating a new Velocity object from an angle and speed, setting and getting the values
 * of its dx and dy components, and applying its vector to a given Point to obtain a new Point object. It also
 * includes a doubleEquals method that determines if two double values are equal within a certain threshold,
 * and defines two constant values: ZERO which represents the numerical value 0, and THRESHOLD which represents
 * the maximum difference allowed between two double values to be considered equal.
 */
//Itamar Cohen 318897089
public class Velocity {
    /**
     * The Zero.
     */
    static final double ZERO = 0;
    private double dx;
    private double dy;
    /**
     * The constant Comparison_threshold.
     */
    static final double THRESHOLD = 0.00001;

    /**
     * Double equals boolean.
     *
     * @param a the a
     * @param b the b
     * @return boolean boolean
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Instantiates a new Velocity.
     * constructor
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get dx double.
     *
     * @return the double
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get dy double.
     *
     * @return the double
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }


    /**
     * Apply to point point.
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy)
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * From angle and speed velocity.
     * This method takes an angle in degrees and a speed, and calculates the corresponding velocity vector.
     * The velocity is returned as a new Velocity object.
     * First, the angle in degrees is converted to radians using the Math.toRadians() method. Then,
     * the vertical component of the velocity is calculated as -speed * Math.cos(radian), since the y-axis is
     * inverted in computer graphics (positive y is downward).
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radian = Math.toRadians(angle);
        double dy = -speed * Math.cos(radian);
        double dx = speed * Math.sin(radian);
        if (doubleEquals(dx, ZERO)) {
            dx = ZERO;
        }
        if (doubleEquals(dy, ZERO)) {
            dy = ZERO;
        }
        return new Velocity(dx, dy);
    }
}

