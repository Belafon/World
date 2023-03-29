package com.example.MainWindow;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import com.example.Panels;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class MainWindow extends JFrame {
    private final float HEIGHT_PERCENT = 0.9f;
    private final float WIDTH_PERCENT = 0.9f;
    public final Dimension screenSize;
    public final GraphicsDevice screen;

    private final StringBuilder titleName = new StringBuilder("Client");

    public MainWindow(int screenIndex) {
        super("My Window");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the specified screen device
        GraphicsDevice[] screens = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        if (screenIndex < 0 || screenIndex >= screens.length) {
            screenIndex = 0;
        }
        screen = screens[screenIndex];

        // Set the size of the window
        screenSize = screen.getDefaultConfiguration().getBounds().getSize();

        setWindowSize();

        setTitleCondition(new StringBuilder("trying to connect"));
        // Make the window visible
        setVisible(true);
    }

    private void setWindowSize() {
        int width = (int) (screenSize.getWidth() * WIDTH_PERCENT);
        int height = (int) (screenSize.getHeight() * HEIGHT_PERCENT);
        setSize(width, height);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Set the position of the window on the screen
        int x = screen.getDefaultConfiguration().getBounds().x
                + (int) (screenSize.getWidth() * (1 - WIDTH_PERCENT) / 2);

        int y = screen.getDefaultConfiguration().getBounds().y
                + (int) (screenSize.getHeight() * (1 - HEIGHT_PERCENT) / 2);

        setBounds(x, y, width, height);
    }

    public void displayPanels(Panels panels) {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(panels.bodyStatistics);
        getContentPane().add(panels.listenerPanel);
        pack();

        setWindowSize();
        setVisible(true);
    }

    public void setTitleCondition(StringBuilder titleCondition) {
        setTitle(titleName + " ~ " + titleCondition);
        revalidate();
        repaint();
    }
}
