package EV3;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;

public class Main {

    public static void main(String[] args) {
    		EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);	
    		SampleProvider light4= sensor4.getMode("Red");	
    		float sample4[] = new float[light4.sampleSize()];
    		
    		EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
    		float[] value = new float[1];	
    		float  xDistance=(float) 0.9;
    		float  x=(float) 0.2;

    		while (xDistance > 0.2) {
    			sonar.getDistanceMode().fetchSample(value, 0);
    			xDistance= value[0];
    			light4.fetchSample(sample4, 0);
    			x= sample4[0];	
    			if (x < 0.3) {		
	    			Robot20232024.AfficherUnmessageinst("debug color : " +x);
	    			Robot20232024.AvancerMoteur(350,250);
    			}else {
	    			Robot20232024.AvancerMoteur(250,350);
    			}
    		}
    		
    		sonar.close();
    		sensor4.close();
    		Robot20232024.Arreter();
    		Robot20232024.AfficherUnmessage("fin du suivi");
    		Robot20232024.FaireUnBip();
    }
}
