package com.example.flappykirbyport;

public class BackgroundImage {

    private int backgroundImageX, backgroundImageY, backgroundImageVelocity;

    public BackgroundImage(){
        backgroundImageX = 0;
        backgroundImageY = 0;
        backgroundImageVelocity = 3;
    }

    public int getX(){
        return backgroundImageX;
    }

    public int getY(){
        return backgroundImageY;
    }

    public int getVelocity(){
        return backgroundImageVelocity;
    }

    public void SetX(int backgroundImageX){
        this.backgroundImageX = backgroundImageX;
    }

    public void SetY(int backgroundImageY){
        this.backgroundImageY = backgroundImageY;
    }

    public void SetVelocity(int backgroundImageVelocity){
        this.backgroundImageVelocity = backgroundImageVelocity;
    }
}
