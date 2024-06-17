package com.yangge.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {

    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(800, 600);
        f.setResizable(false);
        f.setTitle("霍盛展科技有限公司管理系统");
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
