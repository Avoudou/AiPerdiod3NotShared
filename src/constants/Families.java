package constants;

import com.badlogic.ashley.core.Family;
import components.Force;
import components.GravityForce;
import components.Mass;
import components.Position;
import components.Velocity;

/**
 * class storing family objects for categorization
 * @author martin
 */
public class Families 
{
	/** family object for moving objects, having a position and velocity component**/
	public static Family MOVING = Family.all (Position.class, Velocity.class).get();
	/** family object for moving objects which have an acceleration component**/
	public static Family ACCELERABLE  = Family.all (Position.class, Velocity.class, Force.class).get();
	/**family object for objects attracted by gravity, having a mass, force and gravity component**/
	public static Family GRAVITY_ATTRACTED = Family.all (Mass.class, Force.class, GravityForce.class).get();
	public static Family FRICTION = Family.all(Mass.class, Force.class, GravityForce.class).get();
}
