package com.example.flappykirbyport;

import java.util.Random;

public class Cloud {
    //cloudX = cloud X-coordinate, cloudOffsetY = top cloud bottom edge
    private int cloudX, topCloudOffsetY;
    private Random random;
    public Cloud(int cloudX, int topCloudOffsetY){
        this.cloudX = cloudX;
        this.topCloudOffsetY = topCloudOffsetY;
        random = new Random();

    }

    public int getTopCloudOffsetY(){
        return topCloudOffsetY;
    }

    public int getCloudX(){
        return cloudX;
    }

    public int getTopCloudY(){
        return topCloudOffsetY - AppConstants.getBitmapBank().getCloudHeight();
    }

    public int getBottomCloudY(){
        return topCloudOffsetY + AppConstants.gapBetweenTopAndBotClouds;
    }

    public void setCloudX(int cloudX){
        this.cloudX = cloudX;
    }

    public void setTopCloudOffsetY(int topCloudOffsetY){
        this.topCloudOffsetY = topCloudOffsetY;
    }
}
