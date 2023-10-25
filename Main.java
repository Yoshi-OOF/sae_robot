package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
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

		int act=0;
		
		while (act<3) {
			Robot20232024.Tourner("A", 800, 120);
			Motor.A.stop();
			Robot20232024.FaireUnePause(1000);
			Robot20232024.Tourner("A", 800, -120);
			act++;
		}

		Robot20232024.Arreter();
		Robot20232024.FaireUneRotationADroite(60);
		Robot20232024.Arreter();
		
		// Programme labyrinthe
		labyrinthe.main(args);
		
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

}
