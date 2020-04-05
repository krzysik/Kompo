/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komponenty;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author KOMPUTER
 */
public class przycisk extends JPanel implements KeyListener,MouseWheelListener{
    private JPanel panel;
    private JLabel label;
    String[] images={"gora.png","prawogora.png","prawo.png","prawodol.png","dol.png","lewodol.png","lewo.png","lewogora.png"};
    private File Clap;
    private int x=0;
    public przycisk(){
        Clap=new File("E:\\Dev\\CygBacKomp\\Kompo\\src\\komponenty\\klik.wav");
        panel=new JPanel();
        label=new JLabel();
   
        
        ImageIcon iconImage = new ImageIcon(this.getClass().getResource(images[x]));
        label.setIcon(iconImage);
        addKeyListener(this);
        addMouseWheelListener(this);
        setFocusable(true);
        add(label);
        setVisible(true);
    }

        
        
    static void PlaySound(File Sound){
        try{
            Clip clip= AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
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
                PlaySound(Clap);
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
                PlaySound(Clap);
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
                PlaySound(Clap);
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
                PlaySound(Clap);
            }
        }
   }
}


    
