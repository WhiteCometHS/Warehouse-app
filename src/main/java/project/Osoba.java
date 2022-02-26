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
public class Osoba {
    private String name;
    private String password;
    public Osoba(String a, String b)
    {
        this.name=a;
        this.password=b;
    }
    String GetName()
    {
        return name;
    }
    String GetPassword()
    {
        return password;
    }
}
