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

public class Main {

	
	public static void main(String[] args) {
		suiviligne();
		
		Robot20232024.Tourner("C", 900, 270);

		suiviligne();

		int act=0;

		while (act<5) {
			Robot20232024.Tourner("A", 500, 60);
			Robot20232024.Tourner("A", 500, 0);
			act++;
		}

		
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

			if (color>0.06) {
				Robot20232024.AvancerMoteur(350,250);
			}else
			{
				Robot20232024.AvancerMoteur(250,350);
			}
			
		}
		Motor.B.setSpeed(0);
		Motor.C.setSpeed(0);
		sensor4.close();
	}

}
