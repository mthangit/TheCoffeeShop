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
public class FileManager {
    public static void deletePdfFile(String id) {
    String filePath = "D:\\Bill\\" + id + ".pdf";
    File pdfFile = new File(filePath);
    try{
            if(pdfFile.exists()){
                pdfFile.delete();
                JOptionPane.showMessageDialog(null,"Xoa thanh cong");
            }
            else JOptionPane.showMessageDialog(null,"File khong ton tai");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void openPdfFile(String id){
        String filePath = "D:\\Bill\\" + id + ".pdf";
        File pdfFile = new File(filePath);
        try{
            if(pdfFile.exists()){
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filePath);
            }
            else JOptionPane.showMessageDialog(null,"File khong ton tai");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
    
