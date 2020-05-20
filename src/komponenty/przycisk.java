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
    private JPanel multimetrPanel, switchPanel, onePanel, twoPanel, threePanel, fourPanel, fivePanel, sixPanel, sevenPanel, eightPanel;
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
   
    static URL iconUrl;
    static URL soundUrl;
    public przycisk(){
        iconUrl = this.getClass().getResource("klik.wav");
        soundUrl= this.getClass().getResource("success.wav");
        
        multimetrPanel= new JPanel();
        multimetrPanel.setLayout(new BorderLayout());
        multimetrPanel.setSize(new Dimension(this.getWidth(),this.getHeight()));
        addComponentListener(this);
       
        
        onePanel = new JPanel();
        onePanel.setLayout(new FlowLayout());
        
        twoPanel = new JPanel();
        twoPanel.setLayout(new FlowLayout());

        threePanel = new JPanel();
        threePanel.setLayout(new FlowLayout());

        fourPanel = new JPanel();
        fourPanel.setLayout(new FlowLayout());

        fivePanel = new JPanel();
        fivePanel.setLayout(new FlowLayout());

        sixPanel = new JPanel();
        sixPanel.setLayout(new FlowLayout());

        sevenPanel = new JPanel();
        sevenPanel.setLayout(new FlowLayout());

        eightPanel = new JPanel();
        eightPanel.setLayout(new FlowLayout());

        switchPanel = new JPanel();
        switchPanel.setLayout(new GridLayout(3, 3));
        switchPanel.setSize(this.getWidth(),this.getHeight());

        jed1=new JLabel();
        jed2=new JLabel();
        jed3=new JLabel();
        jed4=new JLabel();
        jed5=new JLabel();
        jed6=new JLabel();
        jed7=new JLabel();
        jed8=new JLabel();
        
        jednostki = new JLabel[]{jed1,jed2,jed3,jed4,jed5,jed6,jed7,jed8};
        
        resultTF = new JTextField();
        resultTF.setPreferredSize(new Dimension(multimetrPanel.getWidth(), 40));
        resultTF.setEditable(false);
        resultTF.setHorizontalAlignment(SwingConstants.RIGHT);
        resultTF.setFont(resultTF.getFont().deriveFont(32f));
        
        label=new JLabel();
        label1= new JLabel();
       
        label2= new JLabel();
        label3= new JLabel();
        label4= new JLabel();
        label5= new JLabel();
        label6= new JLabel();
        label7= new JLabel();
        label8= new JLabel();
         label1.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label2.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label3.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label4.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label5.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label6.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label7.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label8.setSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
        label7.setHorizontalAlignment(SwingConstants.RIGHT);
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        label1.setVerticalAlignment(SwingConstants.BOTTOM);
        jed1.setVerticalAlignment(SwingConstants.BOTTOM);
        jed2.setVerticalAlignment(SwingConstants.BOTTOM);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        jed6.setVerticalAlignment(SwingConstants.TOP);
        jed7.setVerticalAlignment(SwingConstants.TOP);
        jed8.setVerticalAlignment(SwingConstants.TOP);
        label5.setVerticalAlignment(SwingConstants.TOP);
        label6.setVerticalAlignment(SwingConstants.TOP);
        label6.setHorizontalAlignment(SwingConstants.RIGHT);
        label8.setVerticalAlignment(SwingConstants.BOTTOM);
        label8.setHorizontalAlignment(SwingConstants.RIGHT);
        label2.setVerticalAlignment(SwingConstants.BOTTOM);
        label4.setVerticalAlignment(SwingConstants.TOP);
        jed3.setVerticalAlignment(SwingConstants.BOTTOM);
        labelmin= new JLabel("min");
        labelmax = new JLabel("max");
        labele = new JLabel[]{label1,label2,label3,label4,label5,label6,label7,label8};

        iconImage = new ImageIcon(this.getClass().getResource(images[x]));
        label.setIcon(iconImage);
        addKeyListener(this);
        addMouseWheelListener(this);
        setFocusable(true);
        
        
        switchPanel.add(onePanel);
        onePanel.add(label8);
        onePanel.add(jed1);
        switchPanel.add(twoPanel);
        twoPanel.add(label1);
        twoPanel.add(jed2);
        switchPanel.add(threePanel);
        threePanel.add(label2);
        threePanel.add(jed3);
        switchPanel.add(fourPanel);
        fourPanel.add(label7);
        fourPanel.add(jed4);
        switchPanel.add(label);
        switchPanel.add(fivePanel);
        fivePanel.add(label3);
        fivePanel.add(jed5);
        switchPanel.add(sixPanel);
        sixPanel.add(label6);
        sixPanel.add(jed6);        
        switchPanel.add(sevenPanel);
        sevenPanel.add(label5);
        sevenPanel.add(jed7);
        switchPanel.add(eightPanel);
        eightPanel.add(label4);
        eightPanel.add(jed8);

        
        multimetrPanel.add(switchPanel, BorderLayout.CENTER);
        multimetrPanel.add(resultTF, BorderLayout.NORTH);
        //setVisible(true);
        add(multimetrPanel);
       
       
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
        for(int i=0;i<8;i++)
        {
            jednostki[i].setText(componentUnit);        
        }
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
            labele[i].setFont(labele[i].getFont().deriveFont(32f));
            jednostki[i].setForeground(componentFontColor);
            jednostki[i].setFont(jednostki[i].getFont().deriveFont(32f));
            labele[i].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            jednostki[i].setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        }
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
                }else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                
                    resultTF.setText(String.valueOf(componentMeasuredValue)); 
                
                }
            }
        }
             
             });
         timer.setRepeats(false); // Only execute once
         timer.start(); // Go go go!
    }else if (e.getKeyCode()==KeyEvent.VK_DOWN){
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
                }else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                
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
    public void keyTyped(KeyEvent e) {
        
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
     public void mouseWheelMoved(MouseWheelEvent e) {
         
      
        
        // If wheel rotation value is a negative it means rotate up, while
        // positive value means rotate down
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
                }else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                
                    resultTF.setText(String.valueOf(componentMeasuredValue)); 
                
                }
                System.out.print(x);
            }
        }
        else {
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
                }else if((componentMeasuredValue < Double.parseDouble(labele[x].getText()))&& componentMeasuredValue > Double.parseDouble(labelmin.getText())){
                
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
        resultTF.setPreferredSize(new Dimension(this.getWidth(), 40));
        multimetrPanel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
        
      
       label.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
       Image img1= iconImage.getImage();
       Image img2= img1.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);
       ImageIcon i=new ImageIcon(img2);
       label.setIcon(i);
            label1.setPreferredSize(new Dimension(81,this.getHeight()/5));
            label2.setPreferredSize(new Dimension(81,this.getHeight()/5));
            label3.setPreferredSize(new Dimension(81,this.getHeight()/5));
            label4.setPreferredSize(new Dimension(81,this.getHeight()/5));
            label5.setPreferredSize(new Dimension(81,this.getHeight()/5));
            label6.setPreferredSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label7.setPreferredSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            label8.setPreferredSize(new Dimension(this.getWidth()/5,this.getHeight()/5));
            jed1.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed2.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed3.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed4.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed5.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed6.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed7.setPreferredSize(new Dimension(21,this.getHeight()/5));
            jed8.setPreferredSize(new Dimension(21,this.getHeight()/5));
            System.out.print(label1.getSize());
        //int k;
      //  k = (this.getWidth()/42)+32;
    //   String z=Integer.toString(k)+"f";
   //    System.out.print(z);
      
    }

    @Override
    public void componentMoved(ComponentEvent e) {
       
    }

    @Override
    public void componentShown(ComponentEvent e) {
     
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }

   
   
}


    
