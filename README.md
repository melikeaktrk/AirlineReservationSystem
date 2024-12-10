import models.Flight;
import models.Passenger;
import models.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AirlineReservationSystem {
    public static void main(String[] args) {
        ArrayList<Flight> flights = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        // Örnek Uçuşlar
        flights.add(new Flight("TK101", "Istanbul", "Ankara", "10:00", 150, 500.0, "1 saat"));
        flights.add(new Flight("TK102", "Istanbul", "Izmir", "14:00", 120, 400.0, "1 saat 30 dakika"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Uçak Bileti Rezervasyon Sistemi ---");
            System.out.println("1. Uçuşları Görüntüle");
            System.out.println("2. Rezervasyon Yap");
            System.out.println("3. Rezervasyon İptal Et");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = getIntInput(scanner);
            switch (choice) {
                case 1:
                    // Uçuşları görüntüle
                    for (Flight flight : flights) {
                        System.out.println("Uçuş No: " + flight.getFlightNumber() +
                                ", Nereden: " + flight.getDeparture() +
                                ", Nereye: " + flight.getDestination() +
                                ", Kalkış Saati: " + flight.getDepartureTime() +
                                ", Boş Koltuklar: " + flight.getAvailableSeats() +
                                ", Fiyat: " + flight.getPrice() + " TL" +
                                ", Süre: " + flight.getTravelDuration());
                    }
                    break;
                case 2:
                    // Rezervasyon yap
                    System.out.print("Lütfen uçuş numarasını girin: ");
                    String flightNo = scanner.next();
                    Flight selectedFlight = null;

                    for (Flight flight : flights) {
                        if (flight.getFlightNumber().equalsIgnoreCase(flightNo)) {
                            selectedFlight = flight;
                            break;
                        }
                    }

                    if (selectedFlight == null) {
                        System.out.println("Geçersiz uçuş numarası.");
                        break;
                    }

                    System.out.print("Kaç kişilik bilet alacaksınız? ");
                    int passengerCount = getIntInput(scanner);

                    if (selectedFlight.getAvailableSeats() < passengerCount) {
                        System.out.println("Yeterli koltuk yok!");
                        break;
                    }

                    ArrayList<Passenger> passengers = new ArrayList<>();
                    for (int i = 1; i <= passengerCount; i++) {
                        System.out.println("Yolcu " + i + ":");
                        System.out.print("Ad: ");
                        String firstName = scanner.next();
                        System.out.print("Soyad: ");
                        String lastName = scanner.next();
                        System.out.print("TC Kimlik No: ");
                        String tcId = scanner.next();
                        System.out.print("Cinsiyet (Bay/Bayan): ");
                        String gender = scanner.next();

                        passengers.add(new Passenger(firstName, lastName, tcId, gender));
                    }

                    System.out.print("Sınıf (Ekonomi/Business): ");
                    String seatClass = scanner.next();
                    System.out.print("Tarih (gg/aa/yyyy): ");
                    String travelDate = scanner.next();

                    if (!isValidDate(travelDate)) {
                        System.out.println("Geçersiz tarih formatı! Lütfen doğru formatta girin (gg/aa/yyyy).");
                        break;
                    }

                    double totalPrice = selectedFlight.getPrice() * passengerCount;
                    System.out.println("Toplam Tutar: " + totalPrice + " TL");
                    System.out.print("Ödeme yapınız: ");
                    double payment = getDoubleInput(scanner);

                    if (payment < totalPrice) {
                        System.out.println("Eksik ödeme yapıldı!");
                        break;
                    } else if (payment > totalPrice) {
                        System.out.println("Fazla ödeme yaptınız! Lütfen tam tutar ödeyin.");
                        break;
                    }

                    Reservation reservation = new Reservation(flightNo, passengers, seatClass, travelDate);
                    reservations.add(reservation);
                    selectedFlight.setAvailableSeats(selectedFlight.getAvailableSeats() - passengerCount);

                    System.out.println("Rezervasyon Başarıyla Oluşturuldu!");
                    System.out.println(reservation);
                    break;
                case 3:
                    // Rezervasyon iptal et
                    System.out.print("Rezervasyon ID girin: ");
                    String reservationId = scanner.next();
                    boolean removed = reservations.removeIf(r -> r.getReservationId().equals(reservationId));
                    if (removed) {
                        System.out.println("Rezervasyon iptal edildi.");
                    } else {
                        System.out.println("Rezervasyon bulunamadı.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Sistemden çıkıldı.");
                    break;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }

        scanner.close();
    }

    // Tarih doğrulama fonksiyonu
    private static boolean isValidDate(String travelDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date selectedDate = dateFormat.parse(travelDate);
            return !selectedDate.before(new Date());
        } catch (ParseException e) {
            return false;
        }
    }

    // Güvenli integer girişi
    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Geçersiz giriş! Bir sayı girin:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Güvenli double girişi
    private static double getDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Geçersiz giriş! Bir sayı girin:");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
