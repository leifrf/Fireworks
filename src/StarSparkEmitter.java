import java.awt.Color;


public class StarSparkEmitter extends Emitter{

	//angle & angleVariation not converted here
	StarSparkEmitter(int sparkCount, double[] initialPositions, double angle, double angleVariation, double maxExitVelocity, 
			double mass, double radius, double lifeTime, Color colour){
		super(sparkCount, initialPositions, angle, angleVariation, maxExitVelocity, mass, radius, lifeTime);
		setColour(colour);
		}
}