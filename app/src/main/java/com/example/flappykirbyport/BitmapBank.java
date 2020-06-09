package com.example.flappykirbyport;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    Bitmap background;
    Bitmap[] kirby_fly;
    Bitmap kirby_death;
    Bitmap cloudTop, cloudBottom;

    public BitmapBank(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.cloud_bg);
        background = scaleImage(background);
        kirby_fly = new Bitmap[2];
        kirby_fly[0] = BitmapFactory.decodeResource(res, R.drawable.kirbyfly_one);
        kirby_fly[1] = BitmapFactory.decodeResource(res, R.drawable.kirbyfly_two);
        kirby_death = BitmapFactory.decodeResource(res, R.drawable.kirby_death);
        cloudTop = BitmapFactory.decodeResource(res, R.drawable.cloud);
        cloudBottom = BitmapFactory.decodeResource(res, R.drawable.cloud);
    }

    public int getCloudHeight(){
        return cloudBottom.getHeight();
    }

    public int getCloudWidth(){
        return cloudBottom.getWidth();
    }

    public Bitmap getCloudTop(){
        return cloudTop;
    }

    public Bitmap getCloudBottom(){
        return cloudBottom;
    }


    public Bitmap getKirbyFly(int frame){
        return kirby_fly[frame];
    }

    public int getKirbyWidth(){
        return kirby_fly[0].getWidth();
    }

    public int getKirbyHeight(){
        return kirby_fly[0].getHeight();
    }

    public Bitmap getKirbyDeath(){
        return kirby_death;
    }

    //Return background bitmap
    public Bitmap getBackground(){
        return background;
    }

    //return background width
    public int getBackgroundWidth(){
        return background.getWidth();
    }

    //Return background height
    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();

        int backgroundScaledWidth = (int)widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
