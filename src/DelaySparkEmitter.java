import java.awt.Color;


/**
 * Launches sparks from the end of the tube.
 * Fires one set from when the star begins.
 * 
 * @author Leif Raptis-Firth
 *
 */
public class DelaySparkEmitter extends Emitter{
	
	DelaySparkEmitter(int sparkCount, double[] initialPositions, double angle, double angleVariation, double maxExitVelocity, 
			double mass, double radius, double lifeTime){
		super(sparkCount, initialPositions, angle, angleVariation, maxExitVelocity, mass, radius, lifeTime);
		setColour(Color.YELLOW);
	}
	}
