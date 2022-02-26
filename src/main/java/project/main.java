/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import javax.swing.JFrame;

/**
 *
 * @author ACER
 */
public class main {
    public static void main(String[] args)
    {
        Baza A=new Baza();
        TablicaPracownikow A1 = new TablicaPracownikow(A);
        JFrame f;
        f = new OknoStart (A,A1);
    }
}
