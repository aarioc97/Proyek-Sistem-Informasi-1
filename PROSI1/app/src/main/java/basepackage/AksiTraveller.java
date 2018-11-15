package basepackage;

import java.util.Date;

public class AksiTraveller {
    public String tanggal1, tanggal2;
    public String negara, aksi;
    public int id_traveller;

    public AksiTraveller(String negara, String tanggal1){
        this.aksi = "Pergi";
        this.tanggal1 = tanggal1;
        this.negara = negara;
        this.id_traveller = 9;
    }
}
