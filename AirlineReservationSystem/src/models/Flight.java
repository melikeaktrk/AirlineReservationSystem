package models; // Flight sınıfını içeren models paketini tanımlar.

public class Flight {
    // Özel alanlar (fields) - Uçuş bilgilerini saklamak için değişkenler
    private String flightNumber; // Uçuş numarası
    private String destination; // Varış noktası
    private String departure; // Kalkış yeri
    private String departureTime; // Kalkış saati
    private int availableSeats; // Mevcut boş koltuk sayısı
    private double price; // Bilet fiyatı
    private String travelDuration; // Uçuş süresi

    // Constructor (Kurucu) - Yeni bir Flight nesnesi oluşturmak için kullanılır
    public Flight(String flightNumber, String destination, String departure, String departureTime, int availableSeats, double price, String travelDuration) {
        this.flightNumber = flightNumber; // Uçuş numarasını ata
        this.destination = destination; // Varış noktasını ata
        this.departure = departure; // Kalkış yerini ata
        this.departureTime = departureTime; // Kalkış saatini ata
        this.availableSeats = availableSeats; // Boş koltuk sayısını ata
        this.price = price; // Bilet fiyatını ata
        this.travelDuration = travelDuration; // Uçuş süresini ata
    }

    // Getter ve Setter metodları - Özel alanlara erişmek ve onları güncellemek için kullanılır
    public String getFlightNumber() { // Uçuş numarasını döner
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) { // Uçuş numarasını ayarlar
        this.flightNumber = flightNumber;
    }

    public String getDestination() { // Varış noktasını döner
        return destination;
    }

    public void setDestination(String destination) { // Varış noktasını ayarlar
        this.destination = destination;
    }

    public String getDeparture() { // Kalkış yerini döner
        return departure;
    }

    public void setDeparture(String departure) { // Kalkış yerini ayarlar
        this.departure = departure;
    }

    public String getDepartureTime() { // Kalkış saatini döner
        return departureTime;
    }

    public void setDepartureTime(String departureTime) { // Kalkış saatini ayarlar
        this.departureTime = departureTime;
    }

    public int getAvailableSeats() { // Boş koltuk sayısını döner
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) { // Boş koltuk sayısını ayarlar
        this.availableSeats = availableSeats;
    }

    public double getPrice() { // Bilet fiyatını döner
        return price;
    }

    public void setPrice(double price) { // Bilet fiyatını ayarlar
        this.price = price;
    }

    public String getTravelDuration() { // Uçuş süresini döner
        return travelDuration;
    }

    public void setTravelDuration(String travelDuration) { // Uçuş süresini ayarlar
        this.travelDuration = travelDuration;
    }
}
