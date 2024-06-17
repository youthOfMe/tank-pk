package com.yangge.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true) {
            Thread.sleep(1);
            tankFrame.repaint();
        }
    }
}
