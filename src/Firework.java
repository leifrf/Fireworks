import java.awt.Color;


/**
 * Parent class for all fireworks, i.e. Roman Candle.
 * starSparks, delaySparks, and launchSparks are emitted for every star.
 * Most, but not all stars have an explosionSparkEmitter.
 * 
 * Different fireworks may have different tube lengths, thus different tube end positions.
 * @author Leif Raptis-Firth
 *
 */
public abstract class Firework {
	
	private static int colourMin = 25;
	private static int colourMax = 200;

	/**
	 * 
	 * @return the number of stars stored in this firework.
	 */
	public abstract int getNumberOfStars();
	
	/**
	 * 
	 * @return the length of the launch tube
	 */
	public abstract double getLengthOfTube();
	
	/**
	 * Only used in the painting process.
	 * 
	 * @return the width of the tube in m.
	 */
	public abstract double getWidthOfTube();
	
	/**
	 * generator method for the firework's launch tube
	 * @return launchTube, which emits the star
	 */
	public abstract LaunchTube getLaunchTube();
	
	/**
	 * 
	 * @param starPosition the position of the star when starSparks are to be fired
	 * @param starColour the colour of the star
	 * @return a spark emitter at the star's position
	 */
	public abstract StarSparkEmitter getStarSparkEmitter(double[] starPosition, Color starColour);
	
	/**
	 * 
	 * @return delaySparkEmitter, always firing from the end of the tube
	 */
	public abstract DelaySparkEmitter getDelaySparkEmitter();
	
	/**
	 * 
	 * @return launchSparkEmitter, always firing faster than delaySparkEmitter and from the end of the tube
	 */
	public abstract LaunchSparkEmitter getLaunchSparkEmitter();
	
	/**
	 * 
	 * @param starPosition position where the star ends
	 * @param starColour explosion based on the star's colour
	 * @return launches a magnificent explosion of explosionSparks at the point the star died
	 */
	public abstract ExplosionSparkEmitter getExplosionSparkEmitter(double[] starPosition, Color starColour);
	
	/**
	 * 
	 * @param lengthOfTube the length of the launch tube
	 * @param angle angle of the launch tube
	 * @return x and y positions of the end of the tube
	 */
	public double[] getTubeEndPosition(double lengthOfTube, double angle){
		double[] positions = new double[2];
		positions[0] = lengthOfTube * Math.asin(angle);	//adjusts position based on the angle of the tube
		positions[1] = lengthOfTube * Math.acos(angle);	//adjusts position based on the angle of the tube
		return positions;
	}

	/**
	 * returns all the parameters of the emitters used in the firework.
	 */
	public String getFireworkInformation;
	
	public static Color getRandomColour(){
		return new Color((int)(colourMin + (int)(Math.random() * ((colourMax - colourMin) + 1))), 
						 (int)(colourMin + (int)(Math.random() * ((colourMax - colourMin) + 1))),
						 (int)(colourMin + (int)(Math.random() * ((colourMax - colourMin) + 1))));
	}
}
