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
public class OknoWybor extends JFrame{
    private JLabel A; 
    private JButton Paragon;
    private JButton Faktura;
    OknoWybor(Baza X,TablicaPracownikow X1, Koszyk X2, boolean status)
    {
        super(X.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,150);
        setLayout(null);
        add(A=new JLabel("Wybierz dokument podtwierdzający"));
        A.setBounds(35, 5,250,15);              
        add(Paragon=new JButton("Paragon"));
        Paragon.setBounds(5, 35,100,25);
        add(Faktura=new JButton("Faktura"));
        Faktura.setBounds(150, 35,100,25);
        Paragon.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                try
                    {
                        FileWriter w = new FileWriter("paragon.txt");
                        w.write("Paragon:\n"+X.GetName()+"\nSprzedaz:\n");
                        for(int i=0;i<X2.GetSize();i++)
                        {
                             w.write(X2.Wypisz(i)+"\n");
                        }
                        w.write(X2.GetSuma()+"");
                        w.close();
                        JOptionPane.showMessageDialog(null,"Transakcja wykonana","Informacja",JOptionPane.QUESTION_MESSAGE);
                        OknoBaza S=new OknoBaza(X,X1);
                        setVisible(false);
                        S.setVisible(true);
                    }
                    catch(IOException s)
                    {
                        JOptionPane.showMessageDialog(null,"Błąd","Uwaga",JOptionPane.QUESTION_MESSAGE);
                    }
            } 
        } );
        Faktura.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                if(status==true)
                {
                    try
                    {
                        FileWriter w = new FileWriter("faktura.txt");
                        w.write("Faktura:\n"+X.GetName()+"\nSprzedaz:\n");
                        for(int i=0;i<X2.GetSize();i++)
                        {
                             w.write(X2.Wypisz(i)+"\n");
                        }
                        w.write(X2.GetSuma()+"\nDane klienta:\n");
                        w.write(X2.GetKlient().WypiszP());
                        w.close();
                        JOptionPane.showMessageDialog(null,"Transakcja wykonana","Informacja",JOptionPane.QUESTION_MESSAGE);
                        OknoBaza S=new OknoBaza(X,X1);
                        setVisible(false);
                        S.setVisible(true);
                    }
                    catch(IOException s)
                    {
                        JOptionPane.showMessageDialog(null,"Błąd","Uwaga",JOptionPane.QUESTION_MESSAGE);
                    }
                }
                else
                {
                    try
                    {
                        String a = (JOptionPane.showInputDialog(null,"Podaj swoje imie","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                        String b = (JOptionPane.showInputDialog(null,"Podaj swoje nazwisko","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                        String c = (JOptionPane.showInputDialog(null,"Podaj swój email","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                        if(!c.contains("@"))throw new MyException("Podaj prawidowy email");
                        FileWriter w = new FileWriter("faktura.txt");
                        w.write("Faktura:\n"+X.GetName()+"\nSprzedaz:\n");
                        for(int i=0;i<X2.GetSize();i++)
                        {
                             w.write(X2.Wypisz(i)+"\n");
                        }
                        w.write(X2.GetSuma()+"\nDane klienta:\n"+a+" "+b+" "+c);
                        w.close();
                        JOptionPane.showMessageDialog(null,"Transakcja wykonana","Informacja",JOptionPane.QUESTION_MESSAGE);
                        OknoBaza S=new OknoBaza(X,X1);
                        setVisible(false);
                        S.setVisible(true);
                    }
                    catch(IOException s)
                    {
                        JOptionPane.showMessageDialog(null,"Błąd","Uwaga",JOptionPane.QUESTION_MESSAGE);
                    }
                    catch(MyException ex)
                    {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
                    } 
                }
            } 
        } );
        setVisible(true);
    }
}
