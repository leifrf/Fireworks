import java.awt.Color;
import java.util.ArrayList;

/**
 * General emitter used for starSparks, delaySparks, and launchSparks.
 * Only used for emitting sparks.
 * explosionSparkEmitter extends this class.
 * 
 * @author Leif Raptis-Firth
 *
 */
public class Emitter {
	
	private double[] positions = new double[2];
	private int sparkCount;
	private double maxExitVelocity;
	private double mass;
	private double radius;
	private double lifeTime;
	private double angle;
	private double angleVariation;
	private Color colour;						
	
	//Default constructor
	Emitter(){}
	
	/**
	 * 
	 * @param sparkCount number of sparks to be fired
	 * @param initialPositions beginning position
	 * @param angle angle in radians
	 * @param angleVariation angle variation in raidans
	 * @param maxExitVelocity maximum exit velocity
	 * @param mass mass in kg
	 * @param radius radius in m
	 * @param lifeTime time in s
	 */
	Emitter(int sparkCount, double[] initialPositions, double angle, double angleVariation, double maxExitVelocity, 
			double mass, double radius, double lifeTime){
		this.positions = initialPositions;
		this.sparkCount = sparkCount;
		this.maxExitVelocity = maxExitVelocity;
		this.mass = mass;
		this.radius = radius;
		this.lifeTime = lifeTime;
		this.angle = angle;
		this.angleVariation = angleVariation;
	}
	
	/**
	 * 
	 * @param angle angle in radians
	 * @param angleVariation angle variation in radians
	 * @param maxExitVelocity max exit velocity in m/s
	 * @return new velocities slightly altered from the original.
	 */
	public double[] varyVelocities(double angle, double angleVariation, double maxExitVelocity){
		double newAngle = angle - angleVariation + 2 * (angleVariation * Math.random());
		double[] velocities = new double[2];
		velocities[0] = Math.sin(newAngle) * maxExitVelocity;
		velocities[1] = Math.cos(newAngle) * maxExitVelocity;
		return velocities;
	}
		
	/**
	 * 
	 * @param colour sets the colour of the emitter which controls the colour of the sparks.
	 */
	public void setColour(Color colour){
		this.colour = colour;
	}
	
	/**
	 * 
	 * @param time creation time for the sparks
	 * @return an ArrayList<Spark> of countSpark
	 */
	public ArrayList<Spark> launch(double time){
		double positionX = positions[0];
		double positionY = positions[1];
		return launch(time, new double[] {positionX, positionY}, colour);
	}
	
	/**
	 * 
	 * @return x and y initial positions
	 */
	public double[] getPositions(){
		return positions;
	}

	/**
	 * 
	 * @param time creation time for the sparks
	 * @param positions starting positions for the sparks
	 * @param colour spark colour
	 * @return
	 */
	public ArrayList<Spark> launch(double time, double[] positions, Color colour){
		ArrayList<Spark> starSparkList = new ArrayList<>();
		for (int i = 0; i < sparkCount; i++){
			double positionX = positions[0];
			double positionY = positions[1];
			double[] variedVelocities = varyVelocities(angle, angleVariation, maxExitVelocity);
			starSparkList.add(new Spark(new double[] {positionX, positionY}, 
					variedVelocities, mass, radius, lifeTime, time, colour));
		}
		return starSparkList;
	}	
}
