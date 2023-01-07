package common.scene;

import common.camera.Camera;
import common.graphicsObject.AbstractGraphicsObject;
import common.light.Light;

import java.util.ArrayList;
import java.util.Collections;

public class SceneWithALight extends Scene {
    public SceneWithALight(Camera camera, Light light, AbstractGraphicsObject... graphicsObjects) {
        super(camera, SceneWithALight.concatenate(light, graphicsObjects));
    }

    private static ArrayList<AbstractGraphicsObject> concatenate(AbstractGraphicsObject item, AbstractGraphicsObject... collection) {
        ArrayList<AbstractGraphicsObject> newCollection = new ArrayList<AbstractGraphicsObject>(collection.length + 1);
        newCollection.add(item);
        Collections.addAll(newCollection, collection);
        return newCollection;
    }
}
