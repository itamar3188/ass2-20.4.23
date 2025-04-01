/**
 * The Point class represents a point in a two-dimensional coordinate system, defined by its x and y values.
 * It provides methods for calculating the distance between two points, checking if two points are equal, and
 * getting/setting the x and y values of a point. The class also includes a static method for comparing double
 * values within a certain threshold. The class can be used in a variety of applications that require working with
 * points in a two-dimensional space, such as graphics, physics, and geometry.
 */

public class Point {
    private double x;
    private double y;

    /**
     * The constant Comparison_threshold.
     */
    static final double THRESHOLD = 0.00001;

    /**
     * Double equals boolean.
     * checks if two double values are equal within a certain threshold. The method takes two double values
     * as input parameters: "a" and "b". It then calculates the absolute difference between the two values
     * using the Math.abs() method, and compares it to a predefined threshold value.
     *
     * @param a the a
     * @param b the b
     * @return boolean boolean
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < THRESHOLD;
    }

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return the double
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Equals boolean.
     * equals -- return true if the points are equal, false otherwise
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        return doubleEquals(this.x, other.x) && doubleEquals(this.y, other.y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

}
