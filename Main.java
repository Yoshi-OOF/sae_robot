package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.Color;

public class Main {
	static EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
	static EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
	
	public static void main(String[] args) {
		suiviligne();
		
		Robot20232024.Tourner("C", 800, 270);
		suiviligne();

		Robot20232024.Arreter();
		colorSensor.close();
		sonar.close();
	}
	
	public static void suiviligne() {
		SensorMode color = colorSensor.getColorIDMode();
		float[] sample = new float[color.sampleSize()];
		String colorName;
		
		float[] value = new float[1];	
		float  xDistance=(float) 0.9;

		while (xDistance > 0.2) {
			sonar.getDistanceMode().fetchSample(value, 0);
			xDistance= value[0];
			color.fetchSample(sample, 0);
			int colorId = (int)sample[0];
			colorName=Robot20232024.Namecolor(colorId);

			if (colorName == "BLACK") {
    			Robot20232024.AfficherUnmessageinst("Color : " +colorName);
				Robot20232024.AvancerMoteur(350,250);
			}else if (colorName=="WHITE")  
			{
				Robot20232024.AvancerMoteur(0,0);
				Robot20232024.Arreter();
				break;
			}else
			{
				Robot20232024.AfficherUnmessageinst("Color : " +colorName);
				Robot20232024.AvancerMoteur(250,350);
			}
			
		}
	}

}
