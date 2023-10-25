package EV3;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class labyrinthe {

	public static void main(String[] args) {
		
		EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);	
		SampleProvider light4= sensor4.getMode("Red");	
		double blanc = 0.4, color=0;
		float sample[] = new float[light4.sampleSize()];

		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
		float value[] = new float [1];
		float distance;
		distance = (float)0.9;
		
		int touche;
		Robot20232024.AfficherUnmessageinst("Touche droite pour partir");
		do{
		touche = Robot20232024.Attendre();
		}while(touche!=8);
		
		while (distance > 0.1) {
			light4.fetchSample(sample, 0);
			color = sample[0];	
			sonar.getDistanceMode().fetchSample(value, 0);
			distance = value[0];

			if (color < 0.12) {
				Motor.B.setSpeed(100);
				Motor.C.setSpeed(400);   //detecte noir : va beaucoup à gauche
				Motor.B.backward();
				Motor.C.forward();
			}
			else if (color > blanc) {
				Motor.B.setSpeed(400);
				Motor.C.setSpeed(100);  //detecte blanc : va beaucoup à droite presque sur place
				Motor.B.forward();
				Motor.C.forward();
			}
			else{
				Robot20232024.AvancerMoteur(300,200);   //sinon : va presque tout droit (un peu à droite)
			}
			
		}
		Motor.B.stop();
		Motor.C.stop();
		sensor4.close();
		sonar.close();
	}

}
