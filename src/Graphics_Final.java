import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import java.applet.*;
import com.sun.j3d.utils.applet.MainFrame;

public class Graphics_Final extends Applet {

    public static void main(String[] args) {
        new MainFrame(new Graphics_Final(), 640, 480);
    }

    AmbientLight aLight;
    DirectionalLight dLight;
    DirectionalLight dLight2;

    public void init() {
        // create canvas
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);

        Panel menu = new Panel();

        SimpleUniverse su = new SimpleUniverse(cv, 2);
        su.getViewingPlatform().setNominalViewingTransform();
        BranchGroup bg = createSceneGraph(su.getViewingPlatform().
                getMultiTransformGroup().getTransformGroup(0));
        bg.compile();
        su.addBranchGraph(bg);
    }

    Appearance ap;
    Appearance bb;
    private BranchGroup createSceneGraph(TransformGroup vtg) {
        BranchGroup root = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(2);
        //object
        ap = new Appearance();
        bb = new Appearance();
        Material grey = new Material();
        Material mat = new Material();
        grey.setDiffuseColor(.5f,.5f,.5f);
        mat.setDiffuseColor(100,0,0);
        ap.setMaterial(mat);

        Transform3D tr = new Transform3D();
        tr.setScale(0.25);

        TransformGroup tg = new TransformGroup(tr);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        Sphere shape = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, ap);
        tg.addChild(shape);
        bb.setMaterial(grey);
        Cylinder cy = new Cylinder(.1f,1,bb);
        tg.setTransform(tr);
        tg.addChild(cy);

        root.addChild(tg);

        //view rotator
        MouseRotate rotater = new MouseRotate(tg);
        rotater.setSchedulingBounds(bounds);
        tg.addChild(rotater);

        MouseTranslate translator = new MouseTranslate(tg);
        translator.setSchedulingBounds(bounds);
        tg.addChild(translator);


        // background and lights
        Background background = new Background(0f, 0f, 0f);
        background.setApplicationBounds(bounds);
        root.addChild(background);

        aLight = new AmbientLight(true, new Color3f(Color.WHITE));
        aLight.setInfluencingBounds(bounds);
        root.addChild(aLight);
        dLight = new DirectionalLight(new Color3f(Color.WHITE), new Vector3f(7f,10f,-55));
        dLight.setInfluencingBounds(bounds);
        dLight2 = new DirectionalLight(new Color3f(Color.WHITE), new Vector3f(20,-1,-5f));
        dLight.setInfluencingBounds(bounds);
        root.addChild(dLight);




        return root;
    }
}
