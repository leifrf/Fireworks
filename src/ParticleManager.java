import java.util.ArrayList;

/**
 * Manages one Firework object.
 * All of the stars stored in the firework's launchTube will be fired sequentially.
 * Once all particles from one star have died, the next star will be launched.
 * 
 * Each star launched results in launchSparks and delaySparks firing from the launchTube.
 * Sparks fire along the path of the star. When the star dies it emits a large explosion of starSparks. 
 * @author Leif Raptis-Firth
 *
 */
public class ParticleManager {

	private final double TIME_INTERVAL = 0.05;			//deltaTime, which is CONSTANT
	private double time = 0;							//current time, initially 0
	private double windVelocity;						//windVelocity from environment
	private int launchCount; 							//the number of fireworks launched at this time
	private Firework firework;							//the firework to be simulated
	private int numberOfStars;							//the number of star launches this firework has
	private ArrayList<Star> starList = new ArrayList<Star>();					//the starList from launchTube
	private ArrayList<Particle> particles = new ArrayList<Particle>();			//all sparks and stars, used in printing
	private ArrayList<Spark> sparkList = new ArrayList<Spark>();				//sparks from starSparkEmitters, delaySparkEmitters, launchSparkEmitters
	
	//explosionSpark will follow a different tragectory, potentially. I might need a different sparkList for it.
	
	//Emitters from the Firework input
	private LaunchTube launchTube;
	private StarSparkEmitter starSparkEmitter;
	private LaunchSparkEmitter launchSparkEmitter;
	private DelaySparkEmitter delaySparkEmitter;
	private ExplosionSparkEmitter explosionSparkEmitter;
	
	/**
	 * 
	 * @param windVelocity from an Environment object
	 * @param firework i.e. RomanCandle
	 */
	ParticleManager(double windVelocity, Firework firework){
		this.windVelocity = windVelocity;
		this.firework = firework;
		numberOfStars = firework.getNumberOfStars();
		
		launchTube = firework.getLaunchTube();
		launchSparkEmitter = firework.getLaunchSparkEmitter();
		delaySparkEmitter = firework.getDelaySparkEmitter();

	}

	//Check later to make sure this is not calculating one step ahead
	//Check that it's killing the spark on time.
	/**
	 * Updates the path of the spark until it dies. 
	 * @param sparkList sparks from all the sparkEmitters
	 */
	private void updateSparkList(ArrayList<Spark> sparkList){
		if(!sparkList.isEmpty()){
			for(int i = 0; i < sparkList.size();){
				Spark spark = sparkList.get(i);
				//Checking for expiration
				if(time - spark.getCreationTime() >= spark.getLifeTime())
					sparkList.remove(i);					
				else {
					spark.updatePositions(time, TIME_INTERVAL, windVelocity);
					i++;
				}
			}
		}
	}

	
	/**
	 * Updates the path of the star, emitting sparks as it does.
	 * When it dies, it emitts an explosion.
	 * @param starList stars from launchTube
	 */
	private void updateStarList(ArrayList<Star> starList){
		if(!starList.isEmpty()){
			for(int i = 0; i < starList.size(); i++){
				Star star = starList.get(i);
				//Checking for expiration 
				if(time - star.getCreationTime() + TIME_INTERVAL>= star.getLifeTime()){
					//Special sparks that may follow multiple tragectories
					explosionSparkEmitter = firework.getExplosionSparkEmitter(star.getPositions(), star.getColour());
					sparkList.addAll(explosionSparkEmitter.launch(time));
					starList.remove(i);
					//System.out.println("Killing star");
				}
				else {
					//System.out.println(time + " " + star.getCreationTime());
					star.updatePositions(time, TIME_INTERVAL, windVelocity);
					starSparkEmitter = firework.getStarSparkEmitter(star.getPositions(), star.getColour());
					sparkList.addAll(starSparkEmitter.launch(time));
				}

			}
		}
	}

	/**
	 * Getting the star and initial sparks from the emitters.
	 */
	private void launchFireworks(){
		starList.addAll(launchTube.launch(time, Firework.getRandomColour()));
		sparkList.addAll(launchSparkEmitter.launch(time));
		sparkList.addAll(delaySparkEmitter.launch(time));
	}
	
	/**
	 * Used for reference, not in these calculations.
	 * @return current time.
	 */
	public double getTime(){
		return time;
	}
	
	/**
	 * 
	 * @return returns the remaining stars to be fired. At 0, this Firework is at its end.
	 */
	public int getStarsRemaining(){
		return numberOfStars - launchCount;
	}
	
	/**
	 * Completes one time-step of TIME_INTERVAL, updating all particles.
	 */
	public void update(){
		
		//fires another star if the previous one is done
		if(sparkList.size() == 0 && launchCount < numberOfStars){
			launchFireworks();
			launchCount++;
		}
		updateStarList(starList);
		updateSparkList(sparkList);
		time += TIME_INTERVAL;
	}

	/**
	 * Prints the toString() of all currently existing particles.
	 */
	public void printCurrentParticleInformation(){
		particles.clear();
		particles.addAll(starList);
		particles.addAll(sparkList);
		for(Particle particle : particles)
			System.out.println(particle);
	}
	
	/**
	 * on hold while I write a clone method in particle
	 * @return
	 */
	public ArrayList<Particle> getParticles(){
		ArrayList<Particle> newList = new ArrayList<Particle>();
		particles.clear();
		particles.addAll(starList);
		particles.addAll(sparkList);
		for(Particle particle: particles)
			newList.add(particle.clone());
		return newList;
	}
	
}