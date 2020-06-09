package com.example.flappykirbyport;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class AppConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int jumpVelocity;
    static int gapBetweenTopAndBotClouds;
    static int numberOfClouds;
    static int cloudVelocity;
    static int minCloudOffsetY;
    static int maxCloudOffsetY;
    static int distanceBetweenClouds;

    static SoundBank soundBank;
    static Context gameActivityContext;

    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine = new GameEngine();
        soundBank = new SoundBank(context);
    }

    public static SoundBank getSoundBank(){
        return soundBank;
    }

    public static void setGameConstants(){
        AppConstants.gravity = 3;
        AppConstants.jumpVelocity = -40;

        AppConstants.numberOfClouds = 2;
        AppConstants.cloudVelocity = 12;
        AppConstants.minCloudOffsetY = (int)(AppConstants.gapBetweenTopAndBotClouds / 2.0);
        AppConstants.maxCloudOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minCloudOffsetY - AppConstants.gapBetweenTopAndBotClouds;
        AppConstants.distanceBetweenClouds = AppConstants.SCREEN_HEIGHT * 3 / 4;
    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context. getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;
        AppConstants.SCREEN_HEIGHT = height;
        AppConstants.gapBetweenTopAndBotClouds = 600; //randomize this
    }
}
