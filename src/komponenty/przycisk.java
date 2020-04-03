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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
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
public class przycisk extends JPanel implements KeyListener{
    private JPanel panel;
    private JLabel label;
   String[] images={"C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka4.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka3.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka2.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka1.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka5.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka6.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka7.png","C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\kupka8.png"};
    private File Clap;
    private int x=0;
    public przycisk(){
        Clap=new File("C:\\Users\\KOMPUTER\\Documents\\NetBeansProjects\\Komponenty\\src\\komponenty\\klik.wav");
        panel=new JPanel();
        label=new JLabel();
   
        
        ImageIcon imgThisImg = new ImageIcon(images[0]);
        label.setIcon(imgThisImg);
        addKeyListener(this);
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
                   }else{
                    x++;
                   PlaySound(Clap);
               label.setIcon(new ImageIcon(images[x]));
             System.out.print(x);
               
                   }}
               
                else if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    if(x==0){
                        System.out.print("Przekroczyłeś zakres");
                    }else{
                    x--;
                label.setIcon(new ImageIcon(images[x]));
               PlaySound(Clap);
                    }}
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
   
} 


    
