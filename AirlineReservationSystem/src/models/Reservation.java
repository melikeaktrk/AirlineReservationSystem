package models; // Rezervasyon sınıfını içeren models paketini tanımlar.

import java.util.ArrayList; // Yolcu bilgilerini saklamak için dinamik liste.

public class Reservation {
    private static int idCounter = 1; // Rezervasyon için benzersiz ID üreten sayaç.
    private String reservationId; // Her rezervasyon için benzersiz ID.
    private String flightNumber; // Rezervasyon yapılan uçuşun numarası.
    private ArrayList<Passenger> passengers; // Rezervasyonda yer alan yolcuların listesi.
    private String seatClass; // Koltuk sınıfı: "Ekonomi" veya "Business".
    private String travelDate; // Rezervasyonun seyahat tarihi.

    // Constructor (Kurucu metot)
    public Reservation(String flightNumber, ArrayList<Passenger> passengers, String seatClass, String travelDate) {
        this.reservationId = "R" + idCounter++; // Benzersiz bir rezervasyon ID'si oluşturulur.
        this.flightNumber = flightNumber; // Uçuş numarası atanır.
        this.passengers = passengers; // Yolcular listesi atanır.
        this.seatClass = seatClass; // Koltuk sınıfı atanır.
        this.travelDate = travelDate; // Seyahat tarihi atanır.
    }

    // Getters - Özel değişkenlere erişmek için kullanılır.
    public String getReservationId() { // Rezervasyon ID'sini döndürür.
        return reservationId;
    }

    public String getFlightNumber() { // Uçuş numarasını döndürür.
        return flightNumber;
    }

    public ArrayList<Passenger> getPassengers() { // Yolcuların listesini döndürür.
        return passengers;
    }

    public String getSeatClass() { // Koltuk sınıfını döndürür.
        return seatClass;
    }

    public String getTravelDate() { // Seyahat tarihini döndürür.
        return travelDate;
    }

    // Rezervasyon detaylarını metin formatında döndüren metod
    @Override
    public String toString() {
        StringBuilder details = new StringBuilder("Rezervasyon ID: " + reservationId + // Rezervasyon ID'sini ekler.
                "\nUçuş No: " + flightNumber + // Uçuş numarasını ekler.
                "\nSınıf: " + seatClass + // Koltuk sınıfını ekler.
                "\nTarih: " + travelDate + // Seyahat tarihini ekler.
                "\nYolcular:\n"); // Yolcular için başlık ekler.

        for (Passenger passenger : passengers) { // Yolcuların listesini döner.
            details.append(" - ").append(passenger.toString()).append("\n"); // Her bir yolcunun detayını ekler.
        }

        return details.toString(); // Tüm rezervasyon detaylarını döndürür.
    }
}
