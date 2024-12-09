package models; // Passenger sınıfını içeren models paketini tanımlar.

public class Passenger {
    // Yolcu bilgilerini saklayan özel alanlar (fields)
    private String firstName; // Yolcunun adı
    private String lastName; // Yolcunun soyadı
    private String tcId; // Yolcunun TC kimlik numarası
    private String gender; // Yolcunun cinsiyeti (Bay/Bayan)

    // Constructor (Kurucu Metot) - Yeni bir yolcu oluşturmak için kullanılır.
    public Passenger(String firstName, String lastName, String tcId, String gender) {
        this.firstName = firstName; // Yolcunun adını ata
        this.lastName = lastName; // Yolcunun soyadını ata
        this.tcId = tcId; // Yolcunun TC kimlik numarasını ata
        this.gender = gender; // Yolcunun cinsiyetini ata
    }

    // Getter metotlar - Özel alanlara dışarıdan erişmek için kullanılır.
    public String getFirstName() { // Yolcunun adını döndürür.
        return firstName;
    }

    public String getLastName() { // Yolcunun soyadını döndürür.
        return lastName;
    }

    public String getTcId() { // Yolcunun TC kimlik numarasını döndürür.
        return tcId;
    }

    public String getGender() { // Yolcunun cinsiyetini döndürür.
        return gender;
    }

    // Yolcunun bilgilerini metin formatında döndür
    @Override
    public String toString() {
        // Yolcu bilgilerini birleştirerek anlamlı bir metin oluşturur.
        return firstName + " " + lastName + " (TC: " + tcId + ", Cinsiyet: " + gender + ")";
    }
}
