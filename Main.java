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

public class Main {

	
	public static void main(String[] args) {
		suiviligne();
		
		Robot20232024.Tourner("C", 900, 270);

		suiviligne();
		// avancer reculer moteur A
		while (true) {
			Robot20232024.AvancerMoteur(250, 250);
			Robot20232024.ReculerMoteur(250, 250);
		}

		Robot20232024.Arreter();

	}
	
	public static void suiviligne() {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		
		SensorMode color = colorSensor.getColorIDMode();
		float[] sample = new float[color.sampleSize()];
		String colorName;

		while (true) {
			color.fetchSample(sample, 0);
			int colorId = (int)sample[0];
			Robot20232024.AfficherUnmessageinst(colorId);

			colorName=Robot20232024.Namecolor(colorId);
			if (colorName.equals("BLACK")) {
				Robot20232024.AvancerMoteur(350,250);
				
			}else if (colorName.equals("WHITE"))
			{
				Motor.B.setSpeed(0);
				Motor.C.setSpeed(0);
				break;
			}else
			{
				Robot20232024.AvancerMoteur(250,350);
			}
			
		}
		
		colorSensor.close();
	}

}
