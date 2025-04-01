import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.Random;
import java.awt.Color;



/**
 * This is a Java class called AbstractArtDrawing which contains a method drawLinesAndPoints that generates a
 * GUI window and draws random lines and points on it. The method generates 10 random lines, calculates the middle
 * point of each line, and draws a blue circle at each middle point. It then checks for intersection between every
 * pair of lines and draws a red circle at each intersection point. The class also contains several static final
 * variables used throughout the code. The main method serves as the entry point of the application.
 */
public class AbstractArtDrawing {
    private static final int WINDOW_WIDTH = 400;    //The width of screen
    private static final int WINDOW_HEIGHT = 300;   //The height of screen
    /**
     * The Two.
     */
    static final int TWO = 2;
    /**
     * The One.
     */
    static final int ONE = 1;
    /**
     * The Zero.
     */
    static final int ZER0 = 0;
    /**
     * The Ten.
     */
    static final int TEN = 10;
    /**
     * The Three.
     */
    static final int THREE = 3;

    /**
     * Draw lines and points.
     * The drawLinesAndPoints method generates a GUI window with the title "Lines and Points Example" and
     * dimensions of 400 pixels width and 300 pixels height. The method then generates 10 random lines using
     * the Random class and stores them in an array lines. The middle point of each line is calculated and
     * stored in two separate arrays xPoints and yPoints, and the middle point is drawn on the GUI window in
     * blue using the DrawSurface object.
     * Next, the method iterates over each pair of lines in the lines array, and for each pair, it checks if
     * the lines intersect using the isIntersecting method of the Line class. If they intersect, it calculates
     * the intersection point using the intersectionWith method of the Line class and draws a red circle at the
     * intersection point on the GUI window using the DrawSurface object.
     */
    public void drawLinesAndPoints() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Lines and Points Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Lines and Points Example", WINDOW_WIDTH, WINDOW_HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[TEN];
        // Draw 10 lines and store the coordinates of the middle points in xPoints and yPoints
        for (int i = ZER0; i < TEN; ++i) {
            int x1 = rand.nextInt(WINDOW_WIDTH) + ONE; // get integer in range 1-400
            int y1 = rand.nextInt(WINDOW_HEIGHT) + ONE; // get integer in range 1-300
            int x2 = rand.nextInt(WINDOW_WIDTH) + ONE; // get integer in range 1-400
            int y2 = rand.nextInt(WINDOW_HEIGHT) + ONE; // get integer in range 1-300
            lines[i] = new Line(x1, y1, x2, y2);
            d.setColor(Color.BLACK);
            d.drawLine(x1, y1, x2, y2);

            // Calculate the coordinates of the middle point and store them in xPoints and yPoints
            int xMid = (x1 + x2) / TWO;
            int yMid = (y1 + y2) / TWO;

            // Draw the middle point in blue
            d.setColor(Color.BLUE);
            d.fillCircle(xMid, yMid, THREE);
        }

        // Draw circles at the intersection points
        for (int i = ZER0; i < TEN; ++i) {
            for (int j = i + ONE; j < TEN; ++j) {
                // Calculate the intersection point of lines i and j
                if (lines[i].isIntersecting(lines[j])) {
                    Point intersection = lines[i].intersectionWith(lines[j]);
                    double x = intersection.getX();
                    double y = intersection.getY();
                    d.setColor(Color.RED);
                    d.fillCircle((int) x, (int) y, THREE);
                }
            }
        }

        gui.show(d);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawLinesAndPoints();
    }

}
