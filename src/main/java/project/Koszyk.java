/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class Koszyk{
    private Towar koszyk[]=new Towar[1000];
    private float Suma=0;
    private int size=0;
    private boolean status;
    private Klient A;
    void DodajT(String a,int b1)
    {
        int counter=0,spec=0;
        String a1="",b="";
        int c=0;
        float d=0;
        for (String retval : a.split("\\ +")) {
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
        koszyk[size]=new Towar(a1,b,c,d);
        koszyk[size].SetAvailable(b1);
        if(b1>=10)
        {
            this.status=true;
            d=d-(float)(d*0.03);
            Suma+=koszyk[size].GetAvailable()*d;
            size++;
        }
        else
        {
            Suma+=koszyk[size].GetAvailable()*d;
            size++;
        }
    }
    int Porownanie(Towar A)
    {
        for(int i=0;i<size;i++)
        {
            if(koszyk[i].Getname().equals(A.Getname())&& koszyk[i].GetPackadge().equals(A.GetPackadge())&& koszyk[i].GetPrice()==A.GetPrice())
            {
                return koszyk[i].GetAvailable();
            }
        }
        return -1;
    }
    void RemoveT(int a)
    {
        Suma-=(koszyk[a].GetPrice()*koszyk[a].GetAvailable());
        for(int i=a;i<size-1;i++)
        {
            koszyk[i]=koszyk[i+1];
        }
        this.size--;
    }
    void SetKlient(String a,String b,String c,String d,String e)
    {
        this.A=new Klient(a,b,c,d,e);
    }
    Klient GetKlient()
    {
        return A;
    }         
    boolean GetStatus()
    {
        return status;
    }
    int GetSize()
    {
        return size;
    }
    float GetSuma()
    {
        return Suma;
    }
    String Wypisz(int a)
    {
        return koszyk[a].wypiszT1();
    }
    
}
