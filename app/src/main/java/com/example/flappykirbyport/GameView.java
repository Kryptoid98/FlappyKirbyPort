package com.example.flappykirbyport;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;

    public GameView(Context context) {
        super(context);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(!gameThread.isRunning()){
            gameThread = new GameThread(surfaceHolder);
            gameThread.start();
        }else{
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if(gameThread.isRunning){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry){
                try{
                    gameThread.join();
                    retry = false;
                }catch(InterruptedException e){}
            }
        }
    }

    void initView(){
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //Tap is detected
        if(action == MotionEvent.ACTION_DOWN && AppConstants.getGameEngine().gameState < 2){
            if(AppConstants.getGameEngine().gameState == 0) {
                AppConstants.getGameEngine().gameState = 1;
            }

            AppConstants.getSoundBank().PlayFly();

            AppConstants.getGameEngine().gameState = 1;
            AppConstants.getGameEngine().kirby.setVelocity(AppConstants.jumpVelocity);
        }

        return true;
    }
}
