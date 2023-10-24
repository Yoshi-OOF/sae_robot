package EV3;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
public class musculation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		EV3MediumRegulatedMotor armMotor = new EV3MediumRegulatedMotor(MotorPort.A);
		 
		
		
        for (int i = 0; i<4; i++) {
        armMotor.setSpeed(180);
        armMotor.rotate(90);
        armMotor.setSpeed(-180);
        armMotor.rotate(-90);
        }armMotor.close();
        }
		
		
        
        
        
		
		
	


}

