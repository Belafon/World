package com.world.pcclient.client.chatListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class ChatListenerPanel extends JPanel {
    private JList<String> messageList;
    private JScrollPane scrollPane;

    public ChatListenerPanel() {
        setLayout(new BorderLayout());

        // Create the list of messages
        DefaultListModel<String> model = new DefaultListModel<>();
        messageList = new JList<>(model);

        // Add the message list to a scroll pane and add to the panel
        scrollPane = new JScrollPane(messageList);
        add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(400, 75));
    }

    /**
     * Adds new message reveved from the server into the 
     * list of all messages and displays it.
     * @param message
     */
    public synchronized void addMessage(String message) {
        DefaultListModel<String> model = (DefaultListModel<String>) messageList.getModel();
        
        boolean isChatAtBottom = isScrollBarAtBottom();
        
        // Add the message to the list
        model.addElement(message);
        
        if (isChatAtBottom) {
            // Scroll to the bottom of the list
            int lastIndex = model.getSize() - 1;
            if (lastIndex >= 0) {
                messageList.ensureIndexIsVisible(lastIndex);
            }
        }

        try {
            SwingUtilities.invokeAndWait(() -> {
                repaint();
            });
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isScrollBarAtBottom() {
        JScrollBar scrollbar = scrollPane.getVerticalScrollBar();
        return scrollbar.getValue() >= scrollbar.getMaximum() - scrollbar.getVisibleAmount() - 30;
    }
}