package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SimpleDraw extends JPanel {
    // Stores lines as pairs of points
    private final ArrayList<Point> points = new ArrayList<>();

    public SimpleDraw() {
        // Listen for mouse movement
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                repaint(); // Trigger a redraw
            }
        });
        // Start a new line segment on press
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                points.add(null); // Marker for a new segment
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Always call super
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        
        for (int i = 1; i < points.size(); i++) {
            if (points.get(i) != null && points.get(i-1) != null) {
                Point p1 = points.get(i-1);
                Point p2 = points.get(i);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Swing Drawing App");
        frame.add(new SimpleDraw());
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}