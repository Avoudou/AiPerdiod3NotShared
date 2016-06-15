package entities;

import com.badlogic.ashley.core.Entity;
import components.GravityForce;
import components.Mass;
import components.Velocity;

/**
 * ball entity
 * @author martin
 */
public class Ball extends Entity 
{
	/**
	 * @param radius a radius
	 * @param density a density constant
	 * @return the mass of a sphere of radius, density
	 */
	public static float mass (float radius, float density)
	{
		return (float) (4 * Math.PI * Math.pow (radius, 3) * density / 4);
	}
	
	/**
	 * constructs ball having:
	 * a mass,
	 * a velocity, 
	 * a force, 
	 * a gravity object
	 * @param radius radius of ball
	 * @param density density of ball's material
	 * @param gBuild gravity builder object \n
	 * note: the position needs to be set manually by adding a position component to this ball
	 */
	public Ball (float radius, float density, GravityForce.Builder gBuild)
	{
		float mass = mass (radius, density);
		add (new Mass (mass));
		add (new Velocity());
		add (gBuild.get (mass));
	}

}
