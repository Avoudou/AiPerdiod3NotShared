package Visuals;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Golf3D extends RenderAbstr {
	
	private List<ModelInstance> instances;
	
	private int GRID_MIN = 0;
	private int GRID_MAX_X = 0;
	private int GRID_MAX_Y = 0;
	private int GRID_MAX_Z = 0;

	private CameraInputController camController;

	public Golf3D() 
	{
		
		GRID_MAX_Y = 600;
		GRID_MAX_X = 600;
		GRID_MAX_Z = 10;
	}

	@Override
	public void create() 
	{
		Gdx.graphics.setDisplayMode(800, 700, false);
		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		modelBatch = new ModelBatch();

		cam = new PerspectiveCamera(50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(30f, 40f, -40f);
		cam.lookAt(-10, 5, 30);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		camController.forwardTarget = true;
		Gdx.input.setInputProcessor(camController);

		ModelBuilder modelBuilder = new ModelBuilder();

		List<Model> models = new ArrayList<Model>();
		instances = new ArrayList<ModelInstance>();
		// Gdx.gl.glEnable(GL20.GL_BLEND);
		
		
		Model spaceBoarder= new Model();
		drawField(modelBuilder, spaceBoarder, GRID_MAX_Y / 2f, GRID_MAX_X / 2f, GRID_MAX_Z / 2f, Color.GREEN);
		
	

	}
	@Override
	public void render() 
	{
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		modelBatch.begin(cam);
		for (ModelInstance instance : instances) 
		{
			modelBatch.render(instance, lights);
		}
		modelBatch.end();
	}

	@SuppressWarnings("unused")
	private void drawField(ModelBuilder modelBuilder, Model model, float x, float y, float d, Color Color) {
		MeshPartBuilder builder;
		modelBuilder.begin();
		Material mat = new Material();
		mat.set(new ColorAttribute(ColorAttribute.Diffuse, .8f, 0.4f, .4f, 1f));
		mat.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, 0.4f));
		builder = modelBuilder.part("grid", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, mat);
		builder.box(x, y, d, GRID_MAX_Y, GRID_MAX_X, GRID_MAX_Z);

		instances.add(new ModelInstance(modelBuilder.end()));
	}
	

	

	
}


