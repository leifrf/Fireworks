import java.awt.Color;


/**
 * Simple firework.
 * Shoots one star in a straight trajectory.
 * starSparks are the same colour as the star.
 * it has no explosion
 * 
 * @author Leif Raptis-Firth
 *
 */
public class RomanCandle extends Firework{
	
	private int numberOfStars = 8;		
	private double lengthOfTube = 0.2;
	private double angle;
	private double[] tubePosition;
	private double widthOfTube = 0.05;
	
	/**
	 * 
	 * @param angle angle in radians, given by user
	 */
	RomanCandle(double angle){	
		tubePosition = getTubeEndPosition(lengthOfTube, angle);
		this.angle = angle;
	}
	
	/**
	 * @return returns 8
	 */
	public int getNumberOfStars(){
		return numberOfStars;
	}
	
	/**
	 * @return returns 0.2
	 */
	public double getLengthOfTube(){
		return lengthOfTube;
	}	
	
	/**
	 * @return returns 0.05
	 */
	public double getWidthOfTube(){
		return widthOfTube;
	}
	
	/**
	 * tubePosition based on a tube length of 0.2m and the angle from the user.
	 * maxExitVelocity is 22m/s.
	 * angle from the user.
	 * angle variability is Math.toRadians(180)
	 */
	public LaunchTube getLaunchTube(){
		return new LaunchTube(tubePosition, 22, angle, Math.toRadians(3));
	}
	
	/**
	 * launches 20 sparks
	 * uses the position of the star
	 * uses the angle from the user
	 * angle variability is pi
	 * max exit velocity is 3m/s
	 * mass is 0.000002kg
	 * radius is 0.015m
	 * lifeSpan is 0.1s
	 * uses the colour of the star
	 * 
	 */
	public StarSparkEmitter getStarSparkEmitter(double[] starPosition, Color starColour){
		return new StarSparkEmitter(20, starPosition, angle, Math.toRadians(180), 3, 0.000002, 0.0015, 0.1, starColour);
	}
	
	/**
	 * launches 20 sparks
	 * tubePosition based on a tube length of 0.2m and the angle from the user.
	 * angle from the user.
	 * angle variability is Math.toRadians(3)
	 * max exit velocity is 20m/s
	 * mass is 0.000002kg
	 * radius is 0.0005m
	 * lifeSpan is 0.15s
	 */
	public LaunchSparkEmitter getLaunchSparkEmitter(){
		return new LaunchSparkEmitter(20, tubePosition, angle, Math.toRadians(3), 20, 0.000002, 0.0005, 0.15);
	}
	
	/**
	 * launches 5 sparks
	 * tubePosition based on a tube length of 0.2m and the angle from the user.
	 * angle from the user.
	 * angle variability is Math.toRadians(90)
	 * max exit velocity is 2.2m/s
	 * mass is 0.000002kg
	 * radius is 0.0015m
	 * lifeTime is 0.6s
	 */
	public DelaySparkEmitter getDelaySparkEmitter(){
		 return new DelaySparkEmitter(5, tubePosition, angle, Math.toRadians(90), 2.2, 0.000002, 0.0015, 0.6);
	}
	
	//fix later
	//make sure this has some kind of selection parameter for looping, bangs, etc
	public ExplosionSparkEmitter getExplosionSparkEmitter(double[] starPosition, Color starColour){
		return new ExplosionSparkEmitter();
	}
	
	
	
}