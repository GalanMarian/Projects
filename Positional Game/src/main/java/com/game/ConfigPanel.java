package com.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    private JLabel label;
    private JSpinner spinnerColumns;
    private JSpinner spinnerRows;
    protected JButton button;

    public ConfigPanel(MainFrame frame) {
        this.frame=frame;
        init();

    }
    private void init()
    {
        label = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerColumns = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        button = new JButton("Create");

        add(label); //JPanel uses FlowLayout by default
        add(spinnerRows);
        add(spinnerColumns);
        add(button);


    }
    public Integer getRows()
    {
        try {
            spinnerRows.commitEdit();
        } catch ( java.text.ParseException e ) {
            System.err.println(e);
        }
        int value = (Integer) spinnerRows.getValue();
        return value;
    }
    public Integer getColumns()
    {
        try {
            spinnerColumns.commitEdit();
        } catch ( java.text.ParseException e ) {
            System.err.println(e);
        }
        int value = (Integer) spinnerColumns.getValue();
        return value;
    }

}
