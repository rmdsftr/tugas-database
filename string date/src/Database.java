import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL_JDBC = "jdbc:mysql://localhost/jdbc";
    private static final String user = "root";
    private static final String password = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC tidak ditemukan. Pastikan Anda telah menambahkan driver JDBC ke classpath.", e);
        }
    }

    public static void insertTransaksi(Penjualan penjualan) {
        try (Connection connection = DriverManager.getConnection(URL_JDBC, user, password)) {
            String query = "INSERT INTO pelanggan (faktur, namaPelanggan, noHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli, totalBayar, namaKasir) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, penjualan.faktur);
                preparedStatement.setString(2, penjualan.namaPelanggan);
                preparedStatement.setString(3, penjualan.noHp);
                preparedStatement.setString(4, penjualan.alamat);
                preparedStatement.setString(5, penjualan.kodeBarang);
                preparedStatement.setString(6, penjualan.namaBarang);
                preparedStatement.setInt(7, penjualan.hargaBarang);
                preparedStatement.setInt(8, penjualan.jumlahBeli);
                preparedStatement.setInt(9, penjualan.totalBayar);
                preparedStatement.setString(10, penjualan.namaKasir);
    
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void retrieveTransaksi() {
    try (Connection connection = DriverManager.getConnection(URL_JDBC, user, password)) {
        String query = "SELECT * FROM pelanggan";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Process and display the retrieved data
                System.out.println("Faktur: " + resultSet.getString("faktur"));
                System.out.println("Nama Pelanggan: " + resultSet.getString("namaPelanggan"));
                // ... (repeat for other fields)
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public static void updateTransaksi(String faktur, int newTotalBayar) {
        try (Connection connection = DriverManager.getConnection(URL_JDBC, user, password)) {
            String query = "UPDATE pelanggan SET totalBayar = ? WHERE faktur = ?";
    
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, newTotalBayar);
                preparedStatement.setString(2, faktur);
    
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTransaksi(String faktur) {
        try (Connection connection = DriverManager.getConnection(URL_JDBC, user, password)) {
            String query = "DELETE FROM pelanggan WHERE faktur = ?";
    
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, faktur);
    
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    
    
}

    
}
