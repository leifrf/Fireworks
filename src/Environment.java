
/**
 * Environment object, gets windVelocity from the user.
 * Will also take rain parameters at a later date.
 * Wind must be more than -15m/s and less than 15m/s in the x dimension.
 * @author Leif Raptis-Firth
 *
 */
public class Environment {
	
	public static final double GRAVITY = 9.807;					//m/(s^2), referenced by multiple methods
	public static final double FLUID_DENSITY = 1.2;				//fluid density, referred to in dragForce methods
	public static final double DRAG_COEFFICIENT = 0.4;			//drag coefficient, referred to in dragForce methods
	
	private double windVelocity;								//given by the user, in m/s

	/**
	 * Will be altered later to accept rain parameters.
	 * This will likely also necessitate overloading constructors.
	 * 
	 * @param windVelocity windVelocity from the user in m/s
	 */
	Environment(double windVelocity){
		this.windVelocity = windVelocity;
	}
		
	/**
	 * 
	 * @return windVelocity in m/s
	 */
	public double getWindVelocity(){
		return windVelocity / 3.6;
	}
	

}