package systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import components.Velocity;

import constants.CompoMappers;
import constants.Families;


	public class FricsionSystem extends EntitySystem {
		public FricsionSystem() {
			// TODO Auto-generated constructor stub
		}

		public void addedToEngine(Engine e) {
			for (Entity add : e.getEntitiesFor(Families.FRICTION)) {
				entities().add(add);
			}
		}

		/**
		 * applies gravity to all entities being GRAVITY_ATTRACTED, thus adding gravity attraction to their force component
		 */
		public void update(float dTime) {
			for (Entity update : entities()) {

				Velocity v = CompoMappers.VELOCITY.get(update);
				v.scl(0.8f);

			}
		}

	}



