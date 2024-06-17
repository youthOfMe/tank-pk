package com.yangge.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;

    public static int WIDTH = ResourceMgr.tankD.getWidth();
    public static int HEIGHT = ResourceMgr.tankD.getHeight();

    private Random random = new Random();

    private Group group = Group.BAD;

    private boolean moving = true;

    private TankFrame tankFrame;
    private boolean living = true;

    public Tank() {
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics graphics) {
        if (!living) tankFrame.tanks.remove(this);

        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.tankL, x, y ,null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.tankU, x, y ,null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankR, x, y ,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankD, x, y ,null);
                break;
        }

        move();
    }

    private void move() {
        if (!moving) return;

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (random.nextInt(10) > 5) {
            this.fire();
        }
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2 - 1;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2 + 4;
        tankFrame.bullets.add(new Bullet(bX, bY, this.dir, group, this.tankFrame));
    }


    public void die() {
        living = false;
    }
}
