package Projekt02.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends JPanel {

    private final BufferedImage img;
    private final Graphics g;
    private static final int FPS = 1000 / 30;
    public static final int HEIGHT = 600;
    public static final int WIDTH = 800;

    public Raster() {
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = img.getGraphics();
        setTimer();
        setLayout(new BorderLayout());
        setInfo();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    private void setTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, FPS);
    }

    public void clear() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void setInfo() {
        JLabel lblInfo = new JLabel();
        lblInfo.setText("Reset(R) | levé tlačíko myši pohyb camerou | pravé tlačítko myši rotace objektů | prostřední tlačítko myši pohyb objektů"); //Dopsat klavesy pro kontrolovani vykreslovani
        lblInfo.setFont(new Font("courier", Font.PLAIN, 12));
        lblInfo.setForeground(new Color(0xffffff));
        add(lblInfo, BorderLayout.SOUTH);
    }

    public void drawLine(double x1, double y1, double x2, double y2, Color color) {
        g.setColor(color);
        g.drawLine((int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2), (int) Math.round(y2));
    }
}
