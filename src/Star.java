import java.awt.Color;


/**
 * Star object used as part of a Firework.
 * 
 * The constructors for this object are complex, making for difficult use.
 * Consider simplifying them or breaking them into sets.
 *
 * Defaults:
 * mass = 0.008kg
 * burnRate = 0.003kg/s
 * density = 900kg/(m^3)
 * 
 * @author Leif Raptis-Firth
 *
 */
public class Star extends Particle{
		
	/**
	 * 
	 * @return rendering size of 6 for paintComponent
	 */
	public int getRenderSize(){
		return 6;
	}
	
	/**
	 * 
	 * @param positions initial star positions
	 * @param velocities initial velocities
	 * @param burnRate burn rate in kg/s
	 * @param mass mass in kg
	 * @param density density in kg/(m^3)
	 * @param creationTime time of creation in s
	 * @param colour colour as a string from NewColour
	 * 
	 * lifeTime and radius are calculated in Particle.
	 */
	Star(double[] positions, double[] velocities, double mass, double burnRate, double density, 
			double creationTime, Color colour){
		super(positions, velocities, mass, burnRate, density, mass/burnRate, creationTime, colour);		
		//values are being passed through here properly
	}
		
	/**
	 * 
	 * @param positions initial star positions
	 * @param velocities initial velocities
	 * @param creationTime time of creation in s
	 * @param colour colour as a string from NewColour
	 * 
	 * lifeTime and radius are calculated in Particle.
	 */
	Star(double[] positions, double[] velocities, double creationTime, Color colour){
		this(positions, velocities, 0.008, 0.003, 1900, creationTime, colour);
	}

}