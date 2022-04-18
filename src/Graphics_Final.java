

import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import java.applet.*;
import com.sun.j3d.utils.applet.MainFrame;

public class Graphics_Final extends Applet implements ItemListener {
    public static void main(String[] args) {
        new MainFrame(new Graphics_Final(), 640, 480);
    }

    AmbientLight aLight;
    DirectionalLight dLight;
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
        menu.setLayout(new GridLayout(1,4));
        add(menu, BorderLayout.SOUTH);
        Checkbox mi = new Checkbox("Ambient", true);
        menu.add(mi);
        mi.addItemListener(this);
        mi = new Checkbox("Directional", true);
        menu.add(mi);
        mi.addItemListener(this);
        mi = new Checkbox("Point", true);
        menu.add(mi);
        mi.addItemListener(this);
        mi = new Checkbox("Spot", true);
        menu.add(mi);
        mi.addItemListener(this);

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
        //object
        ap = new Appearance();
        ap.setMaterial(new Material());
        Sphere shape = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, ap);
        root.addChild(shape);
        //view rotator
        Alpha alpha = new Alpha(-1, 4000);
        RotationInterpolator rotator = new RotationInterpolator(alpha, vtg);
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(2);
        rotator.setSchedulingBounds(bounds);
        root.addChild(rotator);
        // background and lights
        Background background = new Background(0.5f, 0.5f, 0.5f);
        background.setApplicationBounds(bounds);
        root.addChild(background);
        aLight = new AmbientLight(true, new Color3f(Color.WHITE));
        aLight.setInfluencingBounds(bounds);
        aLight.setCapability(PointLight.ALLOW_STATE_WRITE | PointLight.ALLOW_STATE_WRITE);
        root.addChild(aLight);
        dLight = new DirectionalLight(new Color3f(Color.WHITE), new Vector3f(1f,1f,0f));
        dLight.setCapability(PointLight.ALLOW_STATE_WRITE | PointLight.ALLOW_STATE_WRITE);
        dLight.setInfluencingBounds(bounds);
        root.addChild(dLight);


        return root;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        Checkbox cmi = (Checkbox)itemEvent.getSource();
        String label = cmi.getLabel();
        boolean state = cmi.getState();
        if("Ambient".equals(label)) {
            aLight.setEnable(state);
        } else if ("Directional".equals(label)) {
            dLight.setEnable(state);
        } else if ("Point".equals(label)) {
            pLight.setEnable(state);
        } else if ("Spot".equals(label)) {
            sLight.setEnable(state);
            sLight2.setEnable(state);
        }
        cmi.setState(state);
    }
}
