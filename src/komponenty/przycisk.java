package komponenty;

import java.awt.GridLayout;
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

public class przycisk extends JPanel implements KeyListener,MouseWheelListener{
    private double min, max, roznica, scale;
    private double maxScale, minScale;
    private String jednostka;
    private JLabel label, label1,label2,label3,label4,label5,label6,label7,label8, labelmin, labelmax,labeljed;
    private JLabel[] labele; 
    String[] images={"gora.png","prawogora.png","prawo.png","prawodol.png","dol.png","lewodol.png","lewo.png","lewogora.png"};
    private File Clap;
    private int x=0;
    static URL iconUrl;
    public przycisk(){
         iconUrl = this.getClass().getResource("klik.wav");

        label=new JLabel();
        label1= new JLabel("jajamiomate");
        label2= new JLabel("jaja");
        label3= new JLabel("jaja");
        label4= new JLabel("jaja");
        label5= new JLabel("jaja");
        label6= new JLabel("jaja");
        label7= new JLabel("jaja");
        label8= new JLabel("jaja");
        labelmin= new JLabel("min");
        labelmax = new JLabel("max");
        labele = new JLabel[]{label1,label2,label3,label4,label5,label6,label7,label8};
        labeljed=new JLabel();
        setLayout(new GridLayout(3, 3));
        ImageIcon iconImage = new ImageIcon(this.getClass().getResource(images[x]));
        label.setIcon(iconImage);
        addKeyListener(this);
        addMouseWheelListener(this);
        setFocusable(true);
        
        add(label8);
        add(label1);
        add(label2);
        add(label7);
        add(label);
        add(label3);
        add(label6);
        add(label5);
        add(label4);
        setVisible(true);
       
    }
 public String getJednostka(){
        return jednostka;
    }
    public void setJednostka(String jednostka){
        this.jednostka=jednostka;
        labeljed.setText(String.valueOf(jednostka));
    }
    public double getMaxScale() {
        return maxScale;
    }

    public void setMaxScale(double maxScale) {
        this.maxScale = maxScale;
        labelmax.setText(String.valueOf(maxScale));

    }
   

    public double getMinScale() {
        return minScale;
    }

    public void setMinScale(double minScale) {
        this.minScale = minScale;
        labelmin.setText(String.valueOf(minScale));
        roznica = Double.parseDouble(labelmax.getText()) - Double.parseDouble(labelmin.getText());
        scale = roznica/8;
        for(int i=0;i<8;i++)
        {
            labele[i].setText(""+((i+1)*scale+ Double.parseDouble(labelmin.getText())+labeljed.getText())); 
            
        }
    }
    
    

        
        
    static void PlaySound(){
        try{
            Clip clip= AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.
            getAudioInputStream( iconUrl );
           clip.open(ais);
    
            clip.start();
            
            Thread.sleep(clip.getMicrosecondLength()/1000);
        }catch(Exception e){
            
        }
    }

  
    public void keyPressed(KeyEvent e){
        
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(x==images.length-1){
                System.out.print("Przekroczyłeś zakres");
            }
            else{
                x++;
                PlaySound();
                label.setIcon(new ImageIcon(this.getClass().getResource(images[x])));
                System.out.print(x);
            }
        }
        else if (e.getKeyCode()==KeyEvent.VK_DOWN){
            if(x==0){
                System.out.print("Przekroczyłeś zakres");
            }
            else{
                x--;
                label.setIcon(new ImageIcon(this.getClass().getResource(images[x])));
                PlaySound();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
     public void mouseWheelMoved(MouseWheelEvent e) {
         
        System.out.println("MouseWheelListenerDemo.mouseWheelMoved");
        
        // If wheel rotation value is a negative it means rotate up, while
        // positive value means rotate down
        
        if (e.getWheelRotation() < 0) {
            if(x==images.length-1){
                System.out.print("Przekroczyłeś zakres");
            }
            else{
                x++;
                PlaySound();
                label.setIcon( new ImageIcon(this.getClass().getResource(images[x])));
                System.out.print(x);
            }
        }
        else {
            if(x==0){
                System.out.print("Przekroczyłeś zakres");
            }
            else{
                x--;
                label.setIcon(new ImageIcon(this.getClass().getResource(images[x])));
                PlaySound();
            }
        }
   }
}


    
