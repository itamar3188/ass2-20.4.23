import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.awt.Rectangle;



/**
 * This is a Java class called "MultipleFramesBouncingBallsAnimation" that animates multiple bouncing balls in
 * two separate frames. The balls are randomly generated and given a size and color. The larger the size of the
 * ball, the slower its speed is set. The balls are then placed into two separate rectangles.
 * The main method of the class is "multipleFramesAnimation", which takes an array of Ball objects as its parameter.
 * It creates two rectangle objects to define the frames for the balls and loops through each ball, setting its
 * velocity and rectangle. If the ball is in the first half of the array, it is placed in the first rectangle,
 * otherwise, it is placed in the second rectangle.
 * The method then creates a GUI object and a Sleeper object, and enters an infinite loop. Within the loop,
 * it draws the two rectangles and the balls in them, moves each ball one step, and shows the updated GUI.
 * The loop sleeps for 50 milliseconds after each iteration.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * The Two.
     */
    static final int TWO = 2;
    /**
     * The Color range.
     */
    static final int COLOR_RANGE = 256;
    /**
     * The Zero.
     */
    static final int ZERO = 0;
    /**
     * The Fifty.
     */
    static final int FIFTY = 50;
    /**
     * The Half.
     */
    static final double HALF = 0.5;
    /**
     * The Three hundred sixty.
     */
    static final int ANGLE_RANGE = 360;     //the angle range.
    private static final int MAX_SPEED = 2;
    private static final int WINDOW_WIDTH = 700;    //dimensions of the main window
    private static final int WINDOW_HEIGHT = 700;
    private static final int FRAME1_X = 50;     //define the position and size of the first frame
    private static final int FRAME1_Y = 50;
    private static final int FRAME1_WIDTH = 450;
    private static final int FRAME1_HEIGHT = 450;
    private static final int FRAME2_X = 450;    //define the position and size of the second frame.
    private static final int FRAME2_Y = 450;
    private static final int FRAME2_WIDTH = 150;
    private static final int FRAME2_HEIGHT = 150;

    /**
     * Multiple frames bouncing balls animation.
     * This method animates multiple bouncing balls in two separate frames. The balls are randomly generated and
     * given a size and color. The larger the size of the ball, the slower its speed is set. The balls are then
     * placed into two separate rectangles.
     * The method creates two rectangle objects to define the frames for the balls. It then loops through each ball
     * and sets its velocity and rectangle. If the ball is in the first half of the array, it is placed in the first
     * rectangle, otherwise, it is placed in the second rectangle.
     *
     * @param balls the balls
     */
    public static void multipleFramesAnimation(Ball[] balls) {
        int half = balls.length / TWO;
        Rectangle rectangle1 = new Rectangle(FRAME1_X, FRAME1_Y, FRAME1_WIDTH, FRAME1_HEIGHT);
        Rectangle rectangle2 = new Rectangle(FRAME2_X, FRAME2_Y, FRAME2_WIDTH, FRAME2_HEIGHT);
        for (int i = ZERO; i < balls.length; i++) {
            int size = balls[i].getSize();
            double speed;
            if (size > FIFTY) {
                // set a fixed slow speed for large balls
                speed = MAX_SPEED * HALF;
            } else {
                // set a speed relative to the ball's radius
                speed = MAX_SPEED * ((double) FIFTY / size);
            }
            Velocity velocity = Velocity.fromAngleAndSpeed(Math.random() * ANGLE_RANGE, speed);
            balls[i].setVelocity(velocity);
            if (i < half) {
                balls[i].setRectangle(rectangle1);
                balls[i].checkRectangle();
            }
            if (i >= half) {
                balls[i].setRectangle(rectangle2);
                balls[i].checkRectangle();
            }
        }
        GUI gui = new GUI("Multiple Frames Animation", WINDOW_WIDTH, WINDOW_HEIGHT);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(FRAME1_X, FRAME1_Y, FRAME1_WIDTH, FRAME1_HEIGHT);
            for (int i = ZERO; i < half; i++) {
                balls[i].drawOn(d);
            }
            d.setColor(Color.YELLOW);
            d.fillRectangle(FRAME2_X, FRAME2_Y, FRAME2_WIDTH, FRAME2_HEIGHT);
            for (int i = half; i < balls.length; i++) {
                balls[i].drawOn(d);
            }
            for (Ball ball : balls) {
                ball.moveOneStep();
            }
            gui.show(d);
            sleeper.sleepFor(FIFTY);
        }
    }

    /**
     * The entry point of application.
     * This program generates an animation with multiple bouncing balls in two different frames. The first
     * frame has a gray background and a rectangle with a yellow background in the center. The second frame has
     * a white background and a rectangle with a black background in the center. The balls move around inside their
     * respective frames and bounce off the edges of the frames and off each other.
     * To run the program, the user needs to provide command line arguments specifying the radius of the balls.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            try {
                int value = Integer.parseInt(args[i]);
                if (value <= 0) {
                    System.out.println("Some of input has ignored because is not positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input String cannot be parsed to integer.");
                args[i] = "0";
            }
        }
        Ball[] balls = new Ball[args.length];
        int half = balls.length / TWO;

        for (int i = ZERO; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);
            int r = COLOR_RANGE;
            Color color = new Color((int) (Math.random() * r), (int) (Math.random() * r),
                    (int) (Math.random() * r));
            int x, y;
            if (i < half) {
                // first half of balls in frame 1
                x = (int) (Math.random() * (FRAME1_WIDTH - TWO * radius)) + FRAME1_X + radius;
                y = (int) (Math.random() * (FRAME1_HEIGHT - TWO * radius)) + FRAME1_Y + radius;
            } else {
                // second half of balls in frame 2
                x = (int) (Math.random() * (FRAME2_WIDTH - TWO * radius)) + FRAME2_X + radius;
                y = (int) (Math.random() * (FRAME2_HEIGHT - TWO * radius)) + FRAME2_Y + radius;
            }
            balls[i] = new Ball(x, y, radius, color);

        }
        multipleFramesAnimation(balls);
    }
}
