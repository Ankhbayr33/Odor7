package com.example.LibraryManagement;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fine {
    private static final Logger logger = LogManager.getLogger(Fine.class);
    private double amount;
    private String reason;
    private Date issueDate;
    private boolean isPaid;

    public Fine(double amount, String reason, Date issueDate) {
        if (amount <= 0) {
            logger.error("Fine creation failed: Amount must be positive. Provided: {}", amount);
            throw new IllegalArgumentException("Tolbor ogoh ystoi dun eyreg baih ystoi");
        }
        if (reason == null || reason.isBlank()) {
            logger.warn("Fine created with empty reason. Amount: {}", amount);
        }
        this.amount = amount;
        this.reason = reason;
        this.issueDate = issueDate;
        this.isPaid = false;
        logger.info("Fine created: Amount={}, Reason='{}', Issue Date='{}'", amount, reason, issueDate);
    }

    public double getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void payFine() {
        if (this.isPaid) {
            logger.warn("Attempted to pay an already paid fine. Amount: {}, Reason: {}", amount, reason);
            return;
        }
        this.isPaid = true;
        logger.info("Fine paid: Amount={}, Reason='{}', Issue Date='{}'", amount, reason, issueDate);
    }

    @Override
    public String toString() {
        return "Tolbor: " + amount + ", Shaltgaan: " + reason +
               ", Ognoo: " + issueDate + ", Tolov: " + (isPaid ? "Tolson" : "Тologdoogui");
    }
}
