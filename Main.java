package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.hardware.Audio; 
import lejos.hardware.BrickFinder; 
import lejos.hardware.Keys; 
import lejos.hardware.ev3.EV3; 
import lejos.hardware.lcd.TextLCD; 

public class Main {

	
	public static void main(String[] args) {
		suiviligne();
		
		// Soulever de poids
		int act=0;
		
		while (act<3) {
			Robot20232024.Tourner("A", 800, 120);
			Motor.A.stop();
			Robot20232024.FaireUnePause(1000);
			Robot20232024.Tourner("A", 800, -120);
			act++;
		}

		// Direction etape suivante
		Robot20232024.Arreter();
		Robot20232024.FaireUneRotationADroite(50);
		Robot20232024.Tourner("A", 800, 200);
		Robot20232024.Arreter();
		suiviligne();

		// Programme labyrinthe
		labyrinthe();
		Robot20232024.Arreter();
		
		// Programme slalom
		slalom();
		Robot20232024.Arreter();
		
		// Programme passage secret
		passagesecret();
		Robot20232024.Arreter();
		suiviligne();

		// Programme princesse
		fin();
		Robot20232024.Arreter();

	}

	public static void suiviligne() {
		EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);	
		SampleProvider light4= sensor4.getMode("Red");	
		double blanc = 0.5, color=0;
		float sample[] = new float[light4.sampleSize()];

		while (color < blanc) {
			light4.fetchSample(sample, 0);
			color = sample[0];	
			if (color<0.07) {
				Robot20232024.AvancerMoteur(350,250);
			}else
			{
				Robot20232024.AvancerMoteur(250,350);
			}
			
		}
		Motor.B.stop();
		Motor.C.stop();
		sensor4.close();
	}
	
	public static void labyrinthe() {
		EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);	
		SampleProvider light4= sensor4.getMode("Red");	
		double blanc = 0.4, color=0;
		float sample[] = new float[light4.sampleSize()];

		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		float value[] = new float [1];
		float distance;
		distance = (float)0.9;
		
		while (distance > 0.3) {
			light4.fetchSample(sample, 0);
			color = sample[0];	
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];

			if (color < 0.12) {
				Motor.B.setSpeed(200);
				Motor.C.setSpeed(300);   //detecte noir : va beaucoup à gauche
				Motor.B.backward();
				Motor.C.forward();
			}
			else if (color > blanc) {
				Motor.B.setSpeed(400);
				Motor.C.setSpeed(100);  //detecte blanc : va beaucoup à droite presque sur place
				Motor.B.forward();
				Motor.C.forward();
			}
			else{
				Robot20232024.AvancerMoteur(350,150);   //sinon : va presque tout droit (un peu à droite)
			}
			
		}
		Motor.B.stop();
		Motor.C.stop();
		sensor4.close();
		sonar.close();
	}
	
	public static void slalom() {
	    // TODO Auto-generated method stub

	    EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);

	    Robot20232024.AvancerMoteur(20, 20);

	    float value[] = new float[1];
	    float distance;
	    distance = 9;
	    int i = 0;

	    while (distance > 0.2) { //Première étape (tourne à droite)
	      sonar.getDistanceMode().fetchSample(value, 0);
	      distance = value[0];
	      Robot20232024.AvancerMoteur(300, 300);

	      if (distance < 0.2) {
	        Robot20232024.Tourner("C", 110, 180);

	      }
	    }

	    sonar.getDistanceMode().fetchSample(value, 0); //Réinitialise les capteurs à 0
	    distance = value[0];

	    while (distance > 0.2) { //Seconde étape (tourne à gauche)
	      sonar.getDistanceMode().fetchSample(value, 0);
	      distance = value[0];
	      Robot20232024.AvancerMoteur(300, 300);

	      if (distance < 0.2) {

	        Robot20232024.Tourner("B", 110, 180);
	      }
	    }

	    sonar.getDistanceMode().fetchSample(value, 0);
	    distance = value[0];

	    while (distance > 0.2) { //Troisième étape (tourne à droite)
	      sonar.getDistanceMode().fetchSample(value, 0);
	      distance = value[0];
	      Robot20232024.AvancerMoteur(300, 300);

	      if (distance < 0.2) {

	        Robot20232024.Tourner("C", 110, 180);
	      }
	    }

	    sonar.close();
	    Robot20232024.Arreter();
	}
	
	public static void passagesecret() {
		// TODO Auto-generated method stub
		//passer du programme de déplacement à celui là juste avant l'entrée du passage
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		float value[] = new float [1];
		float distance;
		distance = 9;
		int i = 0;
	
		while (distance > 0.2) {
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			Robot20232024.AvancerMoteur(300,300);
		}
		 
		sonar.close();
		
		EV3TouchSensor touchSensor1 = new EV3TouchSensor(SensorPort.S1);
		SensorMode touch1 = touchSensor1.getTouchMode();
		float[] sample = new float[touch1.sampleSize()];	
		
		Robot20232024.Reculer();
		do {
			touch1.fetchSample(sample, 0);						
		}while (sample[0] == 0);
		touchSensor1.close();
		
		Robot20232024.FaireUnePause(10);
		
		for (i = 0; i < 1500;i++) {
			Robot20232024.AvancerMoteur(500,500);
		}
		
		Robot20232024.FaireUnePause(10);
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		SensorMode color = colorSensor.getColorIDMode();
		float[] sample1 = new float[color.sampleSize()];
		String colorName = "NONE";	
		
		while (colorName != "BLACK") {
			color.fetchSample(sample1, 0);
			int colorId = (int)sample1[0];
			colorName=Robot20232024.Namecolor(colorId);
			Motor.C.rotate(1);
			Robot20232024.FaireUnePause(10);
			
		}
		Robot20232024.FaireUnePause(10);
		for (i= 0; i <1000; i++) {
			Robot20232024.AvancerMoteur(1000,1000);
		}
		
		colorSensor.close();
	}
	
	public static void fin() {
		  // objectif : idetifier les noms des couleurs et leur Id
		  EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		  //EV3MediumRegulatedMotor armMotor = new EV3MediumRegulatedMotor(MotorPort.A);

		  SensorMode color = colorSensor.getColorIDMode();
		  float[] sample = new float[color.sampleSize()];
		  color.fetchSample(sample, 0);
		  int colorId = (int) sample[0];
		  String colorName;
		  colorName = Robot20232024.Namecolor(colorId);
		  Robot20232024.AfficherUnmessage(colorId + " - " + colorName);
		  colorSensor.close();

		  if (colorId == 7) {
		    Robot20232024.AfficherUnmessage("Couleur noir");
		    for (int i = 1; i < 4; i++) {
		      Robot20232024.AvancerMoteur(500, 500);

		      Robot20232024.Arreter();

		    }
		    for (int j = 0; j < 3; j++) {
		      Robot20232024.Tourner("A", 50, 60);
		      Robot20232024.Tourner("A", 50, -60);
		    }
		    Robot20232024.JouerMusique(1);

		  } else if (colorId != 7) {
		    Robot20232024.AfficherUnmessage("Pas bonne couleur !");
		  }

		  Robot20232024.Arreter();
		  Robot20232024.AfficherUnmessage("Parcours terminé !");
	}

}
