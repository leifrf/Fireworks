import java.awt.Color;


/**
 * This is the parent class of all sparks and stars that make up a firework display.
 * The default values are set for starSparks, delaySparks, and launchSparks.
 * 
 * Many of the accessor methods are for referencing purposes, not used in calculation.
 * 
 * The current way of passing values from Star and Spark to Particle is clunky. Consider revising it.
 * 
 * Particle should be an abstract class. I will later re-write particle and its subclasses to make this happen.
 * 
 * The toString is universal for all Particles.
 * @author Leif Raptis-Firth
 *
 */
public class Particle implements ODESystem{

	private double[] positions = new double[2];			//Current x & y positions
	private double[] velocities = new double[2];		//Current x & y velocities
	private Color colour;								//Colour of the particle, currently a String, will be set to RGB
	private double creationTime;						//The time the particle was created
	private double mass;								//The mass of the particle. Constant for sparks, changing for stars.
	private double lifeTime;							//Lifetime of the particle. Calculated in Star, constant in Sparks.
	private double radius = 0;							//Radius of the particle. Calculated in Star, constant in Sparks.
	private double burnRate = 0;						//burnRate, calculated in the Star. Default is 0 so that sparks do not burn.
	private double density = 1;							//density of the particle. only applies to star.
	RungeKutta solver = new RungeKutta(this);			//Solver instantiated for the use of estimateVelocity().
	
	//Default constructor does nothing
	Particle(){}
	
	//Useless constructor, consider removing
	Particle(double positions){}
	
	/**
	 * Intended for use with Spark objects.
	 * 
	 * @param positions starting positions
	 * @param velocities starting velocities
	 * @param mass mass in kg
	 * @param radius radius in m
	 * @param lifeTime life time in s
	 * @param creationTime creation time in s
	 * @param colour colour as a string from NewColour
	 */
	Particle(double[] positions, double[] velocities, double mass, 
			double radius, double lifeTime, double creationTime, Color colour){
		this.positions = positions;
		this.velocities = velocities;
		this.mass = mass;
		this.radius = radius;
		this.lifeTime = lifeTime;
		this.creationTime = creationTime;
		this.colour = colour;		
	}
	
	/**
	 * Intended for use with Star objects.
	 * 
	 * @param positions starting positions
	 * @param velocities starting velocities
	 * @param mass starting mass in kg
	 * @param density density in kg/(m^3)
	 * @param burnRate burn rate in kg/s
	 * @param lifeTime life time in s
	 * @param creationTime creation time in s
	 * @param colour colour as a string from newColour
	 */
	Particle(double[] positions, double[] velocities, double mass, double burnRate, double density, 
			double lifeTime, double creationTime, Color colour){
		this.positions = positions;
		this.velocities = velocities;
		this.mass = mass;
		this.density = density;
		this.burnRate = burnRate;
		this.lifeTime = lifeTime;
		this.creationTime = creationTime;
		this.colour = colour;
		//values are being passed up here properly
	}
	
	@Override
	public int getSystemSize() {
		return 2;
	}
	
	/**
	 * returns velocities at timePassed = 0
	 */
	@Override
	public double[] getInitialVelocities(){
		return velocities;
	}
	
	/**
	 * sets velocitiest for timePassed = 0
	 * @param velocities initialVelocities
	 */
	public void setInitialVelocities(double[] velocities){
		this.velocities = velocities;
	}
	
	/**
	 * This is version 1 of updatePositions.
	 * Later versions will have to accommodate non-linear trajectories.
	 * 
	 * This method uses estimateVelocity in the RungeKutta ODE Solver.
	 * 
	 * This method may be more appropriate in the ParticleManager.
	 * 
	 * @param currentTime the current time in the ParticleManager
	 * @param timeInterval set in ParticleManager to 0.05s
	 * @param windVelocity in m/s, from the ParticleManager
	 */
	public void updatePositions(double currentTime, double timeInterval, double windVelocity){
		double timePassed = currentTime - creationTime;
		//System.out.println("Velocities before estimate: " + velocities[0] + " " + velocities[1]);
		//System.out.println("timePassed & timeInterval: " + timePassed + " " +timeInterval);
		velocities = solver.estimateVelocity(timePassed, timeInterval);
		//System.out.println("Velocities after estimate: " + velocities[0] + " " + velocities[1]);
		//System.out.println("Positions Before: " + positions[0] + " " + positions[1]);
		positions[0] = positions[0] + (velocities[0] + windVelocity) * timeInterval;
		positions[1] = positions[1] + velocities[1] * timeInterval;
		//System.out.println("Positions After: " + positions[0] + " " + positions[1]);
	}

	/**
	 * Sets positions at timePassed = 0;
	 * @param positions
	 */
	public void setInitialPositions(double[] positions){
		this.positions = positions;
	}
	
	/**
	 * 
	 * @return current positions
	 */
	public double[] getPositions(){
		return positions;
	}

	/**
	 * 
	 * @param mass mass at t = 0, in kg
	 */
	public void setStartingMass(double mass){
		this.mass = mass;
	}
	
	/**
	 * 
	 * @param time timePassed
	 * @return current mass, constant for sparks
	 */
	private double getMass(double timePassed){
		return mass - timePassed * burnRate; 
	}
	
	/**
	 * 
	 * @param time timePassed since creation
	 * @return new radius at the given time
	 */
	private double getRadius(double time){
		//System.out.println("Radius being passed through: " + radius);
		if (radius != 0)
			return radius;
		//System.out.println("Density being passed through: " + density);
		double volume = getMass(time) / density;
		return Math.pow(3 * volume / (4 * Math.PI), 1.0 / 3.0);
	}
	
	/**
	 * Uses getRadius
	 * 
	 * @param time timePassed since creation
	 * @return area
	 */
	private double getArea(double time){
		return Math.PI * getRadius(time) * getRadius(time);
	}
	
	/**
	 * 
	 * @param velocities current velocities
	 * @return velocity magnitude
	 */
	private double getVelocityMagnitude(double[] velocities){
		return Math.sqrt(velocities[0] * velocities[0] + velocities[1] * velocities[1]);
	}
	
	/**
	 * Uses getVelocityMagnitude, getArea.
	 * Uses DRAG_COEFFICIENT and FLUID_DENSITY from environment
	 * 
	 * @param time timePassed since creation
	 * @param velocities current velocities
	 * @return drag
	 */
	private double getDragForce(double time, double[] velocities){
		//System.out.println("getArea in getDragForce: " + getArea(time));
		return getVelocityMagnitude(velocities) * getArea(time)* 
				Environment.DRAG_COEFFICIENT * Environment.FLUID_DENSITY * 0.5;	
	}
	
	/**
	 * Calculates new velocities.
	 */
	public double[] getFunctions(double time, double[] velocities){
		//System.out.println("getFunctions velocities passed in: " + velocities[0] + velocities[1]);
		double[] derivatives = new double[2];
		double velocityMagnitude = getVelocityMagnitude(velocities);
		//System.out.println("velocityMagnitude in getFunctions: " + velocityMagnitude);
		double dragForce = getDragForce(time, velocities);
		//System.out.println("dragForce in getFunctions: " + dragForce);
		derivatives[0] = - (dragForce * velocities[0]) / (getMass(time) * velocityMagnitude);
		derivatives[1] = - Environment.GRAVITY - (dragForce * velocities[1]) / (getMass(time) * velocityMagnitude);
		//System.out.println("derivatives in getFunctions: " + derivatives[0] + " " + derivatives[1]);
		return derivatives;
	}

	/**
	 * 
	 * @param time this particle's lifeTime
	 */
	public void setLifeTime(double time){
		this.lifeTime = time;
	}
	
	/**
	 * 
	 * @return lifeTime of the particle
	 */
	public double getLifeTime(){
		return lifeTime;
	}
	
	/**
	 * This will remain constant for sparks. It changes based on the burnRate for Stars.
	 * 
	 * @param radius radius in m.
	 */
	public void setRadius(double radius){
		this.radius = radius;
	}
	
	/**
	 * 
	 * @param time the time the particle was created.
	 */
	public void setCreationTime(double time){
		this.creationTime = time;
	}
	
	/**
	 * 
	 * @return the time the spark was created
	 */
	public double getCreationTime(){
		return creationTime;
	}
	
	/**
	 * 
	 * @param burnRate burn rate in kg/s
	 */
	public void setBurnRate(double burnRate){
		this.burnRate = burnRate;
	}
	
	/**
	 * Only used for Star.
	 * @param density density in kg/m
	 */
	public void setDensity(double density){
		this.density = density;
	}	
	
	/**
	 * 
	 * @param colour String, one of the colours from NewColour
	 */
	public void setColour(Color colour){
		this.colour = colour;
	}
	
	/**
	 * 
	 * @return the particle's colour
	 */
	public Color getColour(){
		return colour;
	}

	/**
	 * returns a String with the particle's type, colour, and current position.
	 */
	public String toString(){
		return getClass().getName() +  " " + colour + " (" + positions[0] + ", " + positions[1] + ")"; 
	}

	/**
	 * @return returns a deep copy of the Particle.
	 */
	public  Particle clone(){
		return new Particle(positions, velocities, mass, burnRate, density, lifeTime, creationTime, colour);
	}
	
	/**
	 *  This is a placeholder value until I make this an abstract class
	 *  
	 *  
	 * @return 0, must be overriden in subclasses
	 */
	public int getRenderSize(){
		return 10;
	}
	
}