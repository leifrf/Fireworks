import java.awt.Color;
import java.util.ArrayList;

/**
 * Emitter for star objects.
 * Unlike sparks, stars set burnRate and density which causes mass to decrease over time.
 * 
 * @author Leif Raptis-Firth
 *
 */
public class LaunchTube extends Emitter{
	
	//variables
	private double[] positions;
	private double angle;
	private double maxExitVelocity;
	private double angleVariation;
	
	/**
	 * 
	 * @param positions end of the launchTube is where the stars begin
	 * @param maxExitVelocity maximum exit velocity in m/s
	 * @param angle angle in radians
	 * @param angleVariation angle variation in radians
	 */
	LaunchTube(double[] positions, double maxExitVelocity, double angle, double angleVariation){
		this.positions = positions;
		this.maxExitVelocity = maxExitVelocity;
		this.angle = angle;
		this.angleVariation = angleVariation;
	}
	
	/**
	 * This method is used for reference purposes, not for calculations.
	 * 
	 * @return x and y position
	 */
	public double[] getPosition(){
		return new double[] {positions[0], positions[1]};
	}
	
	/**
	 * This method is used mostly for referencing, but may be used in calculation.
	 * 
	 * @return max exit velocity
	 */
	public double getMaxExitVelocity(){
		return maxExitVelocity;
	}

	/**
	 * Currently this method launches a single star.
	 * This may be altered later to accommodate more complex fireworks.
	 * 
	 * @param time creaitonTime for the stars
	 * @param colour colour for the stars
	 * @return stars with varying initial velocities
	 */
	public ArrayList<Star> launch(double time, Color colour){
		ArrayList<Star> starList = new ArrayList<>();
		double[] newVelocities = varyVelocities(angle, angleVariation, maxExitVelocity);
		double positionX = positions[0];
		double positionY = positions[1];
		//System.out.println("launch in LaunchTube: " + newVelocities[0] + " " + newVelocities[1]);
		starList.add(new Star(new double[] {positionX, positionY}, newVelocities, time, colour));
		return starList;
	}
}
