package com.game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static java.awt.BorderLayout.*;


public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() throws IOException {
        super("PositionalGame" );
        this.setIconImage(ImageIO.read(new File("stick.png")));
        init();
        configPanel.button.addActionListener( (ActionEvent e) ->
                {
                    canvas.createOffScreenImage();
                    canvas.game.reset();
                    canvas.init(configPanel.getRows(), configPanel.getColumns());
                    canvas.paintGrid();
                    canvas.paintSticks();
                    repaint();

                }
        );
        controlPanel.buttonExit.addActionListener( (ActionEvent e) ->
                {
                    this.dispose();
                }
        );
        controlPanel.buttonInfo.addActionListener( (ActionEvent e) ->
        {

            try {
                File file = new File("C:/Users/Marian/Downloads/info.txt");
                Scanner myReader = new Scanner(file);
                StringBuilder sb=new StringBuilder();
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    sb.append(data);
                    sb.append("\n");
                }
                JOptionPane.showMessageDialog(this, sb);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

        });
        controlPanel.buttonSaveToPng.addActionListener((ActionEvent e )
    ->
        {
            canvas.saveCurrentStateToPng();
        });

    }
    private void init()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        configPanel= new ConfigPanel(this);
        add(configPanel, PAGE_START);

        controlPanel= new ControlPanel(this);
        add(controlPanel, PAGE_END);

        canvas= new DrawingPanel(this);
        add(canvas, CENTER);

        pack();
    }

  
}
