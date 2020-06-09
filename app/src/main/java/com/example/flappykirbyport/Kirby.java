package com.example.flappykirbyport;

public class Kirby {
    private int kirbyX, kirbyY, currentFrame,velocity;
    public static int maxFrame;
    public static boolean goUp = true;
    public static int distanceMovedUp = 0;

    public Kirby() {
        kirbyX = AppConstants.SCREEN_WIDTH / 2 - AppConstants.getBitmapBank().getKirbyWidth() / 2;
        kirbyY = AppConstants.SCREEN_HEIGHT / 2 - AppConstants.getBitmapBank().getKirbyHeight() / 2;
        currentFrame = 0;
        maxFrame = 1;
        velocity = 0;
        goUp = true;
        distanceMovedUp = 0;
    }

    public int getCurrentFrame(){
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    public int getKirbyX(){
        return kirbyX;
    }

    public void setKirbyX(int kirbyX){
        this.kirbyX = kirbyX;
    }

    public int getKirbyY(){
        return kirbyY;
    }

    public void setKirbyY(int kirbyY){
        this.kirbyY = kirbyY;
    }

    public int getVelocity(){
        return velocity;
    }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
