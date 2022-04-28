package com.game;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class DrawingPanel extends JPanel {
    final MainFrame frame;
    Integer rows, cols;
    Integer canvasWidth = 400, canvasHeight = 400;
    Integer boardWidth, boardHeight;
    Integer cellWidth, cellHeight;
    Integer padX, padY;
    Integer stoneSize = 20;
    BufferedImage image;
    Graphics2D offscreen;
    boolean turn = true;
    GameMap game = new GameMap();



    public DrawingPanel(MainFrame frame)
    {
        this.frame=frame;
        addListener();
        mouseClicked();
        createOffScreenImage();
        init(frame.configPanel.getRows(), frame.configPanel.getColumns());
        paintGrid();
        paintSticks();

    }
    public void createOffScreenImage() {
        image = new BufferedImage(
                canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    public void init(Integer rows, Integer columns)
    {

        this.rows=rows;
        this.cols=columns;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics)
    {
        graphics.drawImage(image, 0, 0, this);
    }
    public void paintGrid()
    {
        offscreen.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            offscreen.drawLine(x1, y1, x2, y2);
        }
        //vertical lines
        for(int col = 0; col< cols; col++)
        {
            int x1=padX + col * cellWidth;
            int y1=padY;
            int x2=x1;
            int y2=y1+ boardHeight;
            offscreen.drawLine(x1,y1,x2,y2);
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                offscreen.setColor(Color.LIGHT_GRAY);
                offscreen.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }


    }
    private void addListener(){
        addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                canvasWidth = frame.canvas.getWidth();
                canvasHeight = frame.canvas.getHeight();
                init(rows, cols);
                repaint();
            }
        });
    }
    private void mouseClicked()
    {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawStone(e.getX(), e.getY());
            }
        });
    }
    public void paintSticks()
    {
            //total sticks: n*(m-1)+m*(n-1)
        Random rn = new Random();
        Integer maximum= rows*(cols-1)+cols*(rows-1);
        Integer minimum= maximum/3;
        Integer randomNum;
        Integer range = maximum - minimum + 1;
        Integer i = rn.nextInt(range);
        randomNum =  minimum + i;

        for(i=1;i<=randomNum;i++)
        {
            Integer randomRow =1+ rn.nextInt(rows);
            Integer randomCol =1+ rn.nextInt(cols);
            Integer neighbourRow=1;
            Integer neighbourCol=1;
            if(randomRow>1 && randomRow <= rows-1 && randomCol>1 && randomCol<=cols-1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(4);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow-1;
                        neighbourCol=randomCol;
                        break;
                    case 2:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol+1;
                        break;
                    case 3:
                        neighbourRow=randomRow+1;
                        neighbourCol=randomCol;
                        break;
                    case 4:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol-1;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomRow==1 && randomCol==1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(2);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol+1;
                        break;
                    case 2:
                        neighbourRow=randomRow+1;
                        neighbourCol=randomCol;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomRow==1 && randomCol==cols)
            {
                Integer randomNeighbour= 1+ rn.nextInt(2);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol-1;
                        break;
                    case 2:
                        neighbourRow=randomRow+1;
                        neighbourCol=randomCol;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomRow==rows && randomCol==cols)
            {
                Integer randomNeighbour= 1+ rn.nextInt(2);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow-1;
                        neighbourCol=randomCol;
                        break;
                    case 2:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol-1;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomRow==rows && randomCol==1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(2);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow-1;
                        neighbourCol=randomCol;
                        break;
                    case 2:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol+1;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomRow==1 && randomCol>1 && randomCol<=cols-1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(3);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol-1;
                        break;
                    case 2:
                        neighbourRow=randomRow+1;
                        neighbourCol=randomCol;
                        break;
                    case 3:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol+1;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomCol==cols && randomRow>1 && randomRow<=rows-1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(3);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow-1;
                        neighbourCol=randomCol;
                        break;
                    case 2:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol-1;
                        break;
                    case 3:
                        neighbourRow=randomRow+1;
                        neighbourCol=randomCol;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomRow==rows && randomCol>1 && randomCol<=cols-1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(3);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol+1;
                        break;
                    case 2:
                        neighbourRow=randomRow-1;
                        neighbourCol=randomCol;
                        break;
                    case 3:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol-1;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }
            if(randomCol==1 && randomRow>1 && randomRow<=rows-1)
            {
                Integer randomNeighbour= 1+ rn.nextInt(3);
                switch (randomNeighbour){
                    case 1:
                        neighbourRow=randomRow-1;
                        neighbourCol=randomCol;
                        break;
                    case 2:
                        neighbourRow=randomRow;
                        neighbourCol=randomCol+1;
                        break;
                    case 3:
                        neighbourRow=randomRow+1;
                        neighbourCol=randomCol;
                        break;
                    default:
                        neighbourRow=-1;
                        neighbourCol=1;
                }
            }

            Node key = new Node(randomRow, randomCol);
            Node neighbour= new Node(neighbourRow, neighbourCol);
            if(!game.stickNodes.contains(key))
            {
                game.stickNodes.add(key);
                if(!key.neighbours.contains(neighbour))
                    key.addNeighbour(neighbour);
            }
            else
            {
                if(!game.getNode(key).neighbours.contains(neighbour))
                    game.getNode(key).addNeighbour(neighbour);
            }
            if(!game.stickNodes.contains(neighbour))
            {
                game.stickNodes.add(neighbour);
                if(!neighbour.neighbours.contains(key))
                    neighbour.addNeighbour(key);
            }
            else
            {
                if(!game.getNode(neighbour).neighbours.contains(key))
                    game.getNode(neighbour).addNeighbour(key);
            }

            Integer x1,x2,y1,y2;
            x1=padX+ (randomCol-1)*cellWidth;
            y1=padY+ (randomRow-1)*cellHeight;
            x2=padX+ (neighbourCol-1)*cellWidth;
            y2=padY+ (neighbourRow-1)*cellHeight;
            offscreen.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            offscreen.setColor(Color.BLACK);
            offscreen.drawLine(x1,y1,x2,y2);
       }

    }

    private void drawStone(int x, int y )
    {

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int xCenter = (padX + col * cellWidth) - (stoneSize/2);
                int yCenter = (padY + row * cellHeight) - (stoneSize/2);
                Node tempNode= new Node(row+1,col+1);
                if(!game.drawnedNodes.contains(tempNode)){
                if((int)Math.pow((x-xCenter-stoneSize/2), 2) + (int)Math.pow((y-yCenter-stoneSize/2), 2) < (int)Math.pow(stoneSize/2,2))
                {

                    repaint();
                    Node node=game.getNode(new Node(row+1,col+1));
                    if(game.gameMap.isEmpty()){
                    if(game.stickNodes.contains(node))
                    {
                        game.gameMap.put(node, node.neighbours);
                        if(turn)
                        {
                            offscreen.setColor(Color.BLUE);
                            turn=false;
                        } else
                        {
                            offscreen.setColor(Color.RED);
                            turn=true;
                        }
                        offscreen.drawOval(xCenter, yCenter, stoneSize, stoneSize);
                        offscreen.fillOval(xCenter, yCenter, stoneSize, stoneSize);
                        game.drawnedNodes.add(node);

                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Wrong move");

                    }
                    }
                    else
                    {
                        List <Node> tempList= game.gameMap.get(game.drawnedNodes.get(game.drawnedNodes.size()-1));
                        if(tempList.contains(node))
                        {
                            game.gameMap.put(node, node.neighbours);
                            List<Node> anotherList= game.gameMap.get(node);
                            anotherList.remove(game.drawnedNodes.get(game.drawnedNodes.size()-1));

                            for(Node nodee : game.gameMap.keySet())
                            {
                                if(game.gameMap.containsKey(nodee))
                                    anotherList.remove(nodee);

                            }
                            game.gameMap.put(node,anotherList);
                            if(turn)
                            {
                                offscreen.setColor(Color.BLUE);
                                turn=false;
                            } else
                            {
                                offscreen.setColor(Color.RED);
                                turn=true;
                            }
                            offscreen.drawOval(xCenter, yCenter, stoneSize, stoneSize);
                            offscreen.fillOval(xCenter, yCenter, stoneSize, stoneSize);
                            game.drawnedNodes.add(node);

                            if(anotherList.isEmpty())
                            {

                                String player;
                                if(!turn)
                                    player="The BLUE Player won!";
                                else
                                    player="The RED Player won!";
                                JOptionPane.showMessageDialog(frame, player);
                                saveCurrentStateToPng();
                                createOffScreenImage();
                                game.reset();
                                init(rows, cols);
                                paintGrid();
                                paintSticks();
                                repaint();
                            }

                        }
                        else
                            JOptionPane.showMessageDialog(frame, "Wrong move");
                    }
                    row=rows+1;
                    col=cols+1;

                 }}

            }

        }

    }
    public BufferedImage getImage() {
        return image;
    }
    public void saveCurrentStateToPng() {
        try {
            ImageIO.write(getImage(), "png", new File("game-board.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
