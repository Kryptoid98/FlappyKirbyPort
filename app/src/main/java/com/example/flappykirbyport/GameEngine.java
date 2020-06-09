package com.example.flappykirbyport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.text.MessagePattern;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Delayed;

public class GameEngine {

    BackgroundImage backgroundImage;
    Kirby kirby;
    static int gameState;
    ArrayList<Cloud> clouds;
    Random random;
    int score;
    int scoringCloud;
    Paint scorePaint;

    float multiplier;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        kirby = new Kirby();
        gameState = 0;
        clouds = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < AppConstants.numberOfClouds; i++) {
            int cloudX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenClouds;
            int topCloudOffsetY = AppConstants.minCloudOffsetY + random.nextInt(AppConstants.maxCloudOffsetY - AppConstants.minCloudOffsetY + 1);

            Cloud cloud = new Cloud(cloudX, topCloudOffsetY);
            clouds.add(cloud);
        }

        score = 0;
        scoringCloud = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);

        multiplier = 1;
    }

    public void updateAndDrawClouds(Canvas canvas) {
        if(gameState == 0 ) {
            AppConstants.getSoundBank().PlayCloudMusic();
            gameState = 1;
        }

        if (gameState == 1) {
            if ((clouds.get(scoringCloud).getCloudX()  < kirby.getKirbyX() + AppConstants.getBitmapBank().getKirbyWidth())
                    && (clouds.get(scoringCloud).getTopCloudOffsetY() > kirby.getKirbyY()
                    || clouds.get(scoringCloud).getBottomCloudY() < (kirby.getKirbyY()  + AppConstants.getBitmapBank().getKirbyHeight()))) {
                //Game Over
                gameState = 3;

                Log.d("Game", "Game Over");
                AppConstants.getSoundBank().StopCloudMusic();
                AppConstants.getSoundBank().PlayDeath();

            } else if (clouds.get(scoringCloud).getCloudX() < kirby.getKirbyX() - AppConstants.getBitmapBank().getCloudWidth()) {
                score++;
                scoringCloud++;
                if (scoringCloud > AppConstants.numberOfClouds - 1) {
                    scoringCloud = 0;
                }

                //Difficulty change
                if(AppConstants.distanceBetweenClouds > ((AppConstants.SCREEN_HEIGHT / 2) / 2)){
                    AppConstants.distanceBetweenClouds = AppConstants.distanceBetweenClouds - 50;
                }

                if(AppConstants.cloudVelocity < 20)
                    AppConstants.cloudVelocity += 1;
            }

        }

        if(gameState >= 1) {
            for (int i = 0; i < AppConstants.numberOfClouds; i++) {
                if (clouds.get(i).getCloudX() < -AppConstants.getBitmapBank().getCloudWidth()) {
                    clouds.get(i).setCloudX(clouds.get(i).getCloudX() + AppConstants.numberOfClouds * AppConstants.distanceBetweenClouds);
                    int topCloudOffsetY = AppConstants.minCloudOffsetY + random.nextInt(AppConstants.maxCloudOffsetY - AppConstants.minCloudOffsetY + 1);
                    clouds.get(i).setTopCloudOffsetY(topCloudOffsetY);
                }
                if (gameState < 2)
                    clouds.get(i).setCloudX((clouds.get(i).getCloudX() - AppConstants.cloudVelocity));
                canvas.drawBitmap(AppConstants.getBitmapBank().getCloudTop(), clouds.get(i).getCloudX(), clouds.get(i).getTopCloudY(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getCloudBottom(), clouds.get(i).getCloudX(), clouds.get(i).getBottomCloudY(), null);
            }
        }

        //Draw Score
        canvas.drawText("Score: " + score, 0, 110, scorePaint);
    }

    public void updateAndDrawBackgroundImage(Canvas canvas) {
        backgroundImage.SetX(backgroundImage.getX() - backgroundImage.getVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.SetX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    public void updateAndDrawKirby(Canvas canvas) {
        if (gameState == 1) {
            if (kirby.getKirbyY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getKirbyHeight()) || kirby.getVelocity() < 0) {
                kirby.setVelocity(kirby.getVelocity() + AppConstants.gravity);
                kirby.setKirbyY(kirby.getKirbyY() + kirby.getVelocity());
            }
        }

        if(gameState == 0 || gameState == 1){
            int currentFrame = kirby.getCurrentFrame();
            canvas.drawBitmap(AppConstants.getBitmapBank().getKirbyFly(currentFrame), kirby.getKirbyX(), kirby.getKirbyY(), null);
            currentFrame++;
            if (currentFrame > kirby.maxFrame)
                currentFrame = 0;

            kirby.setCurrentFrame(currentFrame);
        }

        Log.d("TAG", "barg");
        if(gameState >= 2) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getKirbyDeath(), kirby.getKirbyX(), kirby.getKirbyY(), null);

            //GO UP
            if(kirby.goUp){
                kirby.setKirbyY(kirby.getKirbyY() - (int)(40 * multiplier));
                multiplier -= 0.15;
                if(multiplier < 0.1) multiplier = 0.1f;
                kirby.distanceMovedUp += 40;
                if(kirby.distanceMovedUp > 500) kirby.goUp = false;
            }
            else{
                kirby.setKirbyY(kirby.getKirbyY() + (int)(40 * multiplier));
                multiplier += 0.1;
                if(multiplier > 2) multiplier = 2;
            }

            //GO DOWN
        }

    }
}
