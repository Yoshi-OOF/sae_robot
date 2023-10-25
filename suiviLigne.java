	public static void suiviligne() {
		EV3ColorSensor sensor4 = new EV3ColorSensor(SensorPort.S3);	
		SampleProvider light4= sensor4.getMode("Red");	
		double blanc = 0.5, color=0;
		float sample[] = new float[light4.sampleSize()];

		while (color < blanc) {
			light4.fetchSample(sample, 0);
			color = sample[0];	
			Robot20232024.AfficherUnmessageinst(Double.toString(color));
			if (color<0.07) {
				Robot20232024.AvancerMoteur(350,250);
			}else
			{
				Robot20232024.AvancerMoteur(250,350);
			}
			
		}
		Motor.B.stop();
		Motor.C.stop();
		sensor4.close();
	}
