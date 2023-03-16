package com.example;

import com.example.MainWindow.MainWindow;

public class App {
    private static final int MAIN_SCREEN = 0;
    private static final int SECONDARY_SCREEN = 1;

    private static final int PREFERED_SCREEN = MAIN_SCREEN;
    public static void main( String[] args ) {
        new MainWindow(PREFERED_SCREEN);
    }
}
