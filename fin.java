package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.hardware.port.MotorPort;

public class fin {
	public static void main(String[] args){
		// objectif : idetifier les noms des couleurs et leur Id
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		//EV3MediumRegulatedMotor armMotor = new EV3MediumRegulatedMotor(MotorPort.A);
		
		
		SensorMode color = colorSensor.getColorIDMode();
		float[] sample = new float[color.sampleSize()];
		color.fetchSample(sample, 0);
		int colorId = (int)sample[0];
		String colorName;
		colorName=Robot20232024.Namecolor(colorId);
		Robot20232024.AfficherUnmessage(colorId + " - " + colorName);
		colorSensor.close();
		
		
		
		if(colorId == 7) {
			Robot20232024.AfficherUnmessage("Couleur noir");
			for (int i = 1; i<4; i++) {
				Robot20232024.AvancerMoteur(500,500);
				
				Robot20232024.Arreter();
				
				
			} for (int j= 0; j<3; j++) {
				Robot20232024.Tourner("A", 50, 60);
				Robot20232024.Tourner("A", 50, -60);
		        }Robot20232024.JouerMusique(1);
			
		} else if (colorId != 7) {
			Robot20232024.AfficherUnmessage("Pas bonne couleur !");
		}
		
		Robot20232024.Arreter();
		Robot20232024.AfficherUnmessage("Parcours terminé !");
	}
}
