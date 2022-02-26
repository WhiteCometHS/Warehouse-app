/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author ACER
 */
public class OknoPracownika extends JFrame{
    private JLabel A; 
    private JButton Powrot;
    private JButton Show;
    private JButton Edit;
    private JButton Delete;
    private JButton Add;
    private JButton Status, Remowe,Save;
    private JButton Zamknij;
    OknoPracownika(Pracownik X, Baza X1, TablicaPracownikow X2)
    {
        super(X.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setLayout(new FlowLayout()); 
        add(A=new JLabel("Wybierz opcju:"));
        add(Edit=new JButton("Zmiana danych"));
        add(Add=new JButton("Dodawanie danych"));
        add(Delete=new JButton("Usuniecie danych"));
        add(Save=new JButton("Zapisz"));
        add(Show=new JButton("Przegłąd listy stałych klientów"));
        add(Remowe=new JButton("Odebrać pieniądze"));
        add(Status=new JButton("Zobacz status wplatomatu"));
        add(Powrot=new JButton("Powrót"));
        add(Zamknij=new JButton("Zamknij"));
        Edit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                X.edit();
            } 
        } );
        Save.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                X1.Zapisz();
                JOptionPane.showMessageDialog(null,"Dane zostaly zapisane","Informacja",JOptionPane.QUESTION_MESSAGE);
            } 
        } );
        Show.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                OknoKlientySpec S = new OknoKlientySpec(X1);
            } 
        } );
        Delete.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                X.usunT();
            } 
        } );
        Add.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                X.dodajT();
            } 
        } );
        Powrot.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                OknoStart S=new OknoStart(X1,X2);
                setVisible(false);
                S.setVisible(true);
            } 
        } );
        Remowe.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                Wplatomat A1 =new Wplatomat();
                A1.RemoveMoney();
                JOptionPane.showMessageDialog(null,"Odebrano pieniądze","Informacja",JOptionPane.QUESTION_MESSAGE);
            } 
        } );
        Status.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                Wplatomat A1 =new Wplatomat();
                A1.ShowStatus();
            } 
        } );
        Zamknij.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e)
             {
                 System.exit(0);
             }
        });
        setVisible(true);
    }
}
