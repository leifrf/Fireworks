import java.awt.Color;


/**
 * One of two types of particles, the other being star.
 * Generic sparks are emitted as launchSparks, delaySparks, and starSparks.
 * ExplosionSpark extends Spark.
 * 
 * Defaults:
 * colour = "ORANGE"
 * mass = 0.000002kg
 * radius = 0.0015m
 * 
 * @author Leif Raptis-Firth
 *
 */
public class Spark extends Particle{
	
	Spark(){}
	
	//lowest parameterized constructor
	/**
	 * 
	 * @param positions initial positions
	 * @param velocities initial velocities
	 * @param creationTime time the spark was created
	 * @param lifeTime life time of the spark
	 */
	Spark(double[] positions, double[] velocities, double creationTime, double lifeTime){
		this(positions, velocities, 0.000002, 0.0015, lifeTime, creationTime, Color.ORANGE);
	}
	
	/**
	 * 
	 * @param positions initial positions
	 * @param velocities initial velocities
	 * @param creationTime time the spark was created
	 * @param lifeTime life time of the spark
	 */
	Spark(double[] positions, double[] velocities, double creationTime, double lifeTime, Color colour){
		this(positions, velocities, 0.000002, 0.0015, lifeTime, creationTime, colour);
	}
	
	/**
	 * 
	 * @param positions initial positions
	 * @param velocities initial velocities
	 * @param creationTime time the spark was created
	 * @param lifeTime life time of the spark
	 */
	Spark(double[] positions, double[] velocities, double mass, double radius, 
			double lifeTime, double creationTime, Color colour){
		super(positions, velocities, mass, radius, lifeTime, creationTime, colour);
	}
	
	/**
	 * 
	 * @return the rendering size for paintComponent
	 */
	public int getRenderSize(){
		return 2;
	}
	
}
