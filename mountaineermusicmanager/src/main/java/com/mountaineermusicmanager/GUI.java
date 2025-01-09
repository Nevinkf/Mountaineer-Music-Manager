/*
 * File Name: GUI.java
 * Author: Nevin Fullerton
 * Date: 1/7/2025
 * Description:
 */

package com.mountaineermusicmanager;
import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {

    final private JPanel mainPanel; // Display content to user
    final private JPanel playListPanel; // Display playLists or other sections the user can view
    final private JPanel mediaPanel; // Allows user to play and pause current songs, along with other functions

    private JScrollPane tableScrollPane; // Holds the songTable and allows user to scroll in the table
    private JTable songTable;
    private LinkedList<String> songsDisplayed = new LinkedList<>(); 

    public GUI() {
        this.setLayout(new BorderLayout());

        mainPanel = new JPanel();

        songTable = new JTable();

        tableScrollPane = new JScrollPane(songTable);

        displayTable();

        mainPanel.add(tableScrollPane);

        this.add(mainPanel, BorderLayout.CENTER);

        playListPanel = new JPanel();

        JLabel testLabel2 = new JLabel("playListPanel");
        playListPanel.add(testLabel2);

        this.add(playListPanel, BorderLayout.WEST);

        mediaPanel = new JPanel();

        JLabel testLabel3 = new JLabel("mediaPanel");
        mediaPanel.add(testLabel3);

        this.add(mediaPanel, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    /*
     * 
     */
    public void displayTable() {
        String[][] testData = { // TODO remove and implement data to be variable
            {"1", "2", "3"}, 
            {"4", "5","6"},
            {"7", "8", "9"},
            {"7", "8", "9"},
            {"7", "8", "9"},
            {"7", "8", "9"},
            {"7", "8", "9"}
        };

        String[] columnNames = {"Title", "Author", "Genre"};

        songTable.setModel(new DefaultTableModel(testData, columnNames));
        songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songTable.setDefaultEditor(Object.class, null);

        refreshGUI();
    }

    /*
     * 
     */
    private void refreshGUI() {
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        GUI programGUI = new GUI();
        programGUI.setVisible(true);
    }


    // Get Song metadata and display it in table, then button to play song

}