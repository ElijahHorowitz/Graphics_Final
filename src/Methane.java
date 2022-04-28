import javax.vecmath.*;
import java.awt.*;
import javax.media.j3d.*;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import java.applet.*;
import com.sun.j3d.utils.applet.MainFrame;

public class Methane extends Applet {

    public static void main(String[] args) {
        new MainFrame(new Methane(), 1000, 1000);
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
        SimpleUniverse su = new SimpleUniverse(cv, 2);
        su.getViewingPlatform().setNominalViewingTransform();
        BranchGroup bg = createSceneGraph(su.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0));
        bg.compile();
        su.addBranchGraph(bg);
    }

    Appearance ap;
    Appearance bb;
    Appearance blue;
    private BranchGroup createSceneGraph(TransformGroup vtg) {
        //Branch Group
        BranchGroup root = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(2);

        //Appearance
        ap = new Appearance();
        bb = new Appearance();
        blue = new Appearance();
        Appearance cc = new Appearance();

//Text
        Font3D font = new Font3D(new Font("SansSerif", Font.PLAIN, 1),
                new FontExtrusion());
        cc.setMaterial(new Material());

        Text3D text = new Text3D(font, "Methane");
        Text3D text2 = new Text3D(font, "CH4 is a molecule that contains one Carbon atom and four Hydrogen atoms.");

        Shape3D shee = new Shape3D(text, cc);
        Shape3D shee2 = new Shape3D(text2, cc);

        Transform3D xxx = new Transform3D();
        Transform3D xxx2 = new Transform3D();

        xxx.setScale(0.2);
        xxx2.setScale(0.058);

        xxx.setTranslation(new Vector3f(-.35f, .5f, 0f));
        xxx2.setTranslation(new Vector3f(-.98f, 0.4f, 0f));

        TransformGroup ttt = new TransformGroup(xxx);
        TransformGroup ttt2 = new TransformGroup(xxx2);

        ttt.addChild(shee);
        ttt2.addChild(shee2);


        root.addChild(ttt);
        root.addChild(ttt2);

        Material grey = new Material();
        Material mat = new Material();
        Material blu = new Material();
        blu.setDiffuseColor(0,0,100);
        grey.setDiffuseColor(.5f,.5f,.5f);
        mat.setDiffuseColor(0,100,0);
        ap.setMaterial(mat);
        bb.setMaterial(grey);
        blue.setMaterial(blu);

        Transform3D tr = new Transform3D();
        Transform3D tr1 = new Transform3D();
        Transform3D tr2 = new Transform3D();
        Transform3D tr3 = new Transform3D();
        Transform3D tr4 = new Transform3D();
        Transform3D tr5 = new Transform3D();
        Transform3D tr6 = new Transform3D();
        Transform3D tr7 = new Transform3D();
        Transform3D tr8 = new Transform3D();

        tr.setScale(1);
        tr1.setScale(0.2);
        tr2.setScale(1);

        Vector3f vec = new Vector3f(0f,1f, .0f);
        Vector3f vec1 = new Vector3f(0f,0f, .0f);
        Vector3f vec2 = new Vector3f(0f, -1f, .0f);
        Vector3f vec3 = new Vector3f(0f, -2f, 0f);
        Vector3f vec4 = new Vector3f(0f, -3f, 0f);
        Vector3d vec5 = new Vector3d(1f, -1f, 0f);
        Vector3d vec6 = new Vector3d(-1f, -1f, 0f);
        Vector3f vec7 = new Vector3f(-2f, -1f, 0f);
        Vector3f vec8 = new Vector3f(2f, -1f,0);

        AxisAngle4d rot = new AxisAngle4d();
        rot.set(vec5, 90);
        rot.setZ(1);




        tr.setTranslation(vec);
        tr1.setTranslation(vec1);
        tr2.setTranslation(vec2);
        tr3.setTranslation(vec3);
        tr4.setTranslation(vec4);
        tr5.setTranslation(vec5);
        tr5.setRotation(rot);
        tr6.setTranslation(vec6);
        tr6.setRotation(rot);
        tr7.setTranslation(vec7);
        tr8.setTranslation(vec8);

        TransformGroup tg = new TransformGroup(tr);
        TransformGroup tg1 = new TransformGroup(tr1);
        TransformGroup tg2 = new TransformGroup(tr2);
        TransformGroup tg3 = new TransformGroup(tr3);
        TransformGroup tg4 = new TransformGroup(tr4);
        TransformGroup tg5 = new TransformGroup(tr5);
        TransformGroup tg6 = new TransformGroup(tr6);
        TransformGroup tg7 = new TransformGroup(tr7);
        TransformGroup tg8 = new TransformGroup(tr8);

        tg.setTransform(tr);
        tg1.setTransform(tr1);
        tg2.setTransform(tr2);
        tg3.setTransform(tr3);
        tg4.setTransform(tr4);
        tg5.setTransform(tr5);
        tg6.setTransform(tr6);
        tg7.setTransform(tr7);
        tg8.setTransform(tr8);

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg4.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg5.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg6.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg7.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        tg8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg8.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);


        //Shapes
        Sphere shape = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Cylinder cy = new Cylinder(.1f, Cylinder.GENERATE_NORMALS, bb);
        Sphere shape1 = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, blue);
        Cylinder cy1 = new Cylinder(.1f, Cylinder.GENERATE_NORMALS, bb);
        Sphere shape2 = new Sphere(.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Cylinder cy2 = new Cylinder(.1f, Cylinder.GENERATE_NORMALS, bb);
        Cylinder cy3 = new Cylinder(.1f, Cylinder.GENERATE_NORMALS, bb);
        Sphere shape3 = new Sphere(.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Sphere shape4 = new Sphere(.5f, Sphere.GENERATE_NORMALS, 150, ap);

        cy.addChild(tg);
        cy.addChild(tg2);
        cy.addChild(tg3);
        cy.addChild(tg4);
        cy.addChild(tg5);
        cy.addChild(tg6);
        cy.addChild(tg7);
        cy.addChild(tg8);


        tg.addChild(shape);
        tg1.addChild(cy);
        tg2.addChild(shape1);
        tg3.addChild(cy1);
        tg4.addChild(shape2);
        tg5.addChild(cy2);
        tg6.addChild(cy3);
        tg7.addChild(shape3);
        tg8.addChild(shape4);

        tg.setTransform(tr);
        tg1.setTransform(tr1);
        tg2.setTransform(tr2);
        tg3.setTransform(tr3);
        tg4.setTransform(tr4);
        tg5.setTransform(tr5);
        tg6.setTransform(tr6);
        tg7.setTransform(tr7);
        tg8.setTransform(tr8);

        //root.addChild(tg);
        root.addChild(tg1);
        //root.addChild(tg2);

        //view rotator
        /*MouseRotate rotater = new MouseRotate(tg);
        rotater.setSchedulingBounds(bounds);
        tg.addChild(rotater);

        MouseTranslate translator = new MouseTranslate(tg);
        translator.setSchedulingBounds(bounds);
        tg.addChild(translator);*/

        MouseRotate rotater1 = new MouseRotate(tg1);
        rotater1.setSchedulingBounds(bounds);
        tg1.addChild(rotater1);

        MouseTranslate translator1 = new MouseTranslate(tg1);
        translator1.setSchedulingBounds(bounds);
        tg1.addChild(translator1);


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
