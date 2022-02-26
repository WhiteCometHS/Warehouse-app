/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Date;
import java.io.*;
/**
 *
 * @author ACER
 */
public class OknoKoszyk extends JFrame{
    private JLabel B;
    private JButton Pay;
    private JButton Powrot;
    private JLabel[] myLabels;
    private JButton[] myButtons;
    private int y=10;
    OknoKoszyk(Baza X,TablicaPracownikow X1, Koszyk X2)
    {
        super(X.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setLayout(null);
        myButtons = new JButton[X2.GetSize()];
        myLabels = new JLabel[X2.GetSize()];
        for(int i=0;i<X2.GetSize();i++)
        {
            myLabels[i]= new JLabel(X2.Wypisz(i));
            myLabels[i].setBounds(10, y,250,15); 
            add(myLabels[i]);
            myButtons[i] = new JButton("Usuń");
            myButtons[i].setBounds(250, y,100,25);
            add(myButtons[i]);
            y+=30;
            myButtons[i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) 
            {
                for(int i=0;i<myButtons.length;i++)
                {
                    if(myButtons[i].equals(e.getSource()))
                    {
                        X2.RemoveT(i);
                        OknoKoszyk S = new OknoKoszyk(X,X1,X2);
                        setVisible(false);
                    }
                }
            }
            });           
        }
        add(B=new JLabel("suma towarów: "+String.valueOf(X2.GetSuma())));
        B.setBounds(10, y,250,15);
        y+=30;
        add(Pay=new JButton("Zaplać"));
        Pay.setBounds(170, y,100,25);
        y+=30;
        add(Powrot=new JButton("Powrót"));
        Powrot.setBounds(170, y,100,25);
        Pay.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                if(X2.GetSize()!=0)
                {
                    if(X2.GetStatus()==true)
                    {
                        OknoKlient Z=new OknoKlient(X,X1,X2);
                        setVisible(false);
                    }
                    else
                    {
                        Wplatomat A=new Wplatomat();
                        A.AddMoney(X2.GetSuma());
                            for(int i=0;i<X.GetSize();i++)
                            {
                                int x=X2.Porownanie(X.GetTowar(i));
                                if(x>0)
                                {
                                    int z=X.GetTowar(i).GetAvailable()-x;
                                    X.GetTowar(i).SetAvailable(z);
                                    try
                                    {
                                        FileWriter writer = new FileWriter("transakcji.txt", true);
                                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                                        for(int j=0;j<X2.GetSize();j++)
                                        {
                                            Date date = new Date();
                                            bufferWriter.write("\t"+date+"\n");
                                            bufferWriter.write(X2.Wypisz(j)+"\n");                
                                        }
                                        bufferWriter.write("Suma: "+X2.GetSuma()+"\n========================\n");
                                        bufferWriter.close();
                                    }
                                    catch(IOException ex)
                                    {
                                        JOptionPane.showMessageDialog(null,"Błąd odczytu danych dla wplatowatu","Uwaga",JOptionPane.QUESTION_MESSAGE);
                                    }
                                }
                            }
                            X.Zapisz();
                            setVisible(false);
                            OknoKlient Z=new OknoKlient();
                            OknoWybor S=new OknoWybor(X,X1,X2,Z.GetStatus());
                            S.setVisible(true);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Kosz jest pusty","Uwaga",JOptionPane.QUESTION_MESSAGE);
            } 
        } );
        Powrot.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                OknoBaza S=new OknoBaza(X,X1);
                setVisible(false);
                S.setVisible(true);
            } 
        } );
        setVisible(true);
    }
}
