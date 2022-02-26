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
public class OknoStart extends JFrame{
    private JLabel A,B,C; 
    private static JTextField Login;
    private static JPasswordField Password;
    private JButton Pracownik;
    private JButton Show;
    private JButton Zamknij;
    OknoStart(Baza X, TablicaPracownikow X1)
    {
        super(X.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setLayout(null);
        add(A=new JLabel("Jesteś pracownikiem? Zaloguj się."));
        A.setBounds(5, 0,200,25);
        add(B=new JLabel("Login:"));
        B.setBounds(85, 20,150,25);
        add(Login=new JTextField());
        Login.setBounds(30, 40,150,25);
        add(C=new JLabel("Hasło:"));
        C.setBounds(85, 60,150,25);
        add(Password=new JPasswordField());
        Password.setBounds(30, 80,150,25);
        add(Pracownik=new JButton("Zaloguj się"));
        Pracownik.setBounds(50, 120,100,25);
        add(Show=new JButton("Przegląd bazy towarów"));
        Show.setBounds(200, 80,170,25);
        add(Zamknij=new JButton("Zamknij"));
        Zamknij.setBounds(235, 120,100,25);
        Show.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                OknoBaza A=new OknoBaza(X,X1);
                setVisible(false);
            } 
        } );
        Pracownik.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    if(Login.getText().trim().length() == 0|| String.valueOf(Password.getPassword()).length() == 0)
                    {
                        Login.setText("");
                        Password.setText("");
                        throw new MyException("Podaj login oraz haslo");
                    }
                    else
                    {
                        int a=X1.CheckT(Login.getText(), String.valueOf(Password.getPassword()));
                        if(a>=0)
                        {
                            Pracownik A1=X1.GetPracownik(a);
                            OknoPracownika A=new OknoPracownika(A1,X,X1);
                            setVisible(false);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Niepawidlowe dane, sprobuj ponownie","Uwaga",JOptionPane.QUESTION_MESSAGE);
                            Login.setText("");
                            Password.setText("");
                        }
                    }
                }
                catch(MyException ex)
                {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
                }
            } 
        } );
        Zamknij.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e)
             {
                X.Zapisz();
                System.exit(0);
             }
        });
        setVisible(true);
    }
}
