package com.yangge.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        // 初始化地方坦克
        for (int i = 0; i < 10; i++) {
            tankFrame.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD ,tankFrame));
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
