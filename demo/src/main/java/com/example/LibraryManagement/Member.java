package com.example.LibraryManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Member extends UserAccount {
    private static final Logger logger = LogManager.getLogger(Member.class);
    private String fullName;
    private String email;
    private String phoneNumber;
    private List<BorrowRecord> borrowRecords;
    private List<Fine> fines;
    private static final int MAX_BORROW_LIMIT = 5;

    public Member(String username, String password, String fullName, String email, String phoneNumber) {
        super(username, password);
        if (email == null || !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            logger.error("Member creation failed: Invalid email format for '{}'.", email);
            throw new IllegalArgumentException("Email Buruu formattai baina.");
        }
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.borrowRecords = new ArrayList<>();
        this.fines = new ArrayList<>();
        logger.info("Member created: Full Name='{}', Email='{}', Phone='{}'", fullName, email, phoneNumber);
    }

    @Override
    public String getRole() {
        return "Member";
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void addBorrowRecord(BorrowRecord record) {
        if (borrowRecords.size() >= MAX_BORROW_LIMIT) {
            logger.error("Borrow limit exceeded for member '{}' ({}). Max allowed: {}", fullName, getUsername(), MAX_BORROW_LIMIT);
            throw new IllegalStateException("Neg hereglegch 5-aas iluu nom zeelj bolohgui!");
        }
        borrowRecords.add(record);
        logger.info("Borrow record added for member '{}': Book='{}' (ID: {}), Status='{}'", fullName, record.getBookTitle(), record.getBookId(), record.getStatus());
        logger.debug("Current borrowed books for {}: {}", fullName, borrowRecords.size());
    }

    public void returnBook(BorrowRecord record) {
        if (!borrowRecords.contains(record)) {
            logger.error("Attempted to return a book not borrowed by member '{}': Book='{}' (ID: {})", fullName, record.getBookTitle(), record.getBookId());
            throw new IllegalArgumentException("Ene nomiig zeelsen bicgiin medeelel oldsongui.");
        }
        record.setStatus("Butsaasan");
        record.setReturnDate(new Date());
        borrowRecords.remove(record);
        logger.info("Book returned by member '{}': Book='{}' (ID: {}). Status set to 'Butsaasan'.", fullName, record.getBookTitle(), record.getBookId());
        logger.debug("Remaining borrowed books for {}: {}", fullName, borrowRecords.size());
    }

    public void addFine(Fine fine) {
        fines.add(fine);
        logger.info("Fine added to member '{}': Amount={}, Reason='{}'", fullName, fine.getAmount(), fine.getReason());
    }

    public double calculateTotalFines() {
        double total = fines.stream().filter(f -> !f.isPaid()).mapToDouble(Fine::getAmount).sum();
        logger.debug("Total unpaid fines for member '{}': {}", fullName, total);
        return total;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return new ArrayList<>(borrowRecords);
    }

    public List<Fine> getFines() {
        return new ArrayList<>(fines);
    }

    @Override
    public String toString() {
        return "Gishuun: " + fullName + ", Email: " + email + ", Utas: " + phoneNumber;
    }
    
}
