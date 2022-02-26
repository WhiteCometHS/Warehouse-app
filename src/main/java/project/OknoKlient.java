/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import javax.swing.*;
/**
 *
 * @author ACER
 */
public class OknoKlient extends JFrame{
    private JLabel A,B,C,D,A1,A2,A3,A4,A5; 
    private static JTextField Login;
    private static JTextField Password;
    private static JTextField Name, Surname, Email, Password1, Login1;
    private JButton Pracownik;
    private JButton Create;
    private StaleKlienty Z =new StaleKlienty();
    private static boolean status;
    OknoKlient()
    {
        this.status=false;
    }
    OknoKlient(Baza X,TablicaPracownikow X1, Koszyk X2)
    {
        super(X.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,250);
        setLayout(null);
        add(A=new JLabel("Masz konto? Zaloguj się."));
        A.setBounds(5, 0,200,25);
        add(D=new JLabel("Rejestracja:"));
        D.setBounds(300, 0,250,25);
        add(B=new JLabel("Login:"));
        B.setBounds(85, 20,150,25);
        add(Login=new JTextField());
        Login.setBounds(30, 40,150,25);
        add(C=new JLabel("Hasło:"));
        C.setBounds(85, 60,150,25);
        add(Password=new JTextField());
        Password.setBounds(30, 80,150,25);
        add(A1=new JLabel("Imie:"));
        A1.setBounds(200, 20,150,25);
        add(A2=new JLabel("Nazwisko:"));
        A2.setBounds(200, 50,150,25);
        add(A3=new JLabel("Email:"));
        A3.setBounds(200, 80,150,25);
        add(A4=new JLabel("Hasło:"));
        A4.setBounds(200, 110,150,25);
        add(A5=new JLabel("Login:"));
        A5.setBounds(200, 140,150,25);
        add(Name=new JTextField());
        Name.setBounds(300, 20,150,25);
        add(Surname=new JTextField());
        Surname.setBounds(300, 50,150,25);
        add(Email=new JTextField());
        Email.setBounds(300, 80,150,25);
        add(Password1=new JTextField());
        Password1.setBounds(300, 110,150,25);
        add(Login1=new JTextField());
        Login1.setBounds(300, 140,150,25);
        add(Pracownik=new JButton("Zaloguj się"));
        Pracownik.setBounds(50, 170,100,25);
        add(Create=new JButton("Stworz konto"));
        Create.setBounds(250, 170,170,25);
        Pracownik.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    if(Login.getText().trim().length() == 0|| Password.getText().trim().length() == 0)
                    {
                        throw new MyException("Podaj login oraz haslo");
                    }
                    else
                    {
                        String a=Password.getText();
                        String b=Login.getText();
                        if(Z.Sprawdzenie(a, b)==false)
                        {
                            JOptionPane.showMessageDialog(null,"Nie znaleziono podanego klienta","Uwaga",JOptionPane.QUESTION_MESSAGE);
                        }
                        else
                        {
                            int a1=Z.Poszuk(a, b);
                            X2.SetKlient(Z.GetKlient(a1).GetName(),Z.GetKlient(a1).GetNazwisko() , Z.GetKlient(a1).GetEmail(), Z.GetKlient(a1).GetPassword(), Z.GetKlient(a1).GetLogin());
                            OknoKlient.SetStatus();
                            Z.Zapisz();
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
                            OknoWybor S=new OknoWybor(X,X1,X2,status);
                            S.setVisible(true);
                    }
                    }
                }
                catch(MyException ex)
                {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            } 
        } );
        Create.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                try
                {
                    if(Login1.getText().trim().length() == 0|| Password1.getText().trim().length() == 0||Name.getText().trim().length() == 0||Surname.getText().trim().length() == 0||Email.getText().trim().length() == 0)
                    {
                        throw new MyException("Prosze wypelnić wszyskie pola");
                    }
                    else if(!Email.getText().contains("@"))
                        throw new MyException("Podaj prawidowy email");
                    else
                    {
                        Z.DodajKlienta(Name.getText(), Surname.getText(), Email.getText(), Password1.getText(), Login1.getText());
                        X2.SetKlient(Name.getText(), Surname.getText(), Email.getText(), Password1.getText(), Login1.getText());
                        OknoKlient.SetStatus();
                        Z.Zapisz();
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
                        OknoWybor S=new OknoWybor(X,X1,X2,status);
                        S.setVisible(true);
                    }
                }
                catch(MyException ex)
                {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                
            } 
        } );
        setVisible(true);
    }
    static void SetStatus()
    {
        status=true;
    }
    boolean GetStatus()
    {
        return status;
    }
}
