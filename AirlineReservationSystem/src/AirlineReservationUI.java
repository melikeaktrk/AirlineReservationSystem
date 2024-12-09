import models.Flight;
import models.Passenger;
import models.Reservation;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AirlineReservationUI {
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public AirlineReservationUI() {
    	// Örnek Uçuşlar
    	flights.add(new Flight("TK101", "Istanbul", "Ankara", "10:00", 150, 1500.0, "1 saat"));
    	flights.add(new Flight("TK102", "Istanbul", "Izmir", "14:00", 120, 1400.0, "1 saat 30 dakika"));
    	flights.add(new Flight("TK103", "Ankara", "Istanbul", "09:00", 100, 1550.0, "1 saat"));
    	flights.add(new Flight("TK104", "Izmir", "Ankara", "16:00", 80, 1400.0, "1 saat 15 dakika"));
    	flights.add(new Flight("TK105", "Istanbul", "Antalya", "11:00", 200, 1600.0, "1 saat 45 dakika"));
    	flights.add(new Flight("TK106", "Antalya", "Istanbul", "18:30", 180, 1600.0, "1 saat 40 dakika"));
    	flights.add(new Flight("TK107", "Istanbul", "Trabzon", "13:00", 130, 1700.0, "2 saat"));
    	flights.add(new Flight("TK108", "Trabzon", "Istanbul", "20:00", 120, 1700.0, "2 saat"));
    	flights.add(new Flight("TK109", "Ankara", "Antalya", "09:45", 90, 1400.0, "1 saat 20 dakika"));
    	flights.add(new Flight("TK110", "Antalya", "Izmir", "22:30", 100, 1500.0, "1 saat 30 dakika"));
    	flights.add(new Flight("TK111", "Istanbul", "Bodrum", "07:00", 150, 1300.0, "1 saat 15 dakika"));
    	flights.add(new Flight("TK112", "Bodrum", "Istanbul", "19:15", 140, 1300.0, "1 saat 15 dakika"));

        // Ana pencere
        JFrame frame = new JFrame("Uçak Bileti Rezervasyon Sistemi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Kapatıldığında uygulamayı sonlandırır
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(4, 1, 10, 10));

        // Butonlar
        JButton viewFlightsButton = new JButton("Uçuşları Görüntüle"); // Uçuşları görüntüleme butonu
        JButton makeReservationButton = new JButton("Rezervasyon Yap");
        JButton cancelReservationButton = new JButton("Rezervasyon İptal Et");
        JButton viewReservationsButton = new JButton("Rezervasyonları Görüntüle");

        // Butonları ekle
        frame.add(viewFlightsButton);
        frame.add(makeReservationButton);
        frame.add(cancelReservationButton);
        frame.add(viewReservationsButton);

        // Uçuşları görüntüleme işlemi
        viewFlightsButton.addActionListener(e -> showFlightList());

        // Rezervasyon yapma işlemi
        makeReservationButton.addActionListener(e -> showReservationForm());

        // Rezervasyon iptal etme işlemi
        cancelReservationButton.addActionListener(e -> showCancelReservationForm());

        // Rezervasyonları görüntüleme işlemi
        viewReservationsButton.addActionListener(e -> showReservations());

        // Pencereyi görünür yap
        frame.setVisible(true);
    }

    // Uçuşları listeleme
    private void showFlightList() {
        StringBuilder flightInfo = new StringBuilder("Uçuşlar:\n");
        for (Flight flight : flights) {
            flightInfo.append("Uçuş No: ").append(flight.getFlightNumber())
                    .append(", Nereden: ").append(flight.getDeparture())
                    .append(", Nereye: ").append(flight.getDestination())
                    .append(", Kalkış Saati: ").append(flight.getDepartureTime())
                    .append(", Boş Koltuklar: ").append(flight.getAvailableSeats())
                    .append(", Fiyat: ").append(flight.getPrice()).append(" TL")
                    .append(", Süre: ").append(flight.getTravelDuration())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, flightInfo.toString(), "Uçuş Listesi", JOptionPane.INFORMATION_MESSAGE);
    }

    // Rezervasyon iptal formu
    private void showCancelReservationForm() {
        String reservationId = JOptionPane.showInputDialog("Rezervasyon ID girin:");
        if (reservationId == null || reservationId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Geçersiz giriş. İşlem iptal edildi.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean removed = reservations.removeIf(r -> r.getReservationId().equalsIgnoreCase(reservationId));
        if (removed) {
            JOptionPane.showMessageDialog(null, "Rezervasyon başarıyla iptal edildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Rezervasyon bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Rezervasyonları listeleme
    private void showReservations() {
        if (reservations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hiç rezervasyon yok.", "Rezervasyonlar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder reservationInfo = new StringBuilder("Rezervasyonlar:\n");
        for (Reservation reservation : reservations) {
            reservationInfo.append(reservation).append("\n");
        }
        JOptionPane.showMessageDialog(null, reservationInfo.toString(), "Rezervasyonlar", JOptionPane.INFORMATION_MESSAGE);
    }

    // Rezervasyon yapma formu
    private void showReservationForm() {
        JTextField flightNoField = new JTextField();
        JTextField passengerCountField = new JTextField();
        JComboBox<String> seatClassBox = new JComboBox<>(new String[]{"Ekonomi", "Business"});
        JTextField travelDateField = new JTextField();

        Object[] initialMessage = {
                "Uçuş No:", flightNoField,
                "Kaç Kişilik:", passengerCountField,
                "Sınıf:", seatClassBox,
                "Tarih (gg/aa/yyyy):", travelDateField
        };

        int initialOption = JOptionPane.showConfirmDialog(null, initialMessage, "Rezervasyon Bilgileri", JOptionPane.OK_CANCEL_OPTION);
        if (initialOption == JOptionPane.OK_OPTION) {
            String flightNo = flightNoField.getText();
            int passengerCount = getIntInput(passengerCountField.getText(), "Yolcu Sayısı");
            if (passengerCount <= 0) return;

            String seatClass = (String) seatClassBox.getSelectedItem();
            String travelDate = travelDateField.getText();

            // Tarih doğrulama
            if (!isValidDate(travelDate)) {
                return; // Geçersiz tarih durumunda işlem iptal edilir
            }

            Flight selectedFlight = flights.stream()
                    .filter(flight -> flight.getFlightNumber().equalsIgnoreCase(flightNo))
                    .findFirst()
                    .orElse(null);

            if (selectedFlight == null) {
                JOptionPane.showMessageDialog(null, "Geçersiz uçuş numarası!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedFlight.getAvailableSeats() < passengerCount) {
                JOptionPane.showMessageDialog(null, "Yeterli koltuk bulunmamaktadır!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ArrayList<Passenger> passengers = new ArrayList<>();
            for (int i = 0; i < passengerCount; i++) {
                JTextField firstNameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JTextField tcIdField = new JTextField();
                JComboBox<String> genderBox = new JComboBox<>(new String[]{"Bay", "Bayan"});

                Object[] passengerMessage = {
                        "Ad:", firstNameField,
                        "Soyad:", lastNameField,
                        "TC Kimlik No:", tcIdField,
                        "Cinsiyet:", genderBox
                };

                int passengerOption = JOptionPane.showConfirmDialog(null, passengerMessage, "Yolcu " + (i + 1) + " Bilgileri", JOptionPane.OK_CANCEL_OPTION);
                if (passengerOption == JOptionPane.OK_OPTION) {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String tcId = tcIdField.getText();
                    String gender = (String) genderBox.getSelectedItem();

                    if (firstName.isEmpty() || lastName.isEmpty() || tcId.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Yolcu bilgileri eksik! İşlem iptal edildi.", "Hata", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    passengers.add(new Passenger(firstName, lastName, tcId, gender));
                } else {
                    JOptionPane.showMessageDialog(null, "Rezervasyon işlemi iptal edildi.", "İptal", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            double totalPrice = selectedFlight.getPrice() * passengerCount;
            while (true) {
                String paymentInput = JOptionPane.showInputDialog(null, "Toplam Fiyat: " + totalPrice + " TL\nÖdeme yapmak için lütfen tam tutarı giriniz:");
                if (paymentInput == null) {
                    JOptionPane.showMessageDialog(null, "Rezervasyon işlemi iptal edildi.", "İptal", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double payment = getDoubleInput(paymentInput, "Ödeme");
                if (payment < totalPrice) {
                    JOptionPane.showMessageDialog(null, "Eksik ödeme yapıldı! Lütfen tam tutarı giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                } else if (payment > totalPrice) {
                    JOptionPane.showMessageDialog(null, "Fazla girdiniz! Lütfen tam tutarı giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    break;
                }
            }

            Reservation newReservation = new Reservation(flightNo, passengers, seatClass, travelDate);
            reservations.add(newReservation);
            selectedFlight.setAvailableSeats(selectedFlight.getAvailableSeats() - passengerCount);

            JOptionPane.showMessageDialog(null, "Rezervasyon başarıyla oluşturuldu!\n" + newReservation, "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Yardımcı Fonksiyonlar
    private boolean isValidDate(String travelDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Geçersiz tarihleri engellemek için

        Date currentDate = new Date();
        Date selectedDate;

        // Tarih formatını kontrol et
        if (travelDate == null || travelDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tarih boş olamaz. Lütfen bir tarih girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            selectedDate = dateFormat.parse(travelDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Geçersiz tarih formatı! Lütfen 'gg/aa/yyyy' formatında bir tarih girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Eğer tarih bugünden önceyse hata ver
        if (selectedDate.before(currentDate)) {
            JOptionPane.showMessageDialog(null, "Geçmiş bir tarih seçemezsiniz. Lütfen ileri bir tarih girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Tarih doğruysa işleme devam et
        return true;
    }

    private int getIntInput(String input, String fieldName) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " geçerli bir sayı olmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private double getDoubleInput(String input, String fieldName) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " geçerli bir sayı olmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    // Main metodu
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirlineReservationUI::new);
    }
}
