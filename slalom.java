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
		int i = 0;
		
		
		while (distance > 0.2) {      //Première étape (tourne à droite)
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			Robot20232024.AvancerMoteur(300,300);
			
			if (distance < 0.2) {
				Robot20232024.Tourner("C", 110, 180);
				
				
		}
	}
		
		sonar.getDistanceMode().fetchSample(value, 0); //Réinitialise les capteurs à 0
		distance = value[0];
	
			while (distance > 0.2) {  //Seconde étape (tourne à gauche)
					sonar.getDistanceMode().fetchSample(value, 0);
					distance = value[0];
					Robot20232024.AvancerMoteur(300,300);
					
					if (distance < 0.2) {
						
						
						Robot20232024.Tourner("B", 110, 180);
					}
				} 
			
			
			
			
			
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			
			while (distance > 0.2) {  //Troisième étape (tourne à droite)
				sonar.getDistanceMode().fetchSample(value, 0);
				distance = value[0];
				Robot20232024.AvancerMoteur(300,300);
				
				if (distance < 0.2) {
					
					
					Robot20232024.Tourner("C", 110, 180);
				}
			} 
				
				

		sonar.close();
		Robot20232024.Arreter();
	

	}
}
