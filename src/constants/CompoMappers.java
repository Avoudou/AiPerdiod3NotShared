package constants;

import com.badlogic.ashley.core.*;

import components.*;


/**
 * class storing component mappers
 */
public class CompoMappers 
{
	//static properties
	public static final ComponentMapper<Mass> MASS = ComponentMapper.getFor (Mass.class);
	//position
	public static final ComponentMapper<Position> POSITION = ComponentMapper.getFor (Position.class);
	//velocity
	public static final ComponentMapper<Velocity> VELOCITY = ComponentMapper.getFor (Velocity.class);
	//acceleration
	public static final ComponentMapper<Force> FORCE = ComponentMapper.getFor (Force.class);
	public static final ComponentMapper<GravityForce> GRAVITY_FORCE = ComponentMapper.getFor (GravityForce.class);
}
