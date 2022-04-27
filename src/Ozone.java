import javax.vecmath.*;
import java.awt.*;
import javax.media.j3d.*;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import java.applet.*;

import com.sun.j3d.utils.applet.MainFrame;

public class Ozone extends Applet {

    public static void main(String[] args) {
        new MainFrame(new Ozone(), 640, 480);
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
    private BranchGroup createSceneGraph(TransformGroup vtg) {
        //Branch Group
        BranchGroup root = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(2);

        //Appearance
        ap = new Appearance();
        bb = new Appearance();
        Material grey = new Material();
        Material mat = new Material();
        grey.setDiffuseColor(.5f,.5f,.5f);
        mat.setDiffuseColor(100,0,0);
        ap.setMaterial(mat);
        bb.setMaterial(grey);

        Transform3D tr = new Transform3D();
        Transform3D tr1 = new Transform3D();
        Transform3D tr2 = new Transform3D();
        Transform3D tr3 = new Transform3D();
        Transform3D tr4 = new Transform3D();

        TransformGroup tg = new TransformGroup(tr);
        TransformGroup tg1 = new TransformGroup(tr1);
        TransformGroup tg2 = new TransformGroup(tr2);
        TransformGroup tg3 = new TransformGroup(tr3);
        TransformGroup tg4 = new TransformGroup(tr4);

        //Shapes
        Sphere shape = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Cylinder cy = new Cylinder(.1f, Cylinder.GENERATE_NORMALS, bb);
        Sphere shape1 = new Sphere(0.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Sphere shape2 = new Sphere(.5f, Sphere.GENERATE_NORMALS, 150, ap);
        Cylinder cy1 = new Cylinder(.1f, Cylinder.GENERATE_NORMALS, bb);

        tr1.setScale(0.25);
        AxisAngle4d rot = new AxisAngle4d();


        Vector3f vec = new Vector3f(0f,.9f, 0f);
        Vector3f vec1 = new Vector3f(0f,0f, .0f);
        Vector3f vec2 = new Vector3f(0f, -.9f, 0f);
        Vector3f vec3 = new Vector3f(1.8f, -1f, 0f);
        Vector3d vec4 = new Vector3d(.9f, -1f, 0f);
        rot.set(vec4, 90f);
        rot.setZ(1);


        tr.setTranslation(vec);
        tr1.setTranslation(vec1);
        tr2.setTranslation(vec2);
        tr3.setTranslation(vec3);
        tr4.setTranslation(vec4);
        tr4.setRotation(rot);

        tg.setTransform(tr);
        tg1.setTransform(tr1);
        tg2.setTransform(tr2);
        tg3.setTransform(tr3);
        tg4.setTransform(tr4);

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

        tg.addChild(shape);
        tg1.addChild(cy);
        tg2.addChild(shape1);
        tg3.addChild(shape2);
        tg4.addChild(cy1);

        cy.addChild(tg);
        cy.addChild(tg2);
        cy.addChild(tg3);
        cy.addChild(tg4);



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
