package kelas.b.projectakhir;

public class User {
    private String id, nik, nama, day;

    public User(){

    }

    public User(String nik, String nama, String day) {
        this.nik = nik;
        this.nama = nama;
        this.day = day;
    }
    public String getId(){
        return  id;
    }
    public void setId(){
        this.id = id;
    }

    public String getNik() {
        return nik;
    }
    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }


}
