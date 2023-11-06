
package EV3;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;

public class passageSecret {

	public static void main(String[] args) throws InterruptedException {
		//passer du programme de déplacement à celui là juste avant l'entrée du passage
				EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
				float value[] = new float [1];
				float distance;
				distance = 9;
				int i = 0;
				
				int touche;
				Robot20232024.AfficherUnmessageinst("Touche droite pour partir");
				do{
				touche = Robot20232024.Attendre();
				}while(touche!=8);
				
				while (distance > 0.2) {
					sonar.getDistanceMode().fetchSample(value, 0);
					distance = value[0];
					Robot20232024.AvancerMoteur(300,300);
				}
				 
				sonar.close();
				
				Motor.B.stop();
				Motor.C.stop();
				Robot20232024.FaireUnePause(3000);
				
				EV3TouchSensor touchSensor1 = new EV3TouchSensor(SensorPort.S1);
				SensorMode touch1 = touchSensor1.getTouchMode();
				float[] sample = new float[touch1.sampleSize()];	
				
				Robot20232024.Reculer();
				do {
					touch1.fetchSample(sample, 0);						
				}while (sample[0] == 0);
				touchSensor1.close();
				
				Motor.B.stop();
				Motor.C.stop();
				Robot20232024.FaireUnePause(2000);
				
				for (i = 0; i < 1000;i++) {
					Robot20232024.AvancerMoteur(500,500);
				}
				
				Motor.B.stop();
				Motor.C.stop();
				Robot20232024.FaireUnePause(2000);
				
				Robot20232024.FaireUneRotationADroite(50);
				
				Robot20232024.Arreter();
		
	}
}
