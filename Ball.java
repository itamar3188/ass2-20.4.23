import biuoop.DrawSurface;

import java.awt.Rectangle;
import java.awt.Color;

//Itamar Cohen 318897089

/**
 * The Ball class represents a circle-shaped object that can be displayed on a graphical user interface (GUI)
 * using a given center point, radius, and color. The class provides methods for setting and getting the ball's
 * velocity, as well as for drawing the ball on a given DrawSurface object. The Ball class also includes a static
 * final variable ZERO that represents the value zero, and two static final variables WINDOW_WIDTH and WINDOW_HEIGHT
 * that represent the dimensions of the GUI window. The class uses the Point and Velocity classes to store the ball's
 * center point and velocity, respectively, and the Rectangle class to define a bounding rectangle for the ball.
 * The class includes two constructors, one that takes a Point object and the other that takes x and y coordinates,
 * as well as a radius and color, to create a new Ball object.
 */
public class Ball {
    /**
     * The Zero.
     */
    static final int ZERO = 0;      //declares a constant integer
    private static final int WINDOW_WIDTH = 200;      //the width of window
    private static final int WINDOW_HEIGHT = 200;     //the height of the window
    private Point center;       // the center point of the ball
    private int radius;         // the radius of the ball
    private Color color;        // the color of the ball
    private Velocity velocity;  // the velocity (direction and speed) of the ball
    private Rectangle rectangle;    // the bounding rectangle of the ball

    /**
     * Instantiates a new Ball with a given center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, Color color) {
        // Assign the given center, radius, and color to the instance variables
        this.center = center;
        this.radius = radius;
        this.color = color;
        // Initialize a new velocity with 0 speed in both x and y directions, and assign it to the instance variable
        Velocity velocity = new Velocity(ZERO, ZERO);
        this.velocity = velocity;
        // Initialize a new rectangle with all sides having length 0, and assign it to the instance variable
        Rectangle rectangle = new Rectangle(ZERO, ZERO, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.rectangle = rectangle;
    }

    /**
     * Instantiates a new Ball with a given x and y coordinates, radius, and color.
     *
     * @param x      the x coordinate of the center point of the ball
     * @param y      the y coordinate of the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, Color color) {
        // Create a new Point object with the given x and y coordinates, and assign it to the center instance variable
        Point p = new Point(x, y);
        this.radius = radius;
        this.color = color;
        this.center = p;
        // Initialize a new velocity with 0 speed in both x and y directions, and assign it to the instance variable
        Velocity velocity = new Velocity(ZERO, ZERO);
        this.velocity = velocity;
        // Initialize a new rectangle with all sides having length 0, and assign it to the instance variable
        Rectangle rectangle = new Rectangle(ZERO, ZERO, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.rectangle = rectangle;
    }

    /**
     * Gets the x coordinate of the center point of the ball.
     *
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the y coordinate of the center point of the ball.
     *
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        // Set the color of the drawing surface to the ball's color
        surface.setColor(this.color);
        // Draw a filled circle at the ball's position with its radius
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        // Set the ball's velocity to the given velocity
        this.velocity = v;
    }

    /**
     * Set velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        // Set the ball's velocity to the given dx and dy values
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);
    }

    /**
     * Get velocity velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        // Return the ball's velocity
        return this.velocity;
    }

    /**
     * Set rectangle.
     *
     * @param r the r
     */
    public void setRectangle(Rectangle r) {
        // Set the ball's rectangle to the given rectangle
        this.rectangle = r;
    }

    /**
     * Get rectangle rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        // Return the ball's rectangle
        return this.rectangle;
    }

    /**
     * Sets center.
     *
     * @param x the x
     * @param y the y
     */
    public void setCenter(int x, int y) {
        Point point = new Point(x, y);
        this.center = point;
    }

    /**
     * Check rectangle.
     */
    public void checkRectangle() {
        int diameter = this.radius * 2;
        int width = (int) this.getRectangle().getWidth();
        if (diameter > width) {
            // The diameter of the ball is larger than the width of the rectangle
            // Set the radius to half of the rectangle's width
            this.radius = width / 2;
            int x = (int) this.getRectangle().getCenterX();
            int y = (int) this.getRectangle().getCenterY();
            this.setCenter(x, y);
        }
    }

    /**
     * The method first computes the new position of the ball by applying the current velocity to the current
     * center position of the ball. It then checks if the ball would go out of bounds horizontally or
     * vertically by comparing the new center position of the ball with the radius of the ball and the
     * boundaries of a 200x200 grid.
     * If the ball would go out of bounds horizontally, the x-velocity is reversed by multiplying it by -1,
     * simulating a bounce. Similarly, if the ball would go out of bounds vertically, the y-velocity is reversed
     * by multiplying it by -1.
     * Finally, the method updates the center of the ball by applying the velocity to the current center position
     * again. This is done to ensure that the ball's position is updated even if it didn't hit a boundary.
     */
    public void moveOneStep() {
        Rectangle r = new Rectangle(this.getRectangle());
         Point newCenter = new Point(0, 0);
        newCenter = this.getVelocity().applyToPoint(this.center);

        if ((newCenter.getX() + this.radius > r.getMaxX()) || (newCenter.getX() - this.radius < r.getMinX())) {
            this.velocity.setDx(-this.velocity.getDx());
            newCenter = this.getVelocity().applyToPoint(this.center);
        }

        if ((newCenter.getY() + this.radius > r.getMaxY()) || (newCenter.getY() - this.radius < r.getMinY())) {
            this.velocity.setDy(-this.velocity.getDy());
            newCenter = this.getVelocity().applyToPoint(this.center);
        }

        if ((newCenter.getX() + this.radius > r.getMaxX())) {
            newCenter = new Point(r.getMaxX() - this.radius, this.center.getY());
        }

        if ((newCenter.getX() - this.radius < r.getMinX())) {
            newCenter = new Point(r.getMinX() + this.radius, this.center.getY());
        }

        if ((newCenter.getY() - this.radius < r.getMinY())) {
            newCenter = new Point(this.center.getX(), r.getMinY() + this.radius);
        }

        if ((newCenter.getY() + this.radius > r.getMaxY())) {
            newCenter = new Point(this.center.getX(), r.getMaxY() - this.radius);
        }

        this.center = newCenter;
    }
}