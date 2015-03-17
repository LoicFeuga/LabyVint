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
	
	@Test
	public void getPosition(){
		assertEquals(new Point(15,15),o1.getPosition());
		assertEquals(new Point(35,25),o2.getPosition());
		assertEquals(new Point(25,20),o3.getPosition());
	}
	
	@Test
	public void getNbTotal(){
		assertEquals(3,o1.getNbTotalObjet());
		ObjetCollision o1 = new ObjetCollision();
		assertEquals(4,o1.getNbTotalObjet());
		ObjetCollision o2 = new ObjetCollision();
		assertEquals(5,o1.getNbTotalObjet());
		ObjetCollision o3 = new ObjetCollision();
		assertEquals(6,o1.getNbTotalObjet());
		o1.supprimer();
		assertEquals(5,o1.getNbTotalObjet());
		o2.supprimer();
		assertEquals(4,o1.getNbTotalObjet());
		o3.supprimer();
		assertEquals(3,o1.getNbTotalObjet());
	}
	

}
