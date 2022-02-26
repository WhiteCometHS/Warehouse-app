/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Wplatomat {
    private float money=0;
    public Wplatomat()
    {
        try(DataInputStream dos = new DataInputStream(new FileInputStream("wplatomat.bin")))
        {
            float a = dos.readFloat();
            this.money+=a;
            dos.close();
        }
        catch(IOException ex)
        {    
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
        }  
    }
    public void AddMoney(float a)
    {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("wplatomat.bin")))
        {
            this.money+=a;
            dos.writeFloat(money);
            dos.close();
        }
        catch(IOException ex)
        {      
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
        }  

    }
    public void ShowStatus()
    {
        JOptionPane.showMessageDialog(null,"Pieniądze: "+money,"Dane",JOptionPane.QUESTION_MESSAGE);
    }
    public void RemoveMoney()
    {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("wplatomat.bin")))
        {
            this.money=0;
            dos.writeFloat(money);
            dos.close();
        }
        catch(IOException ex)
        {      
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
        }  

    }
}
