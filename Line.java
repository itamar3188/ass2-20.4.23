/**
 * The Line class represents a line segment in two-dimensional space. It has methods to calculate the length
 * of the line, the middle point of the line, and the start and end points of the line. The class also includes a
 * method to check if two lines are equal. The equality of two lines is determined by comparing the start and end
 * points of both lines in both directions.
 */

public class Line {
    /**
     * The constant Comparison_threshold.
     */
    static final double THRESHOLD = 0.00001;
    /**
     * The Epsilon.
     */
    static final double EPSILON = 1e-9;
    /**
     * The Two.
     */
    static final double TWO = 2;
    /**
     * The Zero.
     */
    static final double ZERO = 0;

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

    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param obj the obj
     * @return the boolean
     */
// constructors
    public boolean equals(Line obj) {
        if (obj == null) {
            return false; // null check
        }
        // Compare start and end points of both lines in both directions
        if (this.start.equals(obj.start) && this.end.equals(obj.end)) {
            return true;
        }
        return this.end.equals(obj.start) && this.start.equals(obj.end);
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Instantiates a new Line.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     */
    public Line(Point p1, Point p2) {
        this.start = p1;
        this.end = p2;
    }

    /**
     * Length double.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point.
     *
     * @return Returns the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / TWO;
        double y = (this.start.getY() + this.end.getY()) / TWO;
        return new Point(x, y);
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     * The method first checks if either line is inclusive of the other, or if they are overlapping,
     * using the helper methods "isinclusing" and "isOverlapping". If either of these conditions is true,
     * the method returns true, indicating that the lines intersect.
     * Next, the method checks if the two lines are equal, meaning they have the same start and end points,
     * or if one of the lines is actually a point, using the helper method "lineIsPoint". If either of these
     * conditions is true, the method also returns true.Finally, if none of the above conditions are met,
     * the method calls the "intersectionWith" method.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        if (this.isinclusing(other) || other.isinclusing(this) || isOverlapping(this, other)) {
            return true;
        }
        if (this.equals(other) || lineIsPoint(this, other) != null) {
            return true;
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * Intersection with point.
     * This is a method in Java that calculates the intersection point between two Line objects.
     * The method takes a Line object as input parameter, which is referred to as "other" in the code.
     * The method first checks if the two lines share a common point, using the helper method "lineIsPoint".
     * If they do, the method returns that common point.
     * Next, the method checks if the two lines are equal, meaning they have the same start and end points.
     * If they are equal, the method returns null, indicating that there is no unique intersection point.
     * If the lines are not equal or do not share a common point, the method calculates the intersection point
     * using the equation for the intersection of two lines in Cartesian coordinates.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        if (lineIsPoint(this, other) != null) {
            return (lineIsPoint(this, other));
        }
        if (this.equals(other)) {
            return null;
        }
        //extract the x- and y-coordinates of the start and end points of both lines
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (den == ZERO) {
            if (this.isinclusing(other) || other.isinclusing(this)) {
                return null;
            }
            // lines are parallel
            if (this.end.equals(other.start) || other.end.equals(this.start)) {
                // lines share a common point
                return (this.end.equals(other.start) ? this.end : this.start);
            } else {
                // lines are disjoint
                return null;
            }
        }
        //calculate the numerator for the x-coordinate and y-coordinate separately using the coefficients.
        double xNum = (x1 * y2 - y1 * x2) * (x3 - x4) - (x1 - x2) * (x3 * y4 - y3 * x4);
        double yNum = (x1 * y2 - y1 * x2) * (y3 - y4) - (y1 - y2) * (x3 * y4 - y3 * x4);
        // The intersection point is then given by the ratio of the two numerators divided by the denominator.
        double x = xNum / den;
        double y = yNum / den;

        if (x < Math.min(x1, x2) || x > Math.max(x1, x2) || x < Math.min(x3, x4) || x > Math.max(x3, x4)) {
            return null; // intersection point is outside the line segments
        }

        if (y < Math.min(y1, y2) || y > Math.max(y1, y2) || y < Math.min(y3, y4) || y > Math.max(y3, y4)) {
            return null; // intersection point is outside the line segments
        }

        return new Point(x, y);
    }

    /**
     * Isinclusing boolean.
     * The  method takes in a Line object as a parameter and returns a boolean
     * value indicating whether the passed-in Line object is completely contained within the current Line object.
     * The method first checks if the passed-in Line object is collinear (i.e., lies on the same line) with the
     * current Line object. If not, it returns false, since non-collinear lines cannot be inclusive of each other.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isinclusing(Line other) {
        // Check if both lines are collinear
        if (!isCollinear(other)) {
            return false;
        }

        // Check if other line's endpoints are inside this line's endpoints
        boolean startInside = isBetween(start, end, other.start);
        boolean endInside = isBetween(start, end, other.end);

        return startInside && endInside;
    }

    /**
     * The isCollinear method is a Java function that takes a Line object as a parameter and returns a boolean
     * value indicating whether the passed-in Line object is collinear with the current Line object.
     *
     * @param other
     * @return the boolean
     */
    private boolean isCollinear(Line other) {
        // Check if slopes are equal
        double slope1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
        double slope2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());

        return doubleEquals(slope1, slope2);
    }

    /**
     * The isBetween method is a Java function that takes three Point objects (start, end, and point) as parameters
     * and returns a boolean value indicating whether the point object lies between the start and end objects.
     *
     * @param start
     * @param end
     * @param point
     * @return the boolean
     */
    private boolean isBetween(Point start, Point end, Point point) {
        // Check if point is within the range of start and end
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());

        return (point.getX() >= minX && point.getX() <= maxX) && (point.getY() >= minY && point.getY() <= maxY);
    }

    /**
     * Is point on line boolean.
     * This method determines whether a given point lies on a given line segment. It first calculates
     * the bounding box of the line segment by finding the minimum and maximum x and y values of the two
     * endpoints. If the point is outside this bounding box, it cannot be on the line segment and the method
     * returns false.
     *
     * @param p    the p
     * @param line the line
     * @return the boolean
     */
    public boolean isPointOnLine(Point p, Line line) {
        double minX = Math.min(line.start.getX(), line.end.getX());
        double maxX = Math.max(line.start.getX(), line.end.getX());
        double minY = Math.min(line.start.getY(), line.end.getY());
        double maxY = Math.max(line.start.getY(), line.end.getY());

        // Check if the point is within the bounding box of the line segment
        if (p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY) {
            return false;
        }

        // Calculate the distance from the point to the line using the cross product
        double pDyiff = p.getY() - line.start.getY(), pDxiff = p.getX() - line.start.getX();
        double crossProduct =
                (pDyiff * (line.end.getX() - line.start.getX())) - (pDxiff * (line.end.getY() - line.start.getY()));
        if (Math.abs(crossProduct) > EPSILON) {
            // The point is not on the line
            return false;
        }

        // The point is on the line
        return true;
    }

    /**
     * Is overlapping boolean.
     * This method checks if two lines overlap by first checking if they are parallel. If they are parallel,
     * it checks if they are collinear and overlapping. If they are not collinear, then they do not overlap.
     * If they are collinear, it checks if there is any overlap by checking if the max x-coordinate of one line
     * is greater than the min x-coordinate of the other line, and if the max y-coordinate of one line is greater
     * than the min y-coordinate of the other line.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean isOverlapping(Line a, Line b) {
        // Get the four points defining the two lines
        Point a1 = a.start;
        Point a2 = a.end;
        Point b1 = b.start;
        Point b2 = b.end;
        // Calculate the slopes of the two lines
        double slopeA = (a2.getY() - a1.getY()) / (a2.getX() - a1.getX());
        double slopeB = (b2.getY() - b1.getY()) / (b2.getX() - b1.getX());
        if (doubleEquals(a2.getX(), a1.getX())) {
            slopeA = ZERO;
        }
        if (doubleEquals(b2.getX(), b1.getX())) {
            slopeB = ZERO;
        }

        // Check if the two lines are parallel
        if (doubleEquals(slopeA, slopeB)) {
            // The lines are parallel, check if they are collinear and overlapping
            double bDiff = b2.getX() - b1.getX();
            double crossP = (bDiff * (a1.getY() - b1.getY()) - (b2.getY() - b1.getY()) * (a1.getX() - b1.getX()));
            if (Math.abs(crossP) > EPSILON) {
                // The lines are not collinear, so they cannot overlap
                return false;
            }
            // The lines are collinear, check if there is any overlap
            double aMinX = Math.min(a1.getX(), a2.getX());
            double aMaxX = Math.max(a1.getX(), a2.getX());
            double bMinX = Math.min(b1.getX(), b2.getX());
            double bMaxX = Math.max(b1.getX(), b2.getX());
            if (aMaxX < bMinX || bMaxX < aMinX) {
                // There is no overlap
                return false;
            }
            double aMinY = Math.min(a1.getY(), a2.getY());
            double aMaxY = Math.max(a1.getY(), a2.getY());
            double bMinY = Math.min(b1.getY(), b2.getY());
            double bMaxY = Math.max(b1.getY(), b2.getY());

            // There is no overlap
            return !(aMaxY < bMinY) && !(bMaxY < aMinY);
            // There is overlap
        }
        // Calculate the y-intercepts of the two lines
        double interceptA = a1.getY() - slopeA * a1.getX();
        double interceptB = b1.getY() - slopeB * b1.getX();

        // Calculate the intersection point of the two lines
        double x = (interceptB - interceptA) / (slopeA - slopeB);
        double y = slopeA * x + interceptA;
        Point intersection = new Point(x, y);

        // Check if the intersection point is within the bounds of both lines
        if (isPointOnLine(intersection, a) && isPointOnLine(intersection, b)) {
            // The lines intersect at the intersection point
            // Check if there are more than one intersection points
            // by checking if there is another intersection point
            // within the bounds of both lines
            for (Point p : new Point[]{a1, a2, b1, b2}) {
                if (!p.equals(intersection) && isPointOnLine(p, a) && isPointOnLine(p, b)) {
                    // There is another intersection point, so the lines overlap
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Line is point boolean.
     * The method first checks if both lines are points, i.e., their start and end points are the same.
     * If so, it compares the two points directly and returns the point if they are equal.
     * If only one of the lines is a point, the method checks if that point is on the other line using
     * the isPointOnLine method. If the point is on the other line, it returns the point.
     * If neither line is a point, it returns null, indicating that the lines do not intersect at a point.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public Point lineIsPoint(Line a, Line b) {
        if (a.start.equals(a.end) && b.start.equals(b.end)) {
            // Both lines are points, so compare them directly
            if (a.start.equals(b.start)) {
                return a.start;
            }
        } else if (a.start.equals(a.end)) {
            // Line a is a point, check if it's on line b
            if (isPointOnLine(a.start, b)) {
                return a.start;
            }
        } else if (b.start.equals(b.end)) {
            // Line b is a point, check if it's on line a
            if (isPointOnLine(b.start, a)) {
                return b.start;
            }
        }
        // Neither line is a point, so they can't intersect at a point
        return null;
    }

}
