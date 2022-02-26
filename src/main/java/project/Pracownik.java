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
public class Pracownik extends Osoba{
    private String password;
    private Baza A;
    public Pracownik(String a, String b,Baza A)
    {
        super(a,b);
        this.A=A;
    }
    void dodajT()
    {
        A.dodajT();
    }
    void usunT()
    {
        A.usunT(A);
    }
    void edit()
    {
        A.edit(A);
    }
}
