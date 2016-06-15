package Visuals;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Golf3D extends RenderAbstr {
	
	private List<ModelInstance> instances;
	

	private int dimZ = 0;
	private int dimX = 0;
	private int dimY = 0;

	private CameraInputController camController;

	public Golf3D() 
	{

		dimX = 300;
		dimZ = 10;
		dimY = 800;

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
		cam.position.set(0, 300, 0);
		cam.lookAt(0, 0, 0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		camController = new CameraInputController(cam);
		camController.forwardTarget = true;
		Gdx.input.setInputProcessor(camController);

		ModelBuilder modelBuilder = new ModelBuilder();

		List<Model> models = new ArrayList<Model>();
		instances = new ArrayList<ModelInstance>();
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		
		Model terrain= new Model();
		Model ball = new Model();
		drawField(modelBuilder, terrain, 0, 0, 0);
		drawBall(modelBuilder, ball, 50, 10, 50);
		
	

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


	private void drawField(ModelBuilder modelBuilder, Model model, float x, float y, float d) {
		MeshPartBuilder builder;
		modelBuilder.begin();
		Material mat = new Material();
		mat.set(new ColorAttribute(ColorAttribute.Diffuse, .2f, 0.9f, .2f, 1f));
		// mat.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, 0.4f));
		builder = modelBuilder.part("grid", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, mat);
		builder.box(x, y, d, dimX, dimZ, dimY);

		instances.add(new ModelInstance(modelBuilder.end()));
	}

	private void drawBall(ModelBuilder modelBuilder, Model model, float x, float y, float d) {
		MeshPartBuilder builder;
		modelBuilder.begin();
		Material mat = new Material();
		mat.set(new ColorAttribute(ColorAttribute.Diffuse, .9f, 0.9f, .9f, 1f));
		// mat.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, 0.4f));
		builder = modelBuilder.part("grid", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal, mat);
		builder.box(x, y, d, 10, 10, 10);
		instances.add(new ModelInstance(modelBuilder.end()));
	}

	public void moveBall() {
		// instances.get(1).transform.set(10, 10, 10);// translate(new Vector3(0, 0, 2));
	}

	class CustomKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
				
				
			moveBall();
			}

			}

		// need for no exceptions
		public void keyReleased(KeyEvent e) {

		}
	}

	

	
}


