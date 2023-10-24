package EV3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.Color;

public class Main {
	public static void suiviligne() {
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S3);
		SensorMode color = colorSensor.getColorIDMode();
		float[] sample = new float[color.sampleSize()];
		String colorName;
		
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		float[] value = new float[1];	
		float  xDistance=(float) 0.9;
		float  x=(float) 0.2;

		while (xDistance > 0.2) {
			sonar.getDistanceMode().fetchSample(value, 0);
			xDistance= value[0];
			color.fetchSample(sample, 0);
			int colorId = (int)sample[0];
			colorName=Robot20232024.Namecolor(colorId);

			if (colorName == "BLACK") {
    			Robot20232024.AfficherUnmessageinst("debug color : " +colorName);
				Robot20232024.AvancerMoteur(350,250);
			}else if (colorName=="WHITE")    			{
    			break;
			}else
			{
				Robot20232024.AfficherUnmessageinst("debug color : " +colorName);
				Robot20232024.AvancerMoteur(250,350);
			}
			
		}
		
		sonar.close();
		colorSensor.close();
		Robot20232024.Arreter();
		Robot20232024.AfficherUnmessage("fin du programme");
		Robot20232024.FaireUnBip();
	}
	
	public static void main(String[] args) {
		
	}

}
