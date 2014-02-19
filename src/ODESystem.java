
/**
 * Any object moving through the air.
 * The methods are used to calculate its path.
 * Any object that is to be used with a RungeKutta solver must extend this interface.
 * 
 * @author Leif Raptis-Firth
 *
 */
public interface ODESystem {

	/**
	 * 
	 * @return the system size, 2D or 3D
	 */
	int getSystemSize();
	
	/**
	 * 
	 * @param timePassed timePassed since the object's flight began
	 * @param velocities velocities from the previous time-step
	 * @return new velocities
	 */
	double[] getFunctions(double timePassed, double[] velocities);
	
	/**
	 * 
	 * @return velocities at the current time step
	 */
	double[] getInitialVelocities();
	
}
