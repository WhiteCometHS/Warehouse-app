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
public class OknoKlientySpec extends JFrame{
    private JLabel A;
    private JButton Powrot;
    private int y=25;
    private JLabel[] myLabels;
    OknoKlientySpec(Baza X1)
    {
        super(X1.GetName());
        StaleKlienty X=new StaleKlienty();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,800);
        setLayout(null);
        myLabels = new JLabel[X.GetSize()];
        A=new JLabel("Stale klienty:");
        add(A);
        A.setBounds(190, 10, 250, 15);
        for(int i=0;i<X.GetSize();i++)
        {
                myLabels[i]= new JLabel(i+1+" - "+X.GetKlient(i).WypiszP1());
                add(myLabels[i]);
                myLabels[i].setBounds(10, y,300,15);
                y+=30;
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
