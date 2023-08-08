package com.example.world;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.world.game.maps.weather.ColorViewTransition;
import com.example.world.game.maps.weather.DifferenceColorViewTransition;
import com.example.world.game.maps.weather.WeatherPanel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

@RunWith(AndroidJUnit4.class)
public class WeatherPanelTest {

    private WeatherPanel weatherPanel;
    private View mockedView;

    @Before
    public void setup() {
        Context appContext = ApplicationProvider.getApplicationContext();
        mockedView = new View(appContext);
        weatherPanel = new WeatherPanel(mockedView);
    }

    @Test
    public void testAddAndRemoveColorViewTransition() throws Exception {
        ColorViewTransition mockTransition1 = new DifferenceColorViewTransition(new ColorViewTransition.Color(10, 20, 30, 40), 5);
        ColorViewTransition mockTransition2 = new DifferenceColorViewTransition(new ColorViewTransition.Color(5, 15, 25, 35), 5);

        // Add two transitions
        weatherPanel.addColorViewTransition(mockTransition1);
        weatherPanel.addColorViewTransition(mockTransition2);

        // Verify that both transitions are added
        Set<ColorViewTransition> colorViewTransitions = getColorViewTransitions(weatherPanel);
        assertEquals(2, colorViewTransitions.size());

        // Remove one transition
        weatherPanel.removeColorViewTransition(mockTransition1);

        // Verify that only one transition is left
        colorViewTransitions = getColorViewTransitions(weatherPanel);
        assertEquals(1, colorViewTransitions.size());
    }

    @Test
    public void testMakeTransition() throws Exception {
        ColorViewTransition mockTransition1 = new DifferenceColorViewTransition(new ColorViewTransition.Color(10, 20, 30, 40), 5);
        ColorViewTransition mockTransition2 = new DifferenceColorViewTransition(new ColorViewTransition.Color(5, 15, 25, 35), 5);

        // Add two transitions
        weatherPanel.addColorViewTransition(mockTransition1);
        weatherPanel.addColorViewTransition(mockTransition2);

        // Call makeTransition method to update color transitions
        Set<ColorViewTransition> colorViewTransitions = getColorViewTransitions(weatherPanel);
        Iterator<ColorViewTransition> iterator = colorViewTransitions.iterator();
        invokeMakeTransition(weatherPanel, iterator);

        // Verify that the color transitions are updated accordingly
        assertEquals(7, getRTransition(weatherPanel));
        assertEquals(17, getGTransition(weatherPanel));
        assertEquals(27, getBTransition(weatherPanel));
        assertEquals(37, getATransition(weatherPanel));
    }

    // Helper methods to access private members using reflection

    private Set<ColorViewTransition> getColorViewTransitions(WeatherPanel weatherPanel) throws Exception {
        Field field = WeatherPanel.class.getDeclaredField("colorViewTransitions");
        field.setAccessible(true);
        return (Set<ColorViewTransition>) field.get(weatherPanel);
    }

    private void invokeMakeTransition(WeatherPanel weatherPanel, Iterator<ColorViewTransition> iterator) throws Exception {
        Field field = WeatherPanel.class.getDeclaredField("rTransition");
        field.setAccessible(true);
        field.setInt(weatherPanel, 0);

        field = WeatherPanel.class.getDeclaredField("gTransition");
        field.setAccessible(true);
        field.setInt(weatherPanel, 0);

        field = WeatherPanel.class.getDeclaredField("bTransition");
        field.setAccessible(true);
        field.setInt(weatherPanel, 0);

        field = WeatherPanel.class.getDeclaredField("aTransition");
        field.setAccessible(true);
        field.setInt(weatherPanel, 0);

        field = WeatherPanel.class.getDeclaredField("weatherPanel");
        field.setAccessible(true);
        field.set(weatherPanel, iterator);

        weatherPanel.run();
    }

    private int getRTransition(WeatherPanel weatherPanel) throws Exception {
        Field field = WeatherPanel.class.getDeclaredField("rTransition");
        field.setAccessible(true);
        return field.getInt(weatherPanel);
    }

    private int getGTransition(WeatherPanel weatherPanel) throws Exception {
        Field field = WeatherPanel.class.getDeclaredField("gTransition");
        field.setAccessible(true);
        return field.getInt(weatherPanel);
    }

    private int getBTransition(WeatherPanel weatherPanel) throws Exception {
        Field field = WeatherPanel.class.getDeclaredField("bTransition");
        field.setAccessible(true);
        return field.getInt(weatherPanel);
    }

    private int getATransition(WeatherPanel weatherPanel) throws Exception {
        Field field = WeatherPanel.class.getDeclaredField("aTransition");
        field.setAccessible(true);
        return field.getInt(weatherPanel);
    }
}