import java.awt.Color;


/**
 * launches sparks following the emission of the star.
 * these sparks go higher into the air and are faster than delaySparks
 * 
 * @author Leif Raptis-Firth
 *
 */
public class LaunchSparkEmitter extends Emitter{
	
	/**
	 * 
	 * @param sparkCount the number of sparks to be created each launch
	 * @param initialPositions the starting position of the sparks
	 * @param angle the angle in radians
	 * @param angleVariation the angle variability in radians
	 * @param maxExitVelocity the maximum exit velocity in m/s
	 * @param mass mass in kg, should be less than that of the star
	 * @param radius radius in m, should be less than that of the star
	 * @param lifeTime time in seconds, should be less than that of the star
	 */
	LaunchSparkEmitter(int sparkCount, double[] initialPositions, double angle, double angleVariation, double maxExitVelocity, 
			double mass, double radius, double lifeTime){
		super(sparkCount, initialPositions, angle, angleVariation, maxExitVelocity, mass, radius, lifeTime);
		setColour(Color.ORANGE);
	}
}
