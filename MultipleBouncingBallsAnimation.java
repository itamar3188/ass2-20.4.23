import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Rectangle;
import java.awt.Color;



/**
 * This is a class that creates a multiple bouncing balls animation in a graphical user interface (GUI) window.
 * The animation consists of several balls bouncing around the window, colliding with each other and the walls
 * of the window. The properties of the balls, such as their size, position, and color, are generated randomly
 * within the constraints of the window size and the ball's radius. The class also includes a main method that
 * takes command-line arguments to specify the radius of each ball. If no arguments are provided or an argument
 * cannot be parsed as an integer, a ball with a radius of zero is created. The animation is displayed in the GUI
 * window indefinitely, with each frame updated every 50 milliseconds.
 */
public class MultipleBouncingBallsAnimation {
    private static final int WINDOW_WIDTH = 200;    //The width of screen
    private static final int WINDOW_HEIGHT = 200;   //The height of screen
    private static final int MAX_SPEED = 2;         //The max speed of balls
    /**
     * The Zero.
     */
    static final int ZERO = 0;
    /**
     * The Half.
     */
    static final double HALF = 0.5;
    /**
     * The Fifty.
     */
    static final int FIFTY = 50;
    /**
     * The Two.
     */
    static final int TWO = 2;
    /**
     * The Color range.
     */
    static final int COLOR_RANGE = 256;
    /**
     * The Three hundred sixty.
     */
    static final int THREE_HUNDRED_SIXTY = 360;

    /**
     * Multiple bouncing balls.
     * The multipleBouncingBalls method takes an array of Ball objects as input and animates them bouncing around
     * the screen in a GUI window.
     * First, the method calculates the speed of each ball based on its size. If a ball's size is larger than 50,
     * it will move at a fixed slow speed, otherwise, its speed is proportional to its radius.
     * Then, a new Velocity object is created for each ball with a random angle and the calculated speed.
     * After that, a new GUI object and a Sleeper object are created to set up the animation loop.
     *
     * @param balls the balls
     */
    public static void multipleBouncingBalls(Ball[] balls) {
        for (int i = ZERO; i < balls.length; i++) {
            double speed;
            int size = balls[i].getSize();
            if (size > FIFTY) {
                speed = MAX_SPEED * HALF; // set a fixed slow speed for large balls
            } else {
                speed = MAX_SPEED * ((double) FIFTY / size); // set a speed relative to the ball's radius
            }
            //choose random angle
            Velocity velocity = Velocity.fromAngleAndSpeed(Math.random() * THREE_HUNDRED_SIXTY, speed);
            balls[i] = new Ball(balls[i].getX(), balls[i].getY(), size, balls[i].getColor());
            balls[i].setVelocity(velocity);
        }
        int x, y;
        Rectangle rectangle1 = new Rectangle(ZERO, ZERO, WINDOW_WIDTH, WINDOW_HEIGHT);
        for (Ball ball : balls) {
            ball.setRectangle(rectangle1);
            ball.checkRectangle();
            int radius = ball.getSize();
            x = (int) (Math.random() * (WINDOW_WIDTH - TWO * radius)) + radius;
            y = (int) (Math.random() * (WINDOW_HEIGHT - TWO * radius)) + radius;
            ball.setCenter(x, y);
        }
        GUI gui = new GUI("Multiple Bouncing Balls Animation", WINDOW_WIDTH, WINDOW_HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.drawOn(d);
                ball.moveOneStep();

            }
            gui.show(d);
            sleeper.sleepFor(FIFTY);
        }
    }


    /**
     * The entry point of application.
     * The main method first creates an array of Ball objects with properties such as position, size, and color.
     * These properties are generated randomly within the constraints of the GUI window size and the ball's radius.
     * The color is also randomly generated.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            try {
                int value = Integer.parseInt(args[i]);
                if (value <= 0) {
                    System.out.println(args[i] + " has ignored because is not positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println(args[i] + " Input String cannot be parsed to integer.");
                args[i] = "0";
            }
        }
        Ball[] balls = new Ball[args.length];
        int x = 0, y = 0;
        for (int i = ZERO; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);
            int r = COLOR_RANGE;
            Color color = new Color((int) (Math.random() * r), (int) (Math.random() * r),
                    (int) (Math.random() * r));
            balls[i] = new Ball(x, y, radius, color);
        }
        multipleBouncingBalls(balls);
    }
}
