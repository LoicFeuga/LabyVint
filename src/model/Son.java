package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;



public class Son  extends Thread {
	private static boolean needToStop = false;
	private static Thread playWave;
	private static boolean  inPlaying = false;
	private String filename;
    private Position curPosition;
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
    enum Position {LEFT, RIGHT, NORMAL};
 
    public Son(String wavfile) {
        filename = wavfile;
        curPosition = Position.NORMAL;
    }
 
    public void run() {
        File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("Wave file not found: " + filename);
            return;
        }
 
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
            return;
        } catch (IOException e1) {
            e1.printStackTrace();
            return;
        }
 
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
 
        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) {
                pan.setValue(1.0f);
            } else if (curPosition == Position.LEFT) {
                pan.setValue(-1.0f);
            }
        }
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
 
        try {
        	
            while (nBytesRead != -1 && needToStop == false) {
            	if(needToStop == true) return;
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {

            inPlaying = false;
            auline.drain();
            auline.close();
        }
    }
    
    public static void playPas(){
    	needToStop = false;
    	if(inPlaying == false){
    		inPlaying = true;
    		 playWave= new Son("C:\\Users\\user\\_Java\\_Projects\\Devint\\LabyVint\\data\\son\\pas.wav");
    		playWave.start();
    		
    	}
    	
    }
    
    public static void stopToPlay(){
    	needToStop =true;

    }
    @SuppressWarnings("deprecation")
	public static void playPom(){
    	needToStop = false;
    	if(inPlaying == true){
    		inPlaying = false;
    		playWave.stop();
    		 playWave= new Son("C:\\Users\\user\\_Java\\_Projects\\Devint\\LabyVint\\data\\son\\pom.wav");
    		playWave.start();
    		
    	}
    	
    }
 
}
 