package com.yangge.tank;


import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x, y;
    private Dir dir;
    private TankFrame tankFrame;

    private Group group = Group.BAD;

    private boolean living = true;

    public Bullet() {
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics graphics) {
        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL, x, y ,null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.bulletU, x, y ,null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR, x, y ,null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD, x, y ,null);
                break;
        }

        if (!living) {
            tankFrame.bullets.remove(this);
        }

        // Color c = graphics.getColor();
        // graphics.setColor(Color.RED);
        // graphics.fillOval(x, y, WIDTH, HEIGHT);
        // graphics.setColor(c);

        move();
    }

    private void move() {
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

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.living = false;
        }
    }

    public void collideWidth(Tank tank) {
        if (this.group == tank.getGroup()) return;

        // TODO: 用一共rect记录子弹的位置
        Rectangle rectBullet = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rectBullet.intersects(rectTank)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
