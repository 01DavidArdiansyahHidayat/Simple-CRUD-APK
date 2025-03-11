import com.sun.jdi.InconsistentDebugInfoException;

import java.util.Scanner;

public class Userinterface {

    public static void tampilkanData() {
        System.out.println();
        System.out.println("+==========================+");
        System.out.println("|        Pilih Menu:       |");
        System.out.println("+--------------------------+");
        System.out.println("|   [C] : Create           |");
        System.out.println("|   [R] : Read             |");
        System.out.println("|   [U] : Update           |");
        System.out.println("|   [D] : Delete           |");
        System.out.println("|   [X] : Exit             |");
        System.out.println("+==========================+");
    }

    public static void main(String[] args) {
        Database db = new Database();
        System.out.println("APLIKASI SIMPLE CRUD TEXT DATABASE");
        while(true) {
            tampilkanData();
            Scanner sc = new Scanner(System.in);
            System.out.print("Pilih : ");
            String pilihan = sc.nextLine();

            switch (pilihan) {
                case "C":
                    System.out.println("INFO : Anda Memilih Menu Create");
                    System.out.print("NIM MAHASISWA : ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA MAHASISWA : ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT : ");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER : ");
                    int semester = sc.nextInt();
                    System.out.print("SKS : ");
                    int sks = sc.nextInt();
                    System.out.print("IPK : ");
                    double ipk = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("-------------------------------------------------------");

                    boolean status = db.insert(nim, nama, alamat, semester, sks, ipk);
                    if (status==true) {
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    }else {
                        System.out.println("NIM " + nim + "Sudah Ada Di Database");
                        System.out.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    break;

                case "R":
                    System.out.println("INFO : Anda Memilih Menu Read");
                    db.view();
                    break;

                case "U":
                    System.out.println("INFO : Anda Memilih Menu Update");
                    System.out.print("Input Key (NIM Mahasiswa yang akan diUpdate): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0) {
                        System.out.println("Apakah Anda Akan Meng-Update Data?" + db.getData().get(index));
                        System.out.print("NIM               : ");
                        nim = sc.nextLine();
                        System.out.print("NAMA              : ");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT            : ");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER          : ");
                        semester = sc.nextInt();
                        System.out.print("SKS               : ");
                        sks = sc.nextInt();
                        System.out.print("IPK               : ");
                        ipk = sc.nextDouble();
                        System.out.println("------------------------------------------------------");
                        status = db.update(index, nim, nama, alamat, semester, sks, ipk);
                                if (status==true) {
                                    System.out.println("DATA BERHASIL DIPERBAHARUI");
                                }else {
                                    System.err.println("GAGAL MEMPERBAHARUI DATA");
                                }
                    }else {
                        System.err.println("Mahasiswa dengan NIM" + key + "Tidak Ada Di Database");
                    }
                    break;

                case "D":
                    System.out.println("INFO : Anda Memilih Menu Delete");
                    db.view();
                    System.out.println("Input Key (NIM Mahasiswa Yang Akan Dihapus): ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index >= 0) {
                        System.out.println("APAKAH ANDA YAKIN AKAN MENGHAPUS DATA INI?" + db.getData().get(index));
                        System.out.print("Pilih : ");
                        pilihan = sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")) {
                            status = db.delete(index);
                            if (status==true) {
                                System.out.println("DATA BERHASIL DIHAPUS");
                            }else {
                                System.out.println("GAGAL MENGHAPUS DATA");
                            }
                        }
                    }else {
                        System.err.println("Mahasiswa dengan NIM" + key + "Tidak Ada Di Database");
                    }
                    break;

                case "X":
                    System.out.println("INFO : Anda Memilih Menu EXIT");
                    System.out.println("APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI INI? Y/N");
                    System.out.print("Pilih : ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")) {
                        System.exit( 0);
                    }
                    break;
            }
        }
    }
}
