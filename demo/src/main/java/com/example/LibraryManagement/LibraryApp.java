package com.example.LibraryManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LibraryApp {
    private static final Logger logger = LogManager.getLogger(LibraryApp.class);

    public static void main(String[] args) {
        logger.info("Library Management System started.");

        Book book1 = new Book("B001", "Harry Potter", "Author A", 3);

        Member member = new Member("user123", "pass1234", "Ankhbayr", "example@example.com", "99990000");

        if (book1.borrowBook()) {
            BorrowRecord record = new BorrowRecord(book1.getId(), book1.getTitle());
            member.addBorrowRecord(record);
            logger.info("Member '{}' borrowed book '{}'.", member.getFullName(), book1.getTitle());
        } else {
            logger.warn("Book '{}' is not available for borrowing.", book1.getTitle());
        }

        if (!member.getBorrowRecords().isEmpty()) {
            BorrowRecord borrowed = member.getBorrowRecords().get(0);
            member.returnBook(borrowed);
            book1.returnBook();
            logger.info("Member '{}' returned book '{}'.", member.getFullName(), book1.getTitle());
        }

        logger.info("Library Management System ended.");
    }
}
