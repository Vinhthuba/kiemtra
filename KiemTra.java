import java.sql.*;
import java.util.Scanner;

public class KiemTra {
    public static void main(String[] args) throws SQLException {
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/KiemTra", "root", "");
                PreparedStatement pstmtInsert = conn.prepareStatement(
                        "insert into phim values (?, ?, ?, ?, ?)");
                PreparedStatement pstmtUpdate = conn.prepareStatement(
                        "update phim set ma = ? where ten  = ?");
                PreparedStatement pstmtDelete = conn.prepareStatement(
                        "delete from phim where ten = ?");
                PreparedStatement pstmtSelect = conn.prepareStatement(
                        "select * from phim where ten = ?");
        ) {
            conn.setAutoCommit(false);
            try {
                int type;
                String ma;
                String ten;
                String thoigian;
                String auhtor;
                int numberMinutes;
                Scanner ip = new Scanner(System.in);
                do {
                    System.out.println("1.Them\n2.Sua\n3.Xoa\n4.Tim kiem");
                    type = ip.nextInt();
                    if (type == 1 ){
                        System.out.println("Them phim");
                        ip.nextLine();
                        System.out.println("\nNhap ma phim: ");
                        ma = ip.nextLine();
                        System.out.println("\nNhap ten phim: ");
                        ten = ip.nextLine();
                        System.out.println("\nNhap thoi gian chieu phim:  ");
                        thoigian = ip.nextLine();
                        System.out.println("\nNhap ten tac gia: ");
                        auhtor= ip.nextLine();
                        System.out.println("\nNhap thoi gian : ");
                        numberMinutes = ip.nextInt();
                        System.out.println("Them phim thanh cong");
                        pstmtInsert.setString(1, ma);
                        pstmtInsert.setString(2, ten);
                        pstmtInsert.setString(3, thoigian);
                        pstmtInsert.setString(4, auhtor);
                        pstmtInsert.setInt   (5, numberMinutes);
                        pstmtInsert.executeUpdate();

                    }
                    else if (type == 2){
                        System.out.println("Ban muon sua thong tin phim?");
                        ip.nextLine();
                        System.out.println("Nhap ten phim muon sua: ");
                        ten = ip.nextLine();
                        System.out.println("Nhap ma ban muon doi");
                        ma = ip.nextLine();
                        pstmtUpdate.setString(1,  ma);
                        pstmtUpdate.setString(2,  ten);
                        pstmtUpdate.executeUpdate();
                    }
                    else if (type == 3){
                        System.out.println("Xoa phim");
                        ip.nextLine();
                        System.out.println("\nNhap ten phim ban muon xoa: ");
                        ten = ip.nextLine();
                        System.out.println("Da xoa");
                        pstmtDelete.setString(1, ten);
                        pstmtDelete.executeUpdate();
                    }
                    else if (type == 4){
                        System.out.println("Tim kiem thong tin ve phim");
                        ip.nextLine();
                        System.out.println("\nNhap ten phim muon tim: ");
                        ten = ip.nextLine();
                        pstmtSelect.setString(1, ten);
                        ResultSet rset = pstmtSelect.executeQuery();
                        while (rset.next()){
                            System.out.println(rset.getString("ma") + ", "
                                    + rset.getString("ten") + ", "
                                    + rset.getString("thoigian") + ", "
                                    + rset.getString("auhtor") + ", "
                                    + rset.getInt("numberMinutes"));
                        }


            }
                    conn.commit();
                    System.out.println("Chon 5 de ket thuc");
                    type = ip.nextInt();
                    if (type==5)
                    {
                        break;
                    }
                }while (type != 1 || type != 2 || type != 3 || type != 4 || type != 5);

            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
            }
        }
    }
}