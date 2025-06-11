package com.example.LibraryManagement;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BorrowRecord {
    private static final Logger logger = LogManager.getLogger(BorrowRecord.class);
    private String bookId;
    private String bookTitle;
    private Date borrowDate;
    private Date returnDate;
    private String status; 

    public BorrowRecord(String bookId, String bookTitle) {
        if (bookId == null || bookId.isBlank() || bookTitle == null || bookTitle.isBlank()) {
            logger.error("BorrowRecord creation failed: Book ID or Title is null or empty.");
            throw new IllegalArgumentException("Nomiin ID ner hooson baij bolohgui");
        }
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = new Date();
        this.status = "Zeelsen";
        logger.info("BorrowRecord created: BookID='{}', Title='{}', BorrowDate='{}', Status='{}'", bookId, bookTitle, borrowDate, status);
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        logger.debug("Return date set for BookID='{}': {}", bookId, returnDate);
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            logger.warn("Attempt to set empty or null status for BorrowRecord BookID='{}'.", bookId);
            return;
        }
        this.status = status;
        logger.debug("Status updated for BookID='{}' to '{}'", bookId, status);
    }

    @Override
    public String toString() {
        return "BorrowRecord{BookID='" + bookId + "', Title='" + bookTitle + "', BorrowDate=" + borrowDate +
               ", ReturnDate=" + returnDate + ", Status='" + status + "'}";
    }
}
