/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author ACER
 */
public class OknoPracSpec extends JFrame{
    private JLabel A;
    private JButton Powrot;
    private int y=25;
    private JButton[] myButtons;
    private JLabel[] myLabels;
    OknoPracSpec(Baza X1,Towar Temp[],int counter,String a,boolean x)
    {
        super(X1.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setLayout(null);
        myLabels = new JLabel[counter];
        myButtons = new JButton[counter];
        A=new JLabel("Znalieziono:");
        add(A);
        A.setBounds(190, 10, 250, 15);
        for(int i=0;i<counter;i++)
        {
                myLabels[i]= new JLabel(i+1+" - "+X1.GetTowar(i).wypiszT1());
                add(myLabels[i]);
                myLabels[i].setBounds(10, y,250,15);
                myButtons[i] = new JButton("Wybierz");
                myButtons[i].setBounds(250, y,100,25);
                add(myButtons[i]);
                y+=30;
                Temp[counter-1]=X1.GetTowar(i);
                if(x==true)
                {
                    myButtons[i].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) 
                    {
                        for(int i=0;i<myButtons.length;i++)
                        {
                            if(myButtons[i].equals(e.getSource()))
                            {
                                for(int j=0;j<X1.GetSize();j++)
                                {
                                    if(X1.GetTowar(j)==Temp[i])
                                    {
                                        for(int k=i;k<X1.GetSize()-1;k++)
                                        {
                                            X1.SetTowar(k, X1.GetTowar(k+1));
                                        }
                                        X1.SetSize();
                                        setVisible(false);
                                        JOptionPane.showMessageDialog(null,"Wybrany towar zostal usunięty","Uwaga",JOptionPane.QUESTION_MESSAGE);
                                        break;
                                    }
                                }
                            }
                        }
                    } 
                    });
                }
                else
                {
                    myButtons[i].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) 
                    {
                        try
                        {
                            for(int i=0;i<myButtons.length;i++)
                            {
                                if(myButtons[i].equals(e.getSource()))
                                {
                                    String a1 = (JOptionPane.showInputDialog(null,"Podaj nazwe towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                                    if(a1.length()==0) throw new MyException("Podane pole musi byc wypelnione");
                                    String b = (JOptionPane.showInputDialog(null,"Podaj typ opakowania towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                                    if(b.length()==0) throw new MyException("Podane pole musi byc wypelnione");
                                    int c = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj ilosc towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                                    float d = Float.parseFloat(JOptionPane.showInputDialog(null,"Podaj cene towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                                    for(int j=0;j<X1.GetSize();j++)
                                    {
                                        if(X1.GetTowar(j)==Temp[i])
                                        {
                                            for(int k=i;k<X1.GetSize()-1;k++)
                                            {
                                                X1.SetTowar(k, a1, b, c, d);
                                                setVisible(false);
                                                JOptionPane.showMessageDialog(null,"Wybrany towar zostal zmieniony","Uwaga",JOptionPane.QUESTION_MESSAGE);
                                                 break;
                                            }
                                        }
                                    }
                                 }
                            }
                        }
                        catch(MyException ex)
                        {
                            JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
                        } 
                        catch(NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(null,"Niepoprawy format, sprobuj ponowninie","Uwaga",JOptionPane.QUESTION_MESSAGE);
                        }                                 
                    } 
                    });
                }           
        }
        add(Powrot=new JButton("Powrót"));
        Powrot.setBounds(170, y,100,25);
        y+=30;
        Powrot.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
            } 
        } );   
        setVisible(true);
    }
            
}
