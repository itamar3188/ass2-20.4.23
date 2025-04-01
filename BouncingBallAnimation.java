import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Rectangle;


/**
 * The type Bouncing ball animation.
 * The drawAnimation method draws an animation of a moving ball on a GUI window. The ball starts at the start
 * point and moves with a velocity determined by the dx and dy parameters.
 * A new Ball object is created with the initial position at the start point and a radius of 30 pixels, colored black.
 * The velocity of the ball is set to a new Velocity object created from the dx and dy parameters
 * using the fromAngleAndSpeed method.
 */
public class BouncingBallAnimation {
    private static final int WINDOW_WIDTH = 200;  //The width of screen
    private static final int WINDOW_HEIGHT = 200;  //The height of screen
    /**
     * The Thirty.
     */
    static final int THIRTY = 30;
    /**
     * The Fifty.
     */
    static final int FIFTY = 50;
    /**
     * The Two.
     */
    static final int TWO = 2;
    /**
     * The Zero.
     */
    static final int ZERO = 0;
    /**
     * The Three.
     */
    static final int THREE = 3;

    /**
     * The One.
     */
    static final int ONE = 1;

    /**
     * @param start is the point we get from user
     * @return Point
     */
    private static Point adjustStartPoint(Point start) {
        // Adjust x-coordinate if it's outside the window
        double x = start.getX();
        if (x < THIRTY) {
            System.out.println("The x is not in the normal range - adjust to the left.");
            x = THIRTY;
        } else if (x > WINDOW_WIDTH - THIRTY) {
            System.out.println("The x is not in the normal range - adjust to the right.");
            x = WINDOW_WIDTH - THIRTY;
        }

        // Adjust y-coordinate if it's outside the window
        double y = start.getY();
        if (y < THIRTY) {
            System.out.println("The y is not in the normal range - adjust to the top.");
            y = THIRTY;
        } else if (y > WINDOW_HEIGHT - THIRTY) {
            System.out.println("The y is not in the normal range - adjust to the buttom.");
            y = WINDOW_HEIGHT - THIRTY;
        }

        return new Point(x, y);
    }

    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball", WINDOW_WIDTH, WINDOW_HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), THIRTY, java.awt.Color.BLACK);
        Velocity v = new Velocity(dx, dy); // create a new Velocity object
        ball.setVelocity(v);
        Rectangle rectangle1 = new Rectangle(ZERO, ZERO, WINDOW_WIDTH, WINDOW_HEIGHT);
        ball.setRectangle(rectangle1);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            // wait for 50 milliseconds.
            sleeper.sleepFor(FIFTY);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Error: Please enter four arguments.");
            args = new String[]{"100", "100", "0", "0"}; // set default values
        }
        for (int i = 0; i < 4; i++) {
            try {
                double value = Double.parseDouble(args[i]);
            } catch (NumberFormatException e) {
                System.out.println(args[i] + " Input String cannot be parsed to double.");
                return;
            }
        }
        Point p = new Point(Double.parseDouble(args[ZERO]), Double.parseDouble(args[ONE]));
        Point adjustedStart = adjustStartPoint(p);
        drawAnimation(adjustedStart, Double.parseDouble(args[TWO]), Double.parseDouble(args[THREE]));
    }
}
