package Visuals;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public abstract class RenderAbstr implements ApplicationListener {
	public Environment lights;
	public PerspectiveCamera cam;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;

	@Override
	public void create() {
		// lights = new Environment();
		// lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		// lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		//
		// modelBatch = new ModelBatch();
		//
		// cam = new PerspectiveCamera(100, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// cam.position.set(10f, 10f, 10f);
		// cam.lookAt(0, 0, 0);
		// cam.near = 1f;
		// cam.far = 300f;
		// cam.update();
		//
		// ModelBuilder modelBuilder = new ModelBuilder();
		// model = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.GREEN)),
		// Usage.Position | Usage.Normal);
		// instance = new ModelInstance(model);
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(instance, lights);
		modelBatch.end();
	}

	@Override
	public void dispose() {
		// modelBatch.dispose();
		// model.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}