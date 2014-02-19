
/**
 * Uses an ODESystem object.
 * Primary use is for firework simulation.
 * 
 * @author Leif Raptis-Firth
 *
 */
public class RungeKutta {

	private double time = 0;			
	private double timeInterval = 0.05;	
	private ODESystem ode;				//will always require an ode system
	
	/**
	 * 
	 * @param ode requires an object that extends the ODESystem interface, such as Firework
	 */
	RungeKutta(ODESystem ode) {
		this.ode = ode;
	}
	
	/**
	 * 
	 * @param timeInterval default timeInterval is 0.05s. The user can supply their own.
	 * @param ode requires an object that extends the ODESystem interface, such as Firework
	 */
	RungeKutta(double timeInterval, ODESystem ode){
		new RungeKutta(time, timeInterval, ode);
	}
	
	/**
	 * 
	 * @param time current time, defaulted to 0s.
	 * @param timeInterval default timeInterval is 0.05s.
	 * @param ode requires an object that extends the ODESystem interface, such as Firework
	 */
	RungeKutta(double time, double timeInterval, ODESystem ode){
		this.time = time;
		this.timeInterval = timeInterval;
		this.ode = ode;
	}
	
	/**
	 * 
	 * @return estimates velocity with default time = 0 and timeInterval = 0.05s.
	 */
	public double[] estimateVelocity(){
		return estimateVelocity(time, timeInterval);
	}
	
	/**
	 * 
	 * @param time time from the user
	 * @return estimates veloicty with default timeInterval = 0.05s.
	 */
	public double[] estimateVelocity(double time){
		return estimateVelocity(time, timeInterval);
	}
	
	/**
	 * 
	 * @param time current time
	 * @param timeInterval time difference
	 */
	public double[] estimateVelocity(double time, double timeInterval) {
		//System.out.println("Estimating Velocities");
		int systemSize = ode.getSystemSize();
		double[] q1, q2, q3, q4;										
		double[] intermediateValues = new double[systemSize];	//Values used only in iteration to calculate q arrays.
		double halfTime = time + timeInterval / 2;
		double fullTime = time + timeInterval;
		double[] newVals = new double[systemSize];
		double[] values = ode.getInitialVelocities();
		
		//System.out.println("ode.getInitialVelocities(): " + values[0] + " " + values[1]);
		
		//Calculation iterates based on the size of the system
		q1 = ode.getFunctions(fullTime, values);
		
		//System.out.println("q1: " + q1[0] + " " + q1[1]);
		
		for (int valNum = 0; valNum < ode.getSystemSize(); valNum++)
			intermediateValues[valNum] = values[valNum] + timeInterval * q1[valNum] / 2;
		q2 = ode.getFunctions(halfTime, intermediateValues);
		
		
		for (int valNum = 0; valNum < ode.getSystemSize(); valNum++)
			intermediateValues[valNum] = values[valNum] + timeInterval * q1[valNum] / 2;
		q3 = ode.getFunctions(halfTime, intermediateValues);
		
		
		for (int valNum = 0; valNum < ode.getSystemSize(); valNum++)
			intermediateValues[valNum] = values[valNum] + timeInterval * q1[valNum];
		q4 = ode.getFunctions(fullTime, intermediateValues);
		
		//Final calculation
		for (int i = 0; i < systemSize; i++)			
			newVals[i] = values[i] + timeInterval * (q1[i] + 2 * q2[i] + 2 * q3[i] + q4[i]) / 6;
		
		return newVals;
	} // end estimateVelocity
	
}