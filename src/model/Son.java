package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

public class Son {
	
	/**
     * Joue le son.
	 * @throws MalformedURLException 
     */
    public static void jouer(String son) throws MalformedURLException {
    	AudioClip monSon = Applet.newAudioClip(new URL("data/son/pas.wav"));
    }
    
    
    
public static void main(String[] args) throws MalformedURLException {
	Son.jouer("a");
}
}
