package com.example.LibraryManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Book {
    private static final Logger logger = LogManager.getLogger(Book.class);
    private String id;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public Book(String id, String title, String author, int totalCopies) {
        if (id == null || id.isBlank()) {
            logger.error("Book creation failed: ID is null or empty.");
            throw new IllegalArgumentException("Nomiin ID hooson baij bolohgui.");
        }
        if (title == null || title.isBlank()) {
            logger.error("Book creation failed: Title is null or empty.");
            throw new IllegalArgumentException("Nomiin ner hooson baij bolohgui.");
        }
        if (author == null || author.isBlank()) {
            logger.error("Book creation failed: Author is null or empty.");
            throw new IllegalArgumentException("Zohiogc hooson baij bolohgui.");
        }
        if (totalCopies < 1) {
            logger.error("Book creation failed: Total copies must be at least 1. Given: {}", totalCopies);
            throw new IllegalArgumentException("Niit huvi '1' bolon tuunees ih baih ystoi.");
        }
        this.id = id;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        logger.info("Book created: ID='{}', Title='{}', Author='{}', Copies={}", id, title, author, totalCopies);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            logger.info("Book borrowed: ID='{}', Title='{}'. Available copies now: {}", id, title, availableCopies);
            return true;
        } else {
            logger.warn("Attempt to borrow book failed: No available copies for ID='{}', Title='{}'.", id, title);
            return false;
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            logger.info("Book returned: ID='{}', Title='{}'. Available copies now: {}", id, title, availableCopies);
        } else {
            logger.warn("Return failed: All copies are already available for ID='{}', Title='{}'.", id, title);
        }
    }

    @Override
    public String toString() {
        return "Book{ID='" + id + "', Title='" + title + "', Author='" + author + "', Available=" + availableCopies + "/" + totalCopies + "}";
    }
}
