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
public class Klient extends Osoba{
    private String nazwisko;
    private String email;
    private String login;
    public Klient(String a,String b,String c,String d,String e)
    {
        super(a,d);
        this.nazwisko=b;
        this.email=c;
        this.login=e;
    }
    String GetNazwisko()
    {
        return nazwisko;
    }
    String GetEmail()
    {
        return email;
    }
    String GetLogin()
    {
        return login;
    }
    String Wypisz()
    {
        return GetName()+" "+nazwisko+" "+email+" "+GetPassword()+" "+login+"\n";
    }
    String WypiszP()
    {
        return GetName()+" "+nazwisko+" "+email+"\n";
    }
    String WypiszP1()
    {
        return GetName()+" "+nazwisko+" "+email;
    }
}
