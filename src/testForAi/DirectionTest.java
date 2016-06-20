package testForAi;

import java.util.ArrayList;

import aiExtention.ActionGeneatorV2;
import aiExtention.ActionGeneratorV3;

import com.badlogic.gdx.math.Vector3;

public class DirectionTest {

	public static void main(String[] args) {
		ActionGeneratorV3 generator = new ActionGeneratorV3(new Vector3(1,0,0),3);
		ArrayList<Vector3> myList = generator.getForceData();
		System.out.println(myList.size());
		for (int i = 0; i < myList.size(); i++) {
			System.out.println(myList.get(i));
			System.out.println(myList.get(i).len());

	}

}
}
