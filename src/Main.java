import java.util.ArrayList;

public class Main {

	static double angle = Math.toRadians(10);
	static Environment env = new Environment(10);
	static double wind = env.getWindVelocity();
	
	public static void main(String[] args){
		
		/*
		double[] positions = {0,0};
		double[] velocities = {5,10};
		double creationTime = 0;
		String colour = "BLUE";
		Star star = new Star(positions, velocities, creationTime, colour);
		RungeKutta testSolver = new RungeKutta(star);
		*/
		//fullTest();
		
		intervalTest();
		
		
		
		//tubeTest();
		
		//starSparkEmitterTest();
		
		//starTest();
	}
	
	private static void tubeTest(){
		double windVelocity = 10 / 3.6;
		double timeInterval = 0.05;
		LaunchTube testTube = new LaunchTube(new double[] {0, 0}, 22, Math.toRadians(0), Math.toRadians(5));
		ArrayList<Star> starList = testTube.launch(0, "BLUE");
		Star testStar = starList.get(0);
		//System.out.println("getInitialVelocities called in Main: " + testStar.getInitialVelocities()[0] + " " + testStar.getInitialVelocities()[1]);
		
		double time = 0;
		while(time < testStar.getLifeTime() - timeInterval){
			System.out.println(testStar + " " + time);
			testStar.updatePositions(time,  timeInterval, windVelocity);
			time+= timeInterval;
		}
		
	}
	
	
	
	private static void starSparkEmitterTest(){
		double windVelocity = 10;
		double timeInterval = 0.05;
		StarSparkEmitter testEmitter = new StarSparkEmitter(20, new double[] {10,10}, Math.toRadians(10), 0, 5, 0.000002, 0.015, 0.6, "BLUE");
		ArrayList<Spark> sparkList = testEmitter.launch(0);
		for (Spark spark: sparkList){
			double time = 0;
			System.out.println(spark);
			spark.updatePositions(time, timeInterval, windVelocity);
		}
		int j = 0;
		for (Spark spark: sparkList){
			System.out.println(j + " " + spark);
			j++;
		}
		double time = 0;
		int i;
		/*
		while(time < sparkList.get(0).getLifeTime()){
			i = 0;
			for(Spark spark: sparkList){
				i++;
				System.out.println(time + " " + i + " " + spark);
				spark.updatePositions(time, timeInterval, windVelocity);
			}	
			time+= timeInterval;
		}
		*/
	}

	
	private static void intervalTest(){
		RomanCandle romanCandle = new RomanCandle(angle);
		ParticleManager manager = new ParticleManager(wind, romanCandle);
		
		for(double i = 0; i < 20; i+= 0.05){
			System.out.println(manager.getTime());
			manager.printCurrentParticleInformation();
			manager.update();
		}
		
	}
	
	
	private static void fullTest(){
		RomanCandle romanCandle = new RomanCandle(angle);
		ParticleManager manager = new ParticleManager(wind, romanCandle);
		
		for(int i = 0; i < 5; i+=0.05){
			manager.update();
			manager.printStarInformation();
		}

		
	}

}
