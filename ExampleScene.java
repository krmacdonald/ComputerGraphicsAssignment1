package Assignment;
import java.awt.*;        // import statements to make necessary classes available
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
    }
}
