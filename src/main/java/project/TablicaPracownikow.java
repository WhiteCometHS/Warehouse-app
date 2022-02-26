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
public class TablicaPracownikow {
    private Pracownik T[]=new Pracownik[3];
    public TablicaPracownikow(Baza X)
    {
            T[0]= new Pracownik("Admin","qwerty",X);
            T[1]= new Pracownik("Admin1","123456",X);
            T[2]= new Pracownik("Admin2","147258369",X);
    }
    int CheckT(String a, String b)
    {
        for(int i=0;i<3;i++)
        {
            if(a.equals(T[i].GetName()) && b.equals(T[i].GetPassword()))
                return i;
        }
        return -1;
    }
    Pracownik GetPracownik(int a)
    {
        return T[a];
    }
}
