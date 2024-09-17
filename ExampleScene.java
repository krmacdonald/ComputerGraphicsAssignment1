package Assignment;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

public class ExampleScene extends JPanel{
    public static void main(String[] args){
        JFrame window;

        window = new JFrame("Kyle's epic scene 0:<");
        window.setContentPane(new ExampleScene());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setResizable(true);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screen.width - window.getWidth())/2, (screen.height - window.getWidth())/2);
        window.setVisible(true);
    }

    private float pixelSize;

    public ExampleScene(){
        setPreferredSize(new Dimension(750, 750));
    }

    protected void paintComponent(Graphics graphics){
        Graphics2D graphics2d = (Graphics2D) graphics.create();

        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2d.setColor(new Color(66, 245, 239));
        graphics2d.fillRect(0, 0, getWidth(), getHeight());

        applyWindowToViewportTransformation(graphics2d, 0, 100, 0, 100, true);
        drawScene(graphics2d);
    }

    private void drawScene(Graphics2D graphics2d){
        drawGround(graphics2d);
        createTrees(graphics2d);
        createSeesaw(graphics2d);
        createSun(graphics2d, 50, 0);
    }

    private void drawGround(Graphics2D graphics2d){
        
        //Creates a giant rectangle on the ground
        Path2D ground = new Path2D.Double();
        ground.moveTo(0, 100);
        ground.lineTo(100, 100);
        ground.lineTo(100, 60);
        ground.lineTo(0, 60);
        ground.lineTo(0,100);
        ground.closePath();
        
        //Colors the ground
        graphics2d.setPaint(new Color(0, 230, 0));
        graphics2d.fill(ground);

        //Draws the lake in the center of the ground using a curved line
        Path2D poly = new Path2D.Double();
        poly.moveTo(10,60);
        poly.quadTo(50, 115, 90, 60);
        poly.closePath();

        //Colors the pond
        graphics2d.setPaint(new Color(0, 12, 230));
        graphics2d.fill(poly);
    }
    
    //Utilizes the createTree function to draw both of the trees, called in drawScene function
    private void createTrees(Graphics2D graphics2d){
        createTree(graphics2d, 0, 80);
        createTree(graphics2d, 80, 80);
    }

    //Creates a singular tree based on XY points provided
    private void createTree(Graphics2D graphics2d, double x, double y){
        //Creates path2d obj for tree base
        Path2D polyTree = new Path2D.Double();

        //Creates the trapezoid for the tree's base
        polyTree.moveTo(x, y);
        polyTree.lineTo(x + 20, y);
        polyTree.lineTo(x + 15, y - 40);
        polyTree.lineTo(x + 5, y - 40);
        polyTree.lineTo(x, y);
        graphics2d.setColor(new Color(133, 93, 0));
        graphics2d.fill(polyTree);

        //Creates leaves
        graphics2d.setColor(new Color(9, 160, 1));
        graphics2d.fill(new Ellipse2D.Double(x-5, y-50, 30, 30));
    }

    //TODO Create seesaw function, will just be triangle and line for the board
    private void createSeesaw(Graphics2D graphics2d){
        Path2D seesaw = new Path2D.Double();
        seesaw.moveTo(10, 90);
        seesaw.lineTo(30, 85);
        seesaw.lineTo(31, 84);
        seesaw.lineTo(11, 91);
        graphics2d.setPaint(new Color(9,150, 6));
        graphics2d.fill(seesaw);
    }

    private void createSun(Graphics2D graphics2d, double x, double y){
        for(int i = 0; i < 5; i ++){
            graphics2d.setPaint(new Color(255, 230 + i * 5, i * 50, 115 + i * 20));
            graphics2d.fill(new Ellipse2D.Double(x + i * 1.5, y + i * 1.5, 30 - i * 3, 30 - i * 3));
        }
    }

    //Function from Professor Duncan that sets the dimensions for coding purposes
    private void applyWindowToViewportTransformation(Graphics2D graphics2d, double left, double right, double top, double bottom, boolean preserveAspect){
        int width = getWidth();
        int height = getHeight();

        if(preserveAspect){
            double displayAspect = Math.abs((double)height / width);
            double requestedAspect = Math.abs(( bottom-top ) / ( right-left ));
            if (displayAspect > requestedAspect) {
                double excess = (bottom-top) * (displayAspect/requestedAspect - 1);
                bottom += excess/2;
                top -= excess/2;
            }
            else if (displayAspect < requestedAspect) {
                double excess = (right-left) * (requestedAspect/displayAspect - 1);
                right += excess/2;
                left -= excess/2;
            }
        }
        graphics2d.scale( width / (right-left), height / (bottom-top) );
        graphics2d.translate( -left, -top );
        double pixelWidth = Math.abs(( right - left ) / width);
        double pixelHeight = Math.abs(( bottom - top ) / height);
        pixelSize = (float)Math.max(pixelWidth,pixelHeight);
    }
}
