package com.example.mainWindow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;

import com.example.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class MainWindow extends JFrame {
    private final float HEIGHT_PERCENT = 0.9f;
    private final float WIDTH_PERCENT = 0.9f;
    public final Dimension screenSize;
    public final GraphicsDevice screen;

    private final StringBuilder titleName = new StringBuilder("Client");

    public MainWindow(int screenIndex) {

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
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        JSplitPane topRow = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panels.bodyStatistics, panels.surroundingPlaces);
        JSplitPane bottomRow = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panels.listenerPanel, null);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topRow, bottomRow);
        topRow.setDividerSize(4);
        mainSplit.setDividerSize(4);
        bottomRow.setDividerSize(0); // set the divider size to 0
        getContentPane().add(mainSplit);
        pack();

        setWindowSize();
        setVisible(true);
    }



    public void setTitleCondition(StringBuilder titleCondition) {
        setTitle(titleName + " ~ " + titleCondition);
        revalidate();
        repaint();
    }

    public static void setTitleBorder(String title, JComponent component){
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title );
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        component.setBorder(titledBorder);
    }
}
