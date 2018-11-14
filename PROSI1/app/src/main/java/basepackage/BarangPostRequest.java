package basepackage;
import android.media.Image;

import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

/**
 * Created by user on 06/05/2018.
 */

public class BarangPostRequest extends Requester {
    public String idBarang;
    public int kodePR;
    public int biayaAdmin;
    public int jumlahPR;
    public String statusPR;
    public String statusPost;
    public String jenisBarang;
    public String namaBarang;
    public String kodeBarang;
    public String deskripsi;
    public String statusPecahBelah;
    public int berat;
    public int harga;
    public int panjang;
    public int lebar;
    public StorageReference rf;
    public String URLFoto;

    public BarangPostRequest(String idBarang, int berat,String deskripsi,String foto, int harga, int lebar,
                             String namaBarang, int panjang, String statusPecahBelah,int jumlahPR,int biayaAdmin){

        this.idBarang = idBarang;
        this.berat = berat;
        this.deskripsi = deskripsi;
        this.URLFoto = foto;
        this.harga = harga;
        this.kodeBarang = "Request";
        this.lebar = lebar;
        this.namaBarang = namaBarang;
        this.panjang = panjang;
        this.statusPecahBelah = statusPecahBelah;
        this.jumlahPR = jumlahPR;
        this.biayaAdmin = biayaAdmin;

    }

    public String getName() {
        return namaBarang;
    }

    public void setName(String name) {
        namaBarang = name;
    }

    public String getImageUrl() {
        return URLFoto;
    }

    public void setImageUrl(String imageUrl) {
        URLFoto = imageUrl;
    }


}
