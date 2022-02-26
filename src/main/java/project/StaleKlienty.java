/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class StaleKlienty {
    private Klient T[]=new Klient[1000];
    private int size=0;
    public StaleKlienty()
    { 
        try
        {
            Scanner s = new Scanner(new FileInputStream("stale klienty.bin"));
            s.useDelimiter("\n");
            String a="",b="",c="",d="",e="";
            while(s.hasNextLine())
            {
                String a1=s.nextLine();
                int counter=0;
                for (String retval : a1.split("\\ +")) {
                    counter++;
                    if(counter==1)
                    {
                        a=retval;
                    }
                    else if(counter==2)
                    {
                        b=retval;
                    }
                    else if(counter==3)
                    {
                        c = retval;
                    }
                    else if(counter==4)
                    {
                        d = retval;
                    }
                    else if(counter==5)
                    {
                        e = retval;
                    }
                }
                T[size]=new Klient(a,b,c,d,e);
                size++;
            }
        }
        catch(IOException ex)
        {             
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
        }      
    }
    void DodajKlienta(String a,String b,String c,String d,String e)
    {
        T[size]=new Klient(a,b,c,d,e);
        size++;
    }
    boolean Sprawdzenie(String a, String b)
    {
        for(int i=0;i<size;i++)
        {
            if(a.equals(T[i].GetPassword()) && b.equals(T[i].GetLogin()))
            {
                return true;
            }
        }
        return false;
    }
    int Poszuk(String a, String b)
    {
        for(int i=0;i<size;i++)
        {
            if(T[i].GetPassword().equals(a)==true && T[i].GetLogin().equals(b)==true)
            {
                return i;
            }
        }
        return 0;
    }
    Klient GetKlient(int i)
    {
        return T[i];
    }
    int GetSize()
    {
        return size;
    }
    void Zapisz()
    {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("stale klienty.bin")))
        {
            for(int i=0;i<size;i++)
            {
                dos.writeUTF(T[i].Wypisz());
            }
            dos.close();
        }
        catch(IOException ex)
        {      
             JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
        }  
    }
}
