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
public class OknoBaza extends JFrame{
    private JLabel A,B;
    private JButton Powrot;
    private JButton Koszyk;
    private int y=25;
    private JButton[] myButtons;
    private JLabel[] myLabels;
    private JTextField[] amounts;
    private Koszyk A1=new Koszyk();
    OknoBaza(Baza X, TablicaPracownikow X1)
    {
        super(X.GetName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setLayout(null);
        add(A=new JLabel("LISTA TOWARÓW SKLEPU"));
        A.setBounds(150,5,500,15);
        add(B=new JLabel("nazwa | opakowanie | dostępna ilosc towaru | cena za szt."));
        B.setBounds(15,y,500,15);
        y+=30;
        myLabels = new JLabel[X.GetSize()];
        myButtons = new JButton[X.GetSize()];
        amounts = new JTextField[X.GetSize()];
        for(int i=0;i<X.GetSize();i++)
        {
            myLabels[i]= new JLabel(X.GetTowar(i).wypiszT1()+" zl");
            add(myLabels[i]);
            myLabels[i].setBounds(10, y,250,15);
            amounts[i]= new JTextField(5);
            add(amounts[i]);
            amounts[i].setBounds(250, y,60,25);
            myButtons[i] = new JButton("Zamów");
            myButtons[i].setBounds(350, y,100,25);
            add(myButtons[i]);
            y+=30;
            myButtons[i].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) 
            {
                for(int i=0;i<myButtons.length;i++)
                {
                    if(myButtons[i].equals(e.getSource()))
                    {
                        try
                        {
                            if(amounts[i].getText().trim().length() == 0 || Integer.parseInt(amounts[i].getText())>X.GetTowar(i).GetAvailable()|| Integer.parseInt(amounts[i].getText())<=0) 
                            {
                                amounts[i].setText("");
                                throw new MyException("Taka ilość towaru jest niedostępna");
                            }
                            A1.DodajT(myLabels[i].getText(),Integer.parseInt(amounts[i].getText()));
                            JOptionPane.showMessageDialog(null,"Towar został dodany do koszyka","Informacja",JOptionPane.QUESTION_MESSAGE);
                            amounts[i].setText("");
                            break;
                        }
                        catch(MyException ex)
                        {
                            amounts[i].setText("");
                            JOptionPane.showMessageDialog(null,ex.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
                        }
                        catch(NumberFormatException ex)
                        {
                            amounts[i].setText("");
                            JOptionPane.showMessageDialog(null,"Niepoprawy format, sprobuj ponowninie","Uwaga",JOptionPane.QUESTION_MESSAGE);
                        }
                    }
                }
                
            } 
            });
        }
        add(Koszyk=new JButton("Koszyk"));
        Koszyk.setBounds(170, y,100,25);
        y+=30;
        add(Powrot=new JButton("Powrót"));
        Powrot.setBounds(170, y,100,25);
        Powrot.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                OknoStart S=new OknoStart(X,X1);
                setVisible(false);
                S.setVisible(true);
            } 
        } );
        Koszyk.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) 
            {
                OknoKoszyk S=new OknoKoszyk(X,X1,A1);
                setVisible(false);
                S.setVisible(true);
            } 
        } );
        setVisible(true);
    }
}
