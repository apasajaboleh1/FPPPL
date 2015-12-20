/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Harga_Tol;
import model.History_Transaksi;
import model.History_topup;
import model.Operator;
import model.Pengguna;

/**
 *
 * @author Freddy
 */
public class ControlData {
    private static int dataoperator;
    private static int[] datatol={0,0,0};
    private static int[] datapengguna={0,0,0};
    public static String prosesdata(boolean[] kategori,String asal,String tujuan,String input)
    {
        String hasil=null;
        int i=0;
        while(i<kategori.length)
        {
            if(kategori[i]==true)break;
            else i++;
        }
        
        //1. kita harus dapat data dari harga dan idnya dari table harga_tol,
        datatol=Harga_Tol.gethargaandid(++i, tujuan, asal);
        
        //2. kita harus ngambil data saldo dan idnya orang itu... dari gambarnya. nti gambar ubah k string
        datapengguna=Pengguna.getidandsaldo(input);
        
        //3. update saldonya dan nantinya kirimkan ke printer (lek iso printernya atau kalau tidak keluar soft copynya saja)
        boolean hasilupdatedata=Pengguna.updatesaldo(datapengguna[0],datatol[1],datapengguna[1]);
        boolean masukdata=History_Transaksi.masukdata(datapengguna[0],datatol[0],dataoperator);
        //4.slesai. ulangi terus.....
        if(hasilupdatedata&&masukdata)
        {
            int sisa_saldo=Pengguna.getsaldo(datapengguna[0]);
            hasil="Sisa saldo anda sebesar : "+sisa_saldo+"\npemakaian sebesar : "+datatol[1]+"\ntujuan : "+tujuan+"\nasal : "+asal+"\n";
        }
        else
            hasil="maaf,saldo anda tidak cukup\n";
        
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("hasilprosesdata.pdf"));
         document.open();
         document.add(new Paragraph(hasil));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
        
        return hasil;
    }
    public static String bayar_tunai(int duit,boolean[] kategori,String asal,String tujuan)
    {
        String hasil=null;
        datapengguna[0]=1;
        if(datatol[0]==0)
        {
            int i=0;
                while(i<kategori.length)
            {
                if(kategori[i]==true)break;
                else i++;
             }
            datatol=Harga_Tol.gethargaandid(++i, tujuan, asal);
        }
        boolean masukdata=History_Transaksi.masukdata(datapengguna[0],datatol[0],dataoperator);
        //4.slesai. ulangi terus.....
        if(masukdata && (duit-datatol[1])>=0)
        {
            hasil="kembalian : "+(duit-datatol[1])+"\npemakaian sebesar : "+datatol[1]+"\ntujuan : "+tujuan+"\nasal : "+asal+"\n";
        }
        else
            hasil="Error \n";
        
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("hasilprosesdata.pdf"));
         document.open();
         document.add(new Paragraph(hasil));
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
        return hasil;
    }
    public static boolean login(String usr,String pwd)
    {
        boolean hasil=false;
        int temp=Operator.loginuser(usr, pwd);
        if(temp!=0)
        {
            //System.out.print(hasil);
            hasil=true;
            dataoperator=temp;
        }
        return hasil;
    }
    public static boolean topup(String input,int saldo)
    {
        boolean hasil=false;
        if(saldo!=0)
        {
            hasil=History_topup.topup(input, saldo);
            Document document = new Document();
            try
            {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("topup.pdf"));
                document.open();
                document.add(new Paragraph("Top Up anda sebesar Rp." + saldo +"\n Telah Berhasil."));
                document.close();
                writer.close();
            } catch (DocumentException ex) {
                Logger.getLogger(ControlData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControlData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return hasil;
    }
    public static boolean updatehargatol(int gol, int harga, String tujuan, String asal)
    {
        boolean hasil=false;
        if(gol!=0&&harga!=0)
        hasil=Harga_Tol.updateharga(gol, tujuan, asal, harga);
        return hasil;
    }
}
