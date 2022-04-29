import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.Switch;
import javax.media.j3d.TransformGroup;

import java.io.*;
import java.lang.Thread;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.interpolators.*;
import com.sun.j3d.utils.applet.MainFrame;
import java.util.Scanner;

import com.sun.j3d.utils.behaviors.mouse.*;

public class clickThrough extends Applet implements ActionListener{
  
    static int index = 0;
    Switch swNode = new Switch();
    public static void main(String[] args) {
     
        new MainFrame(new clickThrough(), 1600, 1000);
        /*Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter index");
        int newIndex = myObj.nextInt();  // Read user input
        index = newIndex;*/
    }

    Appearance ap;
    Appearance bb;

    AmbientLight aLight;
    DirectionalLight dLight;
    DirectionalLight dLight2;

    private SwitchValueInterpolator Next = null;
    private Interpolator current = null;

    public void init() {
      
      
        setLayout(new BorderLayout());
        Panel panel = new Panel();
        panel.setLayout(new GridLayout(1,1));
        add(panel, BorderLayout.EAST);
        Button button;
        button = new Button("Next");
        button.addActionListener(this);
        panel.add(button);
try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D cv = new Canvas3D(gc);
        setLayout(new BorderLayout());
        add(cv, BorderLayout.CENTER);

        SimpleUniverse su = new SimpleUniverse(cv);
        su.getViewingPlatform().setNominalViewingTransform();
        BranchGroup bg = createSceneGraph();
        bg.compile();
        su.addBranchGraph(bg);
    }

    public BranchGroup createSceneGraph() {
      
      BranchGroup root = new BranchGroup();
      TransformGroup tg = new TransformGroup();root.addChild(tg);
      tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
      
      // switch node
    /*  setLayout(new BorderLayout());
      Panel panel = new Panel();
      panel.setLayout(new GridLayout(1,1));
      add(panel, BorderLayout.EAST);
      Button button;
      button = new Button("Next");
      button.addActionListener(this);
      panel.add(button);*/
      

      swNode.setCapability(Switch.ALLOW_SWITCH_WRITE);
      tg.addChild(swNode);
      // appearance
      Appearance ap = new Appearance();
      Material material = new Material();
      material.setCapability(Material.ALLOW_COMPONENT_WRITE);
      material.setColorTarget(Material.AMBIENT);
      ap.setMaterial(material);
      TransparencyAttributes transAttr = new TransparencyAttributes(
      TransparencyAttributes.BLENDED,0.5f);
      transAttr.setCapability(TransparencyAttributes.ALLOW_VALUE_WRITE);
      ap.setTransparencyAttributes(transAttr);
      // gull
      
      Transform3D trans = new Transform3D();
      trans.setScale(0.3);
      TransformGroup tgScale = new TransformGroup(trans);
      //swNode.addChild(tgScale);
      //tgScale.addChild(tg);
      TransformGroup ox = new TransformGroup();
      TransformGroup oz = new TransformGroup();
      TransformGroup meth = new TransformGroup();
      TransformGroup first = new TransformGroup();
      first.addChild(new FirstPage().createSceneGraph());
      oz.addChild(new Ozone().createSceneGraph());
      ox.addChild(new Oxygen().createSceneGraph());
      meth.addChild(new Methane().createSceneGraph());
      // dodecahedron
      //dodec.setAppearance(ap);
      trans.setScale(0.1);
      tgScale = new TransformGroup(trans);
      swNode.addChild(first);
      swNode.addChild(oz);   
      swNode.addChild(ox);
      swNode.addChild(meth);
      
      //tgScale.addChild(dodec);
      // interpolators    
     BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);
      Alpha alpha = new Alpha(-1, 6000);
     /* alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
      alpha.setDecreasingAlphaDuration(6000);*/
      // switch
      Next = new SwitchValueInterpolator(alpha, swNode);
      Next.setSchedulingBounds(bounds);
      Next.setEnable(false);
      root.addChild(Next);

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

    public void actionPerformed(ActionEvent e) {
      
       swNode.setWhichChild(index);
       if(index == 3){
        index =0;
      } else {
        index++;
      }
    }    
}
