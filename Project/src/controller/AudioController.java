package controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioController {

    private Clip menuMusic;
    private Clip easyMusic;
    private Clip normalMusic;
    private Clip hardMusic;
    private Clip pointEarned;
    private Clip bomb;
    private Clip gameOver;

    public AudioController() throws LineUnavailableException {
        try {
            menuMusic = AudioSystem.getClip();
            menuMusic.open(AudioSystem.getAudioInputStream(new File("./audio/MainMenuMusic.wav").getAbsoluteFile()));
            easyMusic = AudioSystem.getClip();
            easyMusic.open(AudioSystem.getAudioInputStream(new File("./audio/EasyMusic.wav").getAbsoluteFile()));
            normalMusic = AudioSystem.getClip();
            normalMusic.open(AudioSystem.getAudioInputStream(new File("./audio/NormalMusic.wav").getAbsoluteFile()));
            hardMusic = AudioSystem.getClip();
            hardMusic.open(AudioSystem.getAudioInputStream(new File("./audio/HardMusic.wav").getAbsoluteFile()));
            pointEarned = AudioSystem.getClip();
            pointEarned.open(AudioSystem.getAudioInputStream(new File("./audio/PointEarned.wav").getAbsoluteFile()));
            bomb = AudioSystem.getClip();
            bomb.open(AudioSystem.getAudioInputStream(new File("./audio/Bomb.wav").getAbsoluteFile()));
            gameOver = AudioSystem.getClip();
            gameOver.open(AudioSystem.getAudioInputStream(new File("./audio/GameOver2.wav").getAbsoluteFile()));
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void playMenuTheme() {
        menuMusic.setFramePosition(0);
        menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playEasyMusic() {
        easyMusic.setFramePosition(0);
        easyMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playNormalMusic() {
        normalMusic.setFramePosition(0);
        normalMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playHardMusic() {
        hardMusic.setFramePosition(0);
        hardMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void playPointEarned() {
        pointEarned.setFramePosition(0);
        pointEarned.start();
    }

    public void playBomb() {
        bomb.setFramePosition(0);
        bomb.start();
    }

    public void playGameOver() {
        gameOver.setFramePosition(0);
        gameOver.start();
    }

    public void stopMenuTheme() {
        menuMusic.stop();
    }

    public void stopEasyMusic() {
        easyMusic.stop();
    }

    public void stopNormalMusic() {
        normalMusic.stop();
    }

    public void stopHardMusic() {
        hardMusic.stop();
    }

    public void stopPointEarned() {
        pointEarned.stop();
    }

    public void stopBomb() {
        bomb.stop();
    }

    public void stopGameOver() {
        gameOver.stop();
    }
    
}
