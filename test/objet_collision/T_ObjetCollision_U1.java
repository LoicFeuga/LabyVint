package objet_collision;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import model.objet.ObjetCollision;

import org.junit.*;


public class T_ObjetCollision_U1 {
	ObjetCollision o1;
	ObjetCollision o2;
	ObjetCollision o3;
	Rectangle r1;
	Rectangle r2;
	Rectangle r3;
	
	@Before 
	public void init(){
		o1 = new ObjetCollision();
		o2 = new ObjetCollision();
		o3 = new ObjetCollision();
		r1 = new Rectangle(new Point(10,10),new Dimension(10,10));
		r2 = new Rectangle(new Point(30,20),new Dimension(10,10));
		r3 = new Rectangle(new Point(15,15),new Dimension(20,10));
		o1.setHitBox(r1);
		o2.setHitBox(r2);
		o3.setHitBox(r3);
	}
	
	@Test
	public void isTouche(){
		assertTrue(o1.isTouch(o3.getHitBox()));
		assertTrue(o1.isTouch(o3));
		assertTrue(!o1.isTouch(o2.getHitBox()));
		assertTrue(!o1.isTouch(o2));
		assertTrue(o2.isTouch(o3.getHitBox()));
		assertTrue(o2.isTouch(o3));
		
		assertTrue(o3.isTouch(o1.getHitBox()));
		assertTrue(o3.isTouch(o1));
		assertTrue(!o2.isTouch(o1.getHitBox()));
		assertTrue(!o2.isTouch(o1));
		assertTrue(o3.isTouch(o2.getHitBox()));
		assertTrue(o3.isTouch(o2));
	}
	
	

}
