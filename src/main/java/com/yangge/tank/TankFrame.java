package com.yangge.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 600, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    // Bullet bullet = new Bullet(300, 300, Dir.DOWN, this);
    static final int GAME_WIDTH = 1000, GAME_HEIGHT = 1000;

    public TankFrame() {
        super();
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("霍盛展科技有限公司管理系统");
        this.setVisible(true);

        this.addKeyListener(new MyKeyListener());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics graphics) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        graphics.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量: " + bullets.size(), 10, 60);
        graphics.drawString("敌人的数量: " + tanks.size(), 10, 80);
        graphics.setColor(color);

        myTank.paint(graphics);
        for (int i = 0; i < bullets.size(); i++) {
             bullets.get(i).paint(graphics);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(graphics);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWidth(tanks.get(j));
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) myTank.setMoving(false);
            else {
                myTank.setMoving(true);

                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
            }


        }
    }

}
