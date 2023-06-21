package com.world.pcclient.mainWindow;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.world.pcclient.Panels;

import javax.swing.border.Border;

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
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        // add the bodyStatistics, weatherPanel, and surroundingPlaces panels to the top
        // row
        JSplitPane topRow = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panels.bodyStatistics, panels.weatherPanel);
        topRow.setDividerSize(4);

        JSplitPane topRightRow = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topRow, panels.surroundingPlaces);
        topRightRow.setDividerSize(4);

        // add the visibleItems panel to the top row next to the weatherPanel
        JSplitPane leftSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topRightRow, panels.visibleItems.getPanel());
        leftSplit.setDividerSize(4);

        // add the visibleResources panel, visibleCreatures panel, and panels.behaviours
        // panel
        // to the right of visibleItems panel
        JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplit,
                panels.visibleResources.getPanel());
        rightSplit.setDividerSize(4);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rightSplit,
                panels.visibleCreatures.getPanel());
        mainSplit.setDividerSize(4);
        JSplitPane finalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainSplit,
                panels.behaviours.getPanel());
        finalSplit.setDividerSize(4);

        // add the listenerPanel to the bottom row
        JSplitPane bottomRow = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panels.listenerPanel, null);
        bottomRow.setDividerSize(0);

        // add the finalSplit and bottomRow panels to the outerSplit panel
        JSplitPane outerSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, finalSplit, bottomRow);
        outerSplit.setDividerSize(4);

        getContentPane().add(outerSplit);
        pack();

        setWindowSize();
        setVisible(true);
    }

    /**
     * Sets the text in the header of the window.
     * It should say the current program condition.
     * 
     * @param titleCondition
     */
    public void setTitleCondition(StringBuilder titleCondition) {
        setTitle(titleName + " ~ " + titleCondition);
        revalidate();
        repaint();
    }

    /**
     * Method for setting a border with text to the panel.
     * It is used to write a title to a main panel.
     * 
     * @param title
     * @param component
     */
    public static void setTitleBorder(String title, JComponent component) {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 16));
        component.setBorder(titledBorder);
    }
}
