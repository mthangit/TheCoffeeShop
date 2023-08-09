/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author HUY THUC
 */
public class OpenFilePdf {
    public static void openById(String id){
        try{
            if((new File("D:\\Bill\\"+id+".pdf")).exists()){
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\Bill\\"+id+".pdf");
            }
            else JOptionPane.showMessageDialog(null,"File khong ton tai");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
