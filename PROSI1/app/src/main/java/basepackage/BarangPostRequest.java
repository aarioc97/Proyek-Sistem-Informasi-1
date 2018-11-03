package basepackage;

import android.media.Image;

import com.google.firebase.storage.StorageReference;

/**
 * Created by user on 06/05/2018.
 */

public class BarangPostRequest extends Requester {
    int idBarang;
    int kodePR;
    int biaysAdmin;
    int jumlahPR;
    String statusPR;
    String statusPost;
    String jenisBarang;
    String namaBarang;
    String kodeBarang;
    String deskripsi;
    String statusPecahBelah;
    int berat;
    int harga;
    int panjang;
    int lebar;
    Image foto;
    StorageReference rf;

    public BarangPostRequest(int berat,String deskripsi,StorageReference foto, int harga, int lebar,
                             String namaBarang, int panjang, String statusPecahBelah,int jumlahPR){

        this.berat = berat;
        this.deskripsi = deskripsi;
        this.rf = foto;
        this.harga = harga;
        this.kodeBarang = "Request";
        this.lebar = lebar;
        this.namaBarang = namaBarang;
        this.panjang = panjang;
        this.statusPecahBelah = statusPecahBelah;
        this.jumlahPR = jumlahPR;

    }

}
