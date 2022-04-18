

import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
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
    PointLight pLight;
    SpotLight sLight;
    SpotLight sLight2;

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
    private BranchGroup createSceneGraph(TransformGroup vtg) {
        BranchGroup root = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(2);
        //object
        ap = new Appearance();
        Material mat = new Material();
        mat.setDiffuseColor(100,0,0);
        ap.setMaterial(mat);

        Sphere shape = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Transform3D tr = new Transform3D();
        tr.setScale(0.25);
        TransformGroup tg = new TransformGroup(tr);
        tg.addChild(shape);
        root.addChild(tg);
        //view rotator

        // background and lights
        Background background = new Background(0.5f, 0.5f, 0.5f);
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
