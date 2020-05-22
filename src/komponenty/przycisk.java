package komponenty;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class przycisk extends JPanel implements KeyListener,MouseWheelListener,ComponentListener{
    private double min, max, roznica, scale;
    private double componentMaxScale, componentMinScale;
    private Color componentBackColor, componentFontColor,resultFontColor,resultBackColor;
    private JTextField resultTF;
    private String componentUnit;
    private JPanel multimetrPanel, switchPanel, onePanel, twoPanel, threePanel, fourPanel, fivePanel, sixPanel, sevenPanel, eightPanel, photoPanel;
    private JLabel label, label1,label2,label3,label4,label5,label6,label7,label8, labelmin, labelmax;
    private JLabel jed1, jed2, jed3, jed4, jed5, jed6, jed7, jed8;
    private JLabel[] labele;
    private JLabel[] jednostki;
    private ImageIcon iconImage;
    String[] images={"gora.png","prawogora.png","prawo.png","prawodol.png","dol.png","lewodol.png","lewo.png","lewogora.png"};
    private File Clap;
    private int x=0;
    private int componentDelayTime=0;
    private double componentMeasuredValue;
    private float componentFontSize;
   
    static URL iconUrl;
    static URL soundUrl;
    public przycisk(){
        setSize(500,500);
        
        iconUrl = this.getClass().getResource("klik.wav");
        soundUrl= this.getClass().getResource("success.wav");
        multimetrPanel= new JPanel();
        multimetrPanel.setLayout(new BorderLayout());
        multimetrPanel.setSize(new Dimension(getWidth(),getHeight()));
        onePanel = new JPanel();
        onePanel.setLayout(new BorderLayout());
        twoPanel = new JPanel();
        twoPanel.setLayout(new BorderLayout());
        threePanel = new JPanel();
        threePanel.setLayout(new BorderLayout());
        fourPanel = new JPanel();
        fourPanel.setLayout(new BorderLayout());
        fivePanel = new JPanel();
        fivePanel.setLayout(new BorderLayout());
        sixPanel = new JPanel();
        sixPanel.setLayout(new BorderLayout());
        sevenPanel = new JPanel();
        sevenPanel.setLayout(new BorderLayout());
        eightPanel = new JPanel();
        eightPanel.setLayout(new BorderLayout());
        switchPanel = new JPanel();
        switchPanel.setLayout(new GridLayout(3, 3));
        switchPanel.setSize(new Dimension(multimetrPanel.getWidth(),(int)(multimetrPanel.getHeight()*0.8)));
        photoPanel = new JPanel();
        photoPanel.setLayout(new BorderLayout());

        jed1=new JLabel();
        jed1.setPreferredSize(new Dimension(multimetrPanel.getWidth(), (int)(multimetrPanel.getHeight()*0.1)));
        jed1.setHorizontalAlignment(SwingConstants.LEFT);
        
        jednostki = new JLabel[]{jed1};
        resultTF = new JTextField();
        resultTF.setPreferredSize(new Dimension(multimetrPanel.getWidth(), (int)(multimetrPanel.getHeight()*0.1)));
        resultTF.setEditable(false);
        resultTF.setHorizontalAlignment(SwingConstants.RIGHT);
        label=new JLabel();
        label1= new JLabel();
        label2= new JLabel();
        label3= new JLabel();
        label4= new JLabel();
        label5= new JLabel();
        label6= new JLabel();
        label7= new JLabel();
        label8= new JLabel();
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        labelmin= new JLabel("min");
        labelmax = new JLabel("max");
        labele = new JLabel[]{label1,label2,label3,label4,label5,label6,label7,label8};
        iconImage = new ImageIcon(this.getClass().getResource(images[x]));
        label.setIcon(iconImage);
        addKeyListener(this);
        addMouseWheelListener(this);
        setFocusable(true);
        switchPanel.add(onePanel);
        onePanel.add(label8, BorderLayout.CENTER);
        switchPanel.add(twoPanel);
        twoPanel.add(label1, BorderLayout.NORTH);
        switchPanel.add(threePanel);
        threePanel.add(label2, BorderLayout.CENTER);
        switchPanel.add(fourPanel);
        fourPanel.add(label7, BorderLayout.WEST);
        switchPanel.add(photoPanel);
        photoPanel.add(label, BorderLayout.CENTER);
        switchPanel.add(fivePanel);
        fivePanel.add(label3, BorderLayout.EAST);
        switchPanel.add(sixPanel);
        sixPanel.add(label6, BorderLayout.CENTER);
        switchPanel.add(sevenPanel);
        sevenPanel.add(label5, BorderLayout.SOUTH);
        switchPanel.add(eightPanel);
        eightPanel.add(label4, BorderLayout.CENTER);
        multimetrPanel.add(switchPanel, BorderLayout.CENTER);
        multimetrPanel.add(jed1, BorderLayout.SOUTH);
        multimetrPanel.add(resultTF, BorderLayout.NORTH);
        add(multimetrPanel);
        addComponentListener(this);
    }

    public float getComponentFontSize() {
        return componentFontSize;
    }

    public void setComponentFontSize(float componentFontSize) {
        this.componentFontSize = componentFontSize;
        for(int i=0;i<8;i++)
        {
            labele[i].setFont(labele[i].getFont().deriveFont(componentFontSize));
            labele[i].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        }
            jednostki[0].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            jednostki[0].setFont(jednostki[0].getFont().deriveFont(componentFontSize));

        
    }

    
    public double getComponentMeasuredValue() {
        return componentMeasuredValue;
    }

    public void setComponentMeasuredValue(double componentMeasuredValue) {
        this.componentMeasuredValue = componentMeasuredValue;
    }
    
     public String getComponentUnit(){
        return componentUnit;
    }
    public void setComponentUnit(String componentUnit){
        this.componentUnit=componentUnit;
           jednostki[0].setText(componentUnit);        

    }
    public double getComponentMaxScale() {
        return componentMaxScale;
    }

    public void setComponentMaxScale(double componentMaxScale) {
        this.componentMaxScale = componentMaxScale;
        labelmax.setText(String.valueOf(componentMaxScale));
    }
    
    public int getComponentDelayTime(){
        return componentDelayTime;
    }
    public void setComponentDelayTime(int componentDelayTime){
        this.componentDelayTime=componentDelayTime;
    }
   

    public double getComponentMinScale() {
        return componentMinScale;
    }
    
    
    public void setComponentMinScale(double componentMinScale) {
        this.componentMinScale = componentMinScale;
        labelmin.setText(String.valueOf(componentMinScale));
        roznica = Double.parseDouble(labelmax.getText()) - Double.parseDouble(labelmin.getText());
        scale = roznica/8;
        for(int i=0;i<8;i++)
        {
            labele[i].setText(String.valueOf((i+1)*scale+ Double.parseDouble(labelmin.getText()))); 
        }
    }

    public Color getComponentFontColor() {
        return componentFontColor;
    }

    public void setComponentFontColor(Color componentFontColor) {
        this.componentFontColor = componentFontColor;
        for(int i=0;i<8;i++)
        {
            labele[i].setForeground(componentFontColor);
            labele[i].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        }
            jednostki[0].setForeground(componentFontColor);
    }

    public void setResultFontColor(Color resultFontColor){
        this.resultFontColor=resultFontColor;
        resultTF.setForeground(resultFontColor);
    }
    public Color getResultFontColor(){
        return resultFontColor;
    }
    public Color getResultBackColor(){
        return resultBackColor;
    }
    public void setResultBackColor(Color resultBackColor){
        this.resultBackColor=resultBackColor;
        resultTF.setBackground(resultBackColor);
    }
    public Color getComponentBackColor() {
        return componentBackColor;
    }

    public void setComponentBackColor(Color componentBackColor) {
        this.componentBackColor = componentBackColor;
        multimetrPanel.setBackground(componentBackColor);
        switchPanel.setBackground(componentBackColor);
        onePanel.setBackground(componentBackColor);
        twoPanel.setBackground(componentBackColor);
        threePanel.setBackground(componentBackColor);
        fourPanel.setBackground(componentBackColor);
        fivePanel.setBackground(componentBackColor);
        sixPanel.setBackground(componentBackColor);
        sevenPanel.setBackground(componentBackColor);
        eightPanel.setBackground(componentBackColor);
        photoPanel.setBackground(componentBackColor);
    }   
        
    static void PlaySound(){
        try{
            Clip clip= AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream( iconUrl );
            clip.open(ais);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    static void PlaySound2(){
        try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl);
            clip.open(ais);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength()/2000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

  
  
    public void keyPressed(KeyEvent e){
         
        if(e.getKeyCode() == KeyEvent.VK_UP){
            Timer timer = new Timer(componentDelayTime, new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if(x==images.length-1){
                        System.out.print("Przekroczyłeś zakres");
                    }
                    else{
                        x++;
                        PlaySound();
                        iconImage=new ImageIcon(this.getClass().getResource(images[x]));
                        Image img1= iconImage.getImage();
                        Image img2= img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                        ImageIcon i=new ImageIcon(img2);
                        label.setIcon(i);
                        if(componentMeasuredValue >= Double.parseDouble(labele[x].getText())){
                           resultTF.setText(String.valueOf(labele[x].getText())); 
                           PlaySound2();
                        }
                        else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                            resultTF.setText(String.valueOf(componentMeasuredValue)); 
                        }
                    }
                }
            });
            timer.setRepeats(false); // Only execute once
            timer.start(); // Go go go!
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN){
            Timer timer2 = new Timer(componentDelayTime,new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                     if(x==0){
                        System.out.print("Przekroczyłeś zakres");
                    }
                    else{
                        x--;
                        PlaySound();
                        iconImage=new ImageIcon(this.getClass().getResource(images[x]));
                        Image img1= iconImage.getImage();
                        Image img2= img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                        ImageIcon i=new ImageIcon(img2);
                        label.setIcon(i);
                        if(componentMeasuredValue >= Double.parseDouble(labele[x].getText())){
                           resultTF.setText(String.valueOf(labele[x].getText())); 
                           PlaySound2();
                        }
                        else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                            resultTF.setText(String.valueOf(componentMeasuredValue)); 
                        }
                    }
                }
            });
           timer2.setRepeats(false);
           timer2.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    

    @Override
    public void keyReleased(KeyEvent e) {}
    public void mouseWheelMoved(MouseWheelEvent e) {
        Timer timer=new Timer(componentDelayTime,new ActionListener(){
           public void actionPerformed(ActionEvent arg0){
            if (e.getWheelRotation() < 0) {
                if(x==images.length-1){
                    System.out.print("Przekroczyłeś zakres");
                }
                else{
                    x++;
                    PlaySound();
                    iconImage=new ImageIcon(this.getClass().getResource(images[x]));
                    Image img1= iconImage.getImage();
                    Image img2= img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon i=new ImageIcon(img2);
                    label.setIcon(i);
                    if(componentMeasuredValue >= Double.parseDouble(labele[x].getText())){
                        resultTF.setText(String.valueOf(labele[x].getText())); 
                        PlaySound2();
                    }
                    else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                        resultTF.setText(String.valueOf(componentMeasuredValue)); 
                    }
                }
            }
        else{
            if(x==0){
                System.out.print("Przekroczyłeś zakres");
            }
            else{
                x--;
                PlaySound();
                iconImage=new ImageIcon(this.getClass().getResource(images[x]));
                Image img1= iconImage.getImage();
                Image img2= img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon i=new ImageIcon(img2);
                label.setIcon(i);
                if(componentMeasuredValue >= Double.parseDouble(labele[x].getText())){
                   resultTF.setText(String.valueOf(labele[x].getText())); 
                   PlaySound2();
                }
                else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                    resultTF.setText(String.valueOf(componentMeasuredValue));
                }
            }
        }
        } 
        });
        timer.setRepeats(false);
        timer.start();
   }

    @Override
    public void componentResized(ComponentEvent e) {
        resultTF.setPreferredSize(new Dimension(getWidth(),(int) (getHeight()*0.1)));
        jed1.setPreferredSize(new Dimension(getWidth(), (int)(getHeight()*0.1)));
        multimetrPanel.setPreferredSize(new Dimension(getWidth(),getHeight()));
        switchPanel.setPreferredSize(new Dimension(multimetrPanel.getWidth(),(int)(multimetrPanel.getHeight()*0.8)));
        label.setPreferredSize(new Dimension(switchPanel.getWidth()/3, switchPanel.getHeight()/3));
        Image img1= iconImage.getImage();
        Image img2= img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon img=new ImageIcon(img2);
        label.setIcon(img);
        onePanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        twoPanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        threePanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        fourPanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        fivePanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        sixPanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        sevenPanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        eightPanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));
        photoPanel.setPreferredSize(new Dimension(switchPanel.getWidth()/3,switchPanel.getHeight()/3));

    }
    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}


    
