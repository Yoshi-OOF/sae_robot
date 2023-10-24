
package EV3;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import java.util.concurrent.TimeUnit;

public class passageSecret {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//passer du programme de déplacement à celui là juste avant l'entrée du passage
		EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);	
		SampleProvider light4= sensor4.getMode("Red");	
		float sample4[] = new float[light4.sampleSize()];
		
		EV3TouchSensor touchSensor1 = new EV3TouchSensor(SensorPort.S1);
		SensorMode touch1 = touchSensor1.getTouchMode();
		float[] sample = new float[touch1.sampleSize()];	
		
		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		float value[] = new float [1];
		float distance;
		distance = 9;
		int i = 0;
		
		int touche;
		Robot20232024.AfficherUnmessage("Touche droite pour partir");
		do{
		touche = Robot20232024.Attendre();
		}while(touche!=8);
		
		 while (true) {
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];
			if (distance < 0.2) {
				break;
			}
			Robot20232024.AvancerMoteur(400,400);
		}
		TimeUnit.SECONDS.sleep(3);
		
		Robot20232024.Reculer1();
		do {
			touch1.fetchSample(sample, 0);						
		}while (sample[0] == 0);
		touchSensor1.close();
		
		TimeUnit.SECONDS.sleep(3);
		
		for (i = 0; i < 1000;i++) {
			Robot20232024.AvancerMoteur(700,700);
		}
		
		TimeUnit.SECONDS.sleep(1);
		
		float x =(float) 0.2;
		while (x > 0.06) {
			light4.fetchSample(sample4, 0);
			x= sample4[0];			
			Motor.C.rotate(1);
			TimeUnit.SECONDS.sleep(1);
			
		}
		TimeUnit.SECONDS.sleep(5);
		for (i= 0; i <1000; i++) {
			Robot20232024.AvancerMoteur(1000,1000);
		}
		
		sensor4.close();
		sonar.close();
		
	}

}
