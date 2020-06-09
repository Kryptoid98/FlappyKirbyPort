package com.example.flappykirbyport;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundBank {

    Context context;
    MediaPlayer fly, death, gameovermusic, cloudmusic;

    public SoundBank(Context context) {
        this.context = context;
        fly = MediaPlayer.create(context, R.raw.flap);
        death = MediaPlayer.create(context, R.raw.death);
        gameovermusic = MediaPlayer.create(context, R.raw.gameovermusic);
        cloudmusic = MediaPlayer.create(context, R.raw.cloudmusic);
    }

    public void PlayFly(){
        if(fly != null){
            fly.start();

        }
    }

    public void PlayDeath(){
        if(death != null){
            death.start();
        }
    }

    public void PlayGameOverMusic(){
        if(gameovermusic != null){
            gameovermusic.start();
        }
    }

    public void PlayCloudMusic(){
        if(cloudmusic != null){
            cloudmusic.setLooping(true);
            cloudmusic.start();
        }
    }

    public void StopCloudMusic(){
        if(cloudmusic != null){
            cloudmusic.stop();
        }
    }
}
