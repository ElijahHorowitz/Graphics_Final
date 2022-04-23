import java.awt.*;
import java.applet.*;

import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.applet.MainFrame;

public class FirstPage extends Applet {
    public static void main(String s[]) {
        System.setProperty("sun.awt.noerasebackground", "true");
        new MainFrame(new FirstPage(), 640, 480);
    }

    public void init() {
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);
        BranchGroup bg = createSceneGraph();
        bg.compile();
        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        su.addBranchGraph(bg);
    }

    private BranchGroup createSceneGraph() {
        BranchGroup root = new BranchGroup();

        Appearance ap = new Appearance();
        ap.setMaterial(new Material());
        Font3D font = new Font3D(new Font("SansSerif", Font.PLAIN, 1),
                new FontExtrusion());
        Text3D text = new Text3D(font, "Learning Molecules");
        Text3D text2 = new Text3D(font, "Instructions:");
        Text3D text3 = new Text3D(font, "Right Click to move forward");
        Text3D text4 = new Text3D(font, "Left click and drag to analyze molecule");
        Shape3D shape = new Shape3D(text, ap);
        Shape3D shape2 = new Shape3D(text2, ap);
        Shape3D shape3 = new Shape3D(text3, ap);
        Shape3D shape4 = new Shape3D(text4, ap);

        Transform3D tr = new Transform3D();
        Transform3D tr2 = new Transform3D();
        Transform3D tr3 = new Transform3D();
        Transform3D tr4 = new Transform3D();
        tr.setScale(0.2);
        tr2.setScale(0.1);
        tr3.setScale(0.08);
        tr4.setScale(0.08);
        tr.setTranslation(new Vector3f(-0.87f, 0.2f, 0f));
        tr2.setTranslation(new Vector3f(-0.95f, -0.2f, 0f));
        tr3.setTranslation(new Vector3f(-0.75f, -0.35f, 0f));
        tr4.setTranslation(new Vector3f(-0.75f, -0.44f, 0f));
        TransformGroup tg = new TransformGroup(tr);
        TransformGroup tg2 = new TransformGroup(tr2);
        TransformGroup tg3 = new TransformGroup(tr3);
        TransformGroup tg4 = new TransformGroup(tr4);
        root.addChild(tg);
        root.addChild(tg2);
        root.addChild(tg3);
        root.addChild(tg4);
        tg.addChild(shape);
        tg2.addChild(shape2);
        tg3.addChild(shape3);
        tg4.addChild(shape4);

        PointLight light = new PointLight(new Color3f(Color.white),
                new Point3f(1f,1f,1f),
                new Point3f(1f,0.1f,0f));
        BoundingSphere bounds = new BoundingSphere();
        light.setInfluencingBounds(bounds);
        root.addChild(light);

        Background background = new Background(0f,0f,0f);
        background.setApplicationBounds(bounds);
        root.addChild(background);
        return root;
    }
}