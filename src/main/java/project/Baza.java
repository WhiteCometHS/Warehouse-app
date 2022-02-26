/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class Baza{
    private String name;
    private Towar baza[]=new Towar[1000];
    private int size=0;
    public Baza()
    {
        try
        {
            this.name="Sklep towarow";
            Scanner s = new Scanner(new File("dane.txt"));
            s.useDelimiter("\n");
            String a1="",b="";
            int c=0;
            float d=0;
            while(s.hasNext())
            {
                String a=s.next();
                int counter=0;
                for (String retval : a.split("\\t+")) {
                    counter++;
                    if(counter==1)
                    {
                        a1=retval;
                    }
                    if(counter==2)
                    {
                        b=retval;
                    }
                    if(counter==3)
                    {
                        c = Integer.parseInt(retval);
                    }
                    if(counter==4)
                    {
                        d = Float.parseFloat(retval);
                    }
                }
                if(a=="") throw new MyException("Plik jest pusty");
                baza[size]=new Towar(a1,b,c,d);
                size++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Blad");
        }
        catch(MyException e)
        {
            System.out.println(e.getMessage());
        }      
    }
    String GetName()
    {
        return name;
    }
    int GetSize()
    {
        return size;
    }
    void SetSize()
    {
        this.size--;
    }
    Towar GetTowar(int a)
    {
        return baza[a];
    }
    void SetTowar(int a, Towar A)
    {
        baza[a]=A;
    }
    void SetTowar(int a, String a1,String b,int c, float d)
    {
        baza[a]=new Towar(a1,b,c,d);
    }
    void wypisz(int i)
    {
        baza[i].wypiszT();
    }
    void Zapisz()
    {
        try
        {
            FileWriter w = new FileWriter("dane.txt");
            for(int i=0;i<size;i++)
            {
                String a=baza[i].wypiszT();
                w.write(a);
            }
            w.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"Błąd odczytu danych dla wplatowatu","Uwaga",JOptionPane.QUESTION_MESSAGE);
        }
    }
    void dodajT()
    {
        int index=-1;
        boolean czypoprawne=false;
        while(!czypoprawne)
        {
            try
            {
                String a = (JOptionPane.showInputDialog(null,"Podaj nazwę towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                if(a.length()==0) throw new MyException("Podane pole musi byc wypelnione");
                String b = (JOptionPane.showInputDialog(null,"Podaj typ opakowania towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                if(b.length()==0) throw new MyException("Podane pole musi byc wypelnione");
                int c = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj ilość towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                float d = Float.parseFloat(JOptionPane.showInputDialog(null,"Podaj cenę towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                baza[size]=new Towar(a,b,c,d);
                size++;
                JOptionPane.showMessageDialog(null,"Dane zostaly dodane","Informacja",JOptionPane.QUESTION_MESSAGE);
                index=0;
            }
            catch(MyException e)
            {
                JOptionPane.showMessageDialog(null,e.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
            } 
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null,"Niepoprawy format, sprobuj ponowninie","Uwaga",JOptionPane.QUESTION_MESSAGE);
            }
            czypoprawne=index==-1?false:true;
        }
    }
    void usunT(Baza X)
    {
        int counter=0;
        Towar Temp[]=new Towar[1000];
        String a = (JOptionPane.showInputDialog(null,"Podaj dowolna informacje o towarze:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
        for(int i=0;i<size;i++)
        {
            if(baza[i].poszukT(a)==true)
            {
                counter++;
                Temp[counter-1]=baza[i];
            }
        }
        if(counter>1)
        {
            OknoPracSpec A=new OknoPracSpec(X,Temp,counter,a,true);
        }
        else if(counter==0)
        {
            JOptionPane.showMessageDialog(null,"Nic nie znalieziono","Uwaga",JOptionPane.QUESTION_MESSAGE);
        }
        else
        {
            for(int i=0;i<size;i++)
            {
                if(baza[i]==Temp[0])
                {
                    JOptionPane.showMessageDialog(null,"Znalieziono: "+baza[i].wypiszT1(),"informacja",JOptionPane.QUESTION_MESSAGE);
                    for(int j=i;j<size-1;j++)
                    {
                        baza[j]=baza[j+1];
                    }
                    size--;
                    JOptionPane.showMessageDialog(null,"Dane zostaly usunięte","informacja",JOptionPane.QUESTION_MESSAGE);
                    break;
                }
            }
        }
    }
    void edit(Baza X)
    {
        int counter=0;
        Towar Temp[]=new Towar[1000];
        Scanner s=new Scanner(System.in);
        String a = (JOptionPane.showInputDialog(null,"Podaj dowolna informacje o towarze:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
        for(int i=0;i<size;i++)
        {
            if(baza[i].poszukT(a)==true)
            {
                counter++;
                Temp[counter-1]=baza[i];
            }
        }
        if(counter>1)
        {
            OknoPracSpec A=new OknoPracSpec(X,Temp,counter,a,false);
        }
        else if(counter==0)
        {
            JOptionPane.showMessageDialog(null,"Nic nie znalieziono","Uwaga",JOptionPane.QUESTION_MESSAGE);
        }
        else
        {
            int index=-1;
            boolean czypoprawne=false;
            while(!czypoprawne)
            {
                try
                {
                    a = (JOptionPane.showInputDialog(null,"Podaj nazwe towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                    if(a.length()==0) throw new MyException("Podane pole musi byc wypelnione");
                    String b = (JOptionPane.showInputDialog(null,"Podaj typ opakowania towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                    if(b.length()==0) throw new MyException("Podane pole musi byc wypelnione");
                    int c = Integer.parseInt(JOptionPane.showInputDialog(null,"Podaj ilosc towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                    float d = Float.parseFloat(JOptionPane.showInputDialog(null,"Podaj cene towaru:","Wprowadzenie danych",JOptionPane.QUESTION_MESSAGE));
                    for(int i=0;i<size;i++)
                    {
                        if(baza[i]==Temp[0])
                        {
                            baza[i]=new Towar(a,b,c,d);
                            break;
                        }
                    }
                    index=0;
                }
                catch(MyException e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage(),"Uwaga",JOptionPane.QUESTION_MESSAGE);
                } 
                catch(NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null,"Niepoprawy format, sprobuj ponowninie","Uwaga",JOptionPane.QUESTION_MESSAGE);
                }
                czypoprawne=index==-1?false:true;
            }           
        }
    }
}
