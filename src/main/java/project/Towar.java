/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author ACER
 */
public class Towar {
    private String name;
    private String packadge;
    private float price;
    private int available;
    public Towar(String a, String b, int c, float d)
    {
        this.name=a;
        this.packadge=b;
        this.price=d;
        this.available=c;
    }
    void SetAvailable(int a)
    {
        this.available=a;
    }
    String Getname()
    {
        return name;
    }
    String GetPackadge()
    {
        return packadge;
    }
    float GetPrice()
    {
        return price;
    }
    int GetAvailable()
    {
        return available;
    }
    String wypiszT1()
    {
        return name+" "+packadge+" "+available+" "+price;
    }
    String wypiszT()
    {
        return name+"\t"+packadge+"\t"+available+"\t"+price+"\n";
    }
    boolean isInt(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    boolean isFloat(String s) throws NumberFormatException {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    boolean poszukT(String a)
    {
        if(a.equals(name)||a.equals(packadge))
        {
            return true;
        }
        else if(isInt(a)==true)
        {
            int a1=Integer.parseInt(a);
            if(available==a1)
                return true;
        }
        else if(isFloat(a)==true)
        {
            float a1=Float.parseFloat(a);
            if(price==a1)
                return true;
        }
        return false;
    }
}
