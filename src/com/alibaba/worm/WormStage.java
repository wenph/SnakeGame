package com.alibaba.worm;

import com.alibaba.cell.Cell;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by pinghua.wph on 2015/10/12.
 */
public class WormStage extends JPanel {
    public static final int ROWS = 35;
    public static final int COLS = 35;
    public static final int CELL_SIZE = 10;

    private Worm worm;
    private Cell food;

    public WormStage(){
        worm = new Worm();
        food = createFood();
    }

    private Cell createFood() {
        int x;
        int y;
        Random r = new Random();
        do {
            x = r.nextInt(COLS);
            y = r.nextInt(ROWS);
        }while (worm.contains(x,y));
        return new Cell(x,y);
    }

    public String toString(){
        return "worm:" + worm + "\nfood:" + food;
    }

    public void paint(Graphics g){
        //背景
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());
        //food
        g.setColor(Color.red);
        g.fill3DRect(CELL_SIZE * food.getX(), CELL_SIZE * food.getY(), CELL_SIZE, CELL_SIZE, true);
        //snake
        g.setColor(Color.green);
        Cell[] cells = worm.getCells();
        for(int i=0;i<cells.length; i++){
            Cell node = cells[i];
            g.fill3DRect(CELL_SIZE*node.getX(), CELL_SIZE*node.getY(), CELL_SIZE, CELL_SIZE, true);
        }

    }

    public void action(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (worm.hit()) {
                    worm = new Worm();
                    food = createFood();
                } else {
                    boolean eat = worm.creep(food);
                    if (eat) {
                        food = createFood();
                    }
                }
                repaint();
            }
        }, 0, 1000 / 5);

        this.requestFocus();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_UP:
                        creepTo(Worm.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        creepTo(Worm.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        creepTo(Worm.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        creepTo(Worm.RIGHT);
                        break;
                }
            }
        });
    }

    private void creepTo(int direction) {
        if(worm.hit(direction)){
            worm = new Worm();
            food = createFood();
        }else{
            boolean eat = worm.creep(direction, food);
            if(eat){
                food = createFood();
            }
        }
        repaint();
    }

    public static void main(String[] args){
        // start
        JFrame frame = new JFrame("SnakeGame");
        WormStage panel = new WormStage();
        frame.setLayout(null); // 取消自动充满
        frame.add(panel);
        panel.setSize(CELL_SIZE * COLS, CELL_SIZE * ROWS);
        panel.setLocation(50, 50);
        frame.setSize(450, 480);
        panel.setBorder(new LineBorder(Color.black));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.action();
    }
}