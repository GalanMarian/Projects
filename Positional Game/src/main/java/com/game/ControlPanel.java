package com.game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    protected JButton buttonLoad;
    protected JButton buttonSave;
    protected JButton buttonExit;
    protected JButton buttonSaveToPng;
    protected JButton buttonInfo;


    public ControlPanel(MainFrame frame)
    {
        this.frame=frame;
        init();
    }
    private void init()
    {
        buttonLoad= new JButton("Load");
        buttonSave= new JButton("Save");
        buttonExit= new JButton("Exit");
        buttonInfo= new JButton("Info");
        buttonSaveToPng= new JButton("Save to PNG");
        buttonSaveToPng.setBackground(Color.LIGHT_GRAY);
        buttonInfo.setBackground(Color.LIGHT_GRAY);

        add(buttonInfo);
        add(buttonLoad);
        add(buttonSave);
        add(buttonExit);
        add(buttonSaveToPng);
    }
}
