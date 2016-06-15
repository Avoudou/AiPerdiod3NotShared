package systems;

import java.util.*;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;

import constants.CompoMappers;
import constants.Families;

import components.Position;
import components.Velocity;

/**
 * entity system applying movement to every entity
 * @author martin
 */
public class Movement extends EntitySystem
{	
	/**
	 * default constructor
	 */
	public Movement()
	{
		
	}
	
	/**
	 * @param e engine to which this was added
	 * adds all moveable entities of e to this system
	 * is automatically called by engine
	 */
	public void addedToEngine (Engine e)
	{
		for (Entity ent : e.getEntitiesFor (Families.MOVING))
			entities().add (ent);
	}
	
	/**
	 * action happening on update of engine
	 * moves all entities proportionally to the time elapsed
	 */
	public void update (float dTime)
	{
		for (Entity move : entities())
		{
			Position p = CompoMappers.POSITION.get (move);
			Velocity v = CompoMappers.VELOCITY.get (move);
			
			p.mulAdd (v, dTime);
		}
	}
}
