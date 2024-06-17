package com.yangge.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200, y = 200;

    public TankFrame() {
        super();
        this.setSize(800, 600);
        this.setResizable(false);
        this.setTitle("霍盛展科技有限公司管理系统");
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.fillRect(x, y, 50, 50);
        x += 10;
        y += 10;
    }

}
