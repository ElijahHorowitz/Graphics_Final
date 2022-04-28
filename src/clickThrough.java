import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.Switch;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.interpolators.*;
import com.sun.j3d.utils.applet.MainFrame;

import com.sun.j3d.utils.behaviors.mouse.*;

public class clickThrough extends Applet implements ActionListener{
    public static void main(String[] args) {
        new MainFrame(new clickThrough(), 800, 600);
    }

    Appearance ap;
    Appearance bb;

    AmbientLight aLight;
    DirectionalLight dLight;
    DirectionalLight dLight2;

    private SwitchValueInterpolator Home = null;
    private SwitchValueInterpolator Oxygen = null;
    private SwitchValueInterpolator Ozone = null;
    private SwitchValueInterpolator Methane = null;
    private Interpolator current = null;

    public void init() {
        setLayout(new BorderLayout());
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(8,1));
        add(panel, BorderLayout.EAST);
        Button button;
        button = new Button("Home");
        button.addActionListener(this);
        panel.add(button);
        button = new Button("Oxygen");
        button.addActionListener(this);
        panel.add(button);
        button = new Button("Ozone");
        button.addActionListener(this);
        panel.add(button);
        button = new Button("Methane");
        button.addActionListener(this);
        panel.add(button);

        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);

        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        BranchGroup bg = createSceneGraph(su.getViewingPlatform().getMultiTransformGroup().getTransformGroup(0));
        bg.compile();
        su.addBranchGraph(bg);
    }

    public BranchGroup createSceneGraph(TransformGroup vtg) {
        //Branch
        BranchGroup root = new BranchGroup();
        TransformGroup tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        root.addChild(tg);
        //Switch
        Switch swNode = new Switch();
        swNode.setCapability(Switch.ALLOW_SWITCH_WRITE);
        tg.addChild(swNode);
        //Appearance
        Appearance ap = new Appearance();
        Material material = new Material();
        material.setCapability(Material.ALLOW_COMPONENT_WRITE);
        material.setColorTarget(Material.AMBIENT);
        ap.setMaterial(material);
        TransparencyAttributes transAttr = new TransparencyAttributes(
        TransparencyAttributes.BLENDED,0.5f);
        transAttr.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
        ap.setTransparencyAttributes(transAttr);
        //Bound
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);
        Alpha alpha = new Alpha(-1, 6000);
        alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
        alpha.setDecreasingAlphaDuration(6000);
        //Home
        Home = new SwitchValueInterpolator(alpha, swNode);
        Home.setSchedulingBounds(bounds);
        Home.setEnable(false);
        root.addChild(Home);
        //Oxygen
        Oxygen = new SwitchValueInterpolator(alpha, swNode);
        Oxygen.setSchedulingBounds(bounds);
        Oxygen.setEnable(false);
        root.addChild(Oxygen);
        //Ozone
        Ozone = new SwitchValueInterpolator(alpha, swNode);
        Ozone.setSchedulingBounds(bounds);
        Ozone.setEnable(false);
        root.addChild(Ozone);
        //Methane
        Methane = new SwitchValueInterpolator(alpha, swNode);
        Methane.setSchedulingBounds(bounds);
        Methane.setEnable(false);
        root.addChild(Methane);
        //Background
        Background background = new Background(1.0f, 1.0f, 1.0f);
        background.setApplicationBounds(bounds);
        root.addChild(background);

        return root;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String cmd = e.getActionCommand();
        if ("Home".equals(cmd)) {
          current.setEnable(false);
          current = Home;
          current.setEnable(true);
        } else if ("Oxygen".equals(cmd)) {
          current.setEnable(false);
          current = Oxygen;
          current.setEnable(true);
        } else if ("Ozone".equals(cmd)) {
          current.setEnable(false);
          current = Ozone;
          current.setEnable(true);
        } else if ("Methane".equals(cmd)) {
          current.setEnable(false);
          current = Methane;
          current.setEnable(true);
        }
    }

}
