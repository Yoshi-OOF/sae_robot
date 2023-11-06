package EV3;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import java.util.concurrent.TimeUnit;



public class slalom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		
		
		

		Robot20232024.AvancerMoteur(20, 20);
		
		float value[] = new float [1];
		float distance;
		distance = 9;
		
		
		
		while (distance > 0.17) {      
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			Robot20232024.AvancerMoteur(300,300);
				
		}
		Robot20232024.Tourner("C", 110, 160);
		
		Motor.C.stop(); 
		distance = 9;
	
		while (distance > 0.17) {  
				sonar.getDistanceMode().fetchSample(value, 0);
				distance = value[0];
				Robot20232024.AvancerMoteur(300,300);

			} 
		
		Robot20232024.Tourner("B", 110, 160);
		
		
		
		
		Motor.B.stop(); 
		distance = 9;
		
		while (distance > 0.17) {  
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			Robot20232024.AvancerMoteur(300,300);

		} 
		
		Robot20232024.Tourner("B", 110, 160);
		
		Motor.B.stop(); 
		distance = 9;
		
		while (distance > 0.17) {  
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			Robot20232024.AvancerMoteur(300,300);
		} 
		
		Robot20232024.Tourner("C", 110, 160);
		Motor.C.stop(); 
		
			
			

		sonar.close();
		Robot20232024.Arreter();
	

	}
}
