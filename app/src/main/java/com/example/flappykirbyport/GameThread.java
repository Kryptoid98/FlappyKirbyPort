package com.example.flappykirbyport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.flappykirbyport.AppConstants;

public class GameThread extends Thread {

    SurfaceHolder surfaceHolder; //surface object reference
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33; //delay in milliseconds

    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    public void run(){
        while(isRunning){
            startTime = SystemClock.uptimeMillis();
            //Lock the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null){
                synchronized (surfaceHolder){
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawClouds(canvas);
                    AppConstants.getGameEngine().updateAndDrawKirby(canvas);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            loopTime = SystemClock.uptimeMillis() - startTime;

            if(AppConstants.getGameEngine().gameState == 3){
                if(!AppConstants.getSoundBank().death.isPlaying()) {
                    //try{
                    //Thread.sleep(3500);
                    //}catch(InterruptedException e){
                    //Log.e("Interupted", "Interupted while sleeping");
                    //}
                    AppConstants.getGameEngine().updateAndDrawKirby(canvas);
                    Context context = AppConstants.gameActivityContext;
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("score", AppConstants.getGameEngine().score);
                    context.startActivity(intent);
                    ((Activity) context).finish();

                    AppConstants.getSoundBank().PlayGameOverMusic();
                    AppConstants.getGameEngine().gameState = 5;
                }
            }

            if(loopTime < DELAY){
                try{
                    Thread.sleep(DELAY - loopTime);
                }catch(InterruptedException e){
                    Log.e("Interupted", "Interupted while sleeping");
                }
            }
        }
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setIsRunning(boolean state){
        isRunning = state;

    }
}
