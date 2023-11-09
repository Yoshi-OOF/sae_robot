package EV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.EV3UltrasonicSensor;
 import lejos.hardware.motor.EV3MediumRegulatedMotor;
 import lejos.robotics.Color;
 import lejos.robotics.SampleProvider;
 import lejos.hardware.Audio;
 import lejos.hardware.BrickFinder;
 import lejos.hardware.Keys;
 import lejos.hardware.ev3.EV3;
 import lejos.hardware.lcd.TextLCD;

 public class Main {

     public static void main(String[] args) {
      
         int touche; 
 		      Robot20232024.AfficherUnmessageinst("Touche droite pour partir");  //boucle qui attend l'appuis de la touche droitte pour lancer le programme
 		      do{
      		 touche = Robot20232024.Attendre(); 
 	     	 }while(touche!=8);
      
         suiviligne();                                                      //Utilisation de la procédure suiviligne()

         // Soulever de poids
         int act = 0; 

         while (act < 3) {                                                  //boucle qui fait lever et baisser le bras 3 fois 
             Robot20232024.Tourner("A", 300, 120); 
             Motor.A.stop();
             Robot20232024.FaireUnePause(1000);
             Robot20232024.Tourner("A", 300, -120);
             act++;
         }

         // Direction etape suivante
         Robot20232024.Arreter();
         Robot20232024.FaireUneRotationADroite(80);
         Robot20232024.Tourner("A", 500, 200);
         Motor.A.stop();
         Motor.B.stop();
         Motor.C.stop();
         suiviligne();

         // Programme labyrinthe
         labyrinthe();
         Robot20232024.Arreter();

         // Programme slalom
         slalom();
         Robot20232024.Arreter();

         // Programme passage secret
         passagesecret();
         Robot20232024.Arreter();
         suiviligne();

         // Programme de fin
         fin();
     }

     public static void suiviligne() {
         EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);                   //préparation du capteur couleur
         SampleProvider light4 = sensor4.getMode("Red");
         double blanc = 0.5, color = 0; 
         float sample[] = new float[light4.sampleSize()];

         while (color < blanc) {                                                       //Tant que du blanc n'est pas rencontré, le robot suit la ligne noir
             light4.fetchSample(sample, 0);
             color = sample[0];
             if (color < 0.1) {
                 Robot20232024.AvancerMoteur(400, 250);
             } else {
                 Robot20232024.AvancerMoteur(250, 400);
             }

         }
         Motor.B.stop();
         Motor.C.stop();
         sensor4.close();
     }

     public static void labyrinthe() {
         EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);                   //préparation du capteur couleur
         SampleProvider light4 = sensor4.getMode("Red");
         double blanc = 0.4, color = 0;
         float sample[] = new float[light4.sampleSize()];

         EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);           //préparation du capteur distance
         float value[] = new float[1];
         float distance;
         distance = (float) 0.9;

         while (distance > 0.2) {
             light4.fetchSample(sample, 0);
             color = sample[0];
             sonar.getDistanceMode().fetchSample(value, 0);
             distance = value[0];

             if (color < 0.12) {
                 Motor.B.setSpeed(200);
                 Motor.C.setSpeed(300);                                                 //si detecte noir : va beaucoup à gauche
                 Motor.B.backward();
                 Motor.C.forward();
             } else if (color > blanc) {
                 Motor.B.setSpeed(400);
                 Motor.C.setSpeed(100);                                                 //si detecte blanc : va beaucoup à droite presque sur place
                 Motor.B.forward();
                 Motor.C.forward();
             } else {
                 Robot20232024.AvancerMoteur(350, 150);                                 //sinon : va presque tout droit (un peu à droite)
             }

         }
         Motor.B.stop();
         Motor.C.stop();
         sensor4.close();
         sonar.close();
     }

     public static void slalom() {

         EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);

         Robot20232024.AvancerMoteur(20, 20);

         float value[] = new float[1];
         float distance;
         distance = 9;

         while (distance > 0.19) {                                                       //avance jusqu'à croiser un mur
             sonar.getDistanceMode().fetchSample(value, 0);
             distance = value[0];
             Robot20232024.AvancerMoteur(300, 300);

         }
         Robot20232024.Tourner("C", 110, 200);

         Motor.C.stop();
         distance = 9;

         while (distance > 0.19) {                                                        //avance jusqu'à croiser un mur
             sonar.getDistanceMode().fetchSample(value, 0);
             distance = value[0];
             Robot20232024.AvancerMoteur(300, 300);

         }

         Robot20232024.Tourner("B", 110, 150);

         Motor.B.stop();
         distance = 9;

         while (distance > 0.19) {                                                          //avance jusqu'à croiser un mur
             sonar.getDistanceMode().fetchSample(value, 0);
             distance = value[0];
             Robot20232024.AvancerMoteur(300, 300);

         }

         Robot20232024.Tourner("B", 110, 150);

         Motor.B.stop();
         distance = 9;

         while (distance > 0.19) {                                                           //avance jusqu'à croiser un mur
             sonar.getDistanceMode().fetchSample(value, 0);
             distance = value[0];
             Robot20232024.AvancerMoteur(300, 300);
         }

         Robot20232024.Tourner("C", 110, 190);
         Motor.C.stop();

         sonar.close();
         Robot20232024.Arreter();
     }

     public static void passagesecret() {
         // TODO Auto-generated method stub
       
         EV3UltrasonicSensor sonar = new EV3UltrasonicSensor(SensorPort.S4);
         float value[] = new float[1];
         float distance;
         distance = 9;
         int i = 0;

         while (distance > 0.2) {                                                             //avance jusqu'à croiser un mur
             sonar.getDistanceMode().fetchSample(value, 0);
             distance = value[0];
             Robot20232024.AvancerMoteur(300, 300);
         }
         Motor.B.stop();
         Motor.C.stop();
         sonar.close();

         Robot20232024.FaireUnePause(3000);

         EV3TouchSensor touchSensor1 = new EV3TouchSensor(SensorPort.S1);
         SensorMode touch1 = touchSensor1.getTouchMode();
         float[] sample = new float[touch1.sampleSize()];

         Robot20232024.Reculer();
         do {
             touch1.fetchSample(sample, 0);
         } while (sample[0] == 0);
         Motor.B.stop();
         Motor.C.stop();
         touchSensor1.close();

         Robot20232024.FaireUnePause(2000);

         EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);
         SampleProvider light4 = sensor4.getMode("Red");
         double blanc = 0.5, color = 0;
         float sample1[] = new float[light4.sampleSize()];

         while (color < blanc) {
             light4.fetchSample(sample1, 0);
             color = sample1[0];
             Robot20232024.AvancerMoteur(350, 350);
         }

         Motor.B.stop();
         Motor.C.stop();
         sensor4.close();

         Robot20232024.FaireUneRotationADroite(90);
         Robot20232024.AvancerAvecVitesse(1000);
         Motor.B.stop();
         Motor.C.stop();
     }

     public static void fin() {
         Robot20232024.Arreter();
         Robot20232024.AfficherUnmessageinst("Fin de l'évasion");
         Robot20232024.FaireUnBip();
     }

 }
