/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sijamal.model;

/**
 *
 * @author louis
 */
public class Alat {
    private String id;
    private String namaKategori;
    private String namaAlat;
    private int jumlah;
    
    public Alat(){
        
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id =  id;
    }
    
    public String getNamaKategori(){
        return namaKategori;
    }
    
    public void setNamaKategori(String namaKategori){
        this.namaKategori =  namaKategori;
    }
    
    public String getNamaAlat(){
        return namaAlat;
    }
    
    public void setNamaAlat(String namaAlat){
        this.namaAlat =  namaAlat;
    }
    
    public int getJumlah(){
        return jumlah;
    }
    
    public void setJumlah(int jumlah){
        this.jumlah =  jumlah;
    }
}
