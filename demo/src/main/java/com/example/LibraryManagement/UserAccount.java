// UserAccount.java
package com.example.LibraryManagement;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class UserAccount {
    private static final Logger logger = LogManager.getLogger(UserAccount.class);
    private String username;
    private String password;
    private Date registrationDate;
    private boolean isActive;

    public UserAccount(String username, String password) {
        if (!username.matches("^[a-zA-Z0-9_]{3,15}$")) {
            logger.error("Hereglegchiin ner buruu baina: '{}'. 3-15 useg too bolon underscore baih shaardlagatai.", username);
            throw new IllegalArgumentException("Hereglegchiin ner buruu format-tai baina.");
        }
        if (password == null || password.length() < 6) {
            logger.error("Nuuts ug heterhii bogino baina: '{}'. 6 temdegt heregtei.", username);
            throw new IllegalArgumentException("Nuuts ug hamgiin bagaadaa 6 temdegttei baih yostoi.");
        }
        this.username = username;
        this.password = password;
        this.registrationDate = new Date();
        this.isActive = true;
        logger.info("Hereglegchiin account uuslee: Ner='{}'", username);
    }

    public void logAccountCreated() {
        logger.info("Hereglegchiin account amjilttai uuslee: Ner='{}', Role='{}'", username, getRole());
    }

    public boolean checkPassword(String password) {
        if (!isActive) {
            logger.warn("Idevhtei bus hereglegch password shalgalt: {}", username);
            return false;
        }
        boolean isValid = this.password.equals(password);
        if (isValid) {
            logger.debug("Password shalgalt amjilttai: {}", username);
        } else {
            logger.warn("Password buruu baina: {}", username);
        }
        return isValid;
    }

    public String getUsername() {
        return username;
    }

    public abstract String getRole();

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
        logger.info("Account id evhtei baidald orov '{}': isActive={}", username, active);
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!checkPassword(oldPassword)) {
            logger.error("Password solih amjiltgui: '{}'. Huuchin password buruu.", username);
            throw new IllegalArgumentException("Huuchin nuuts ug buruu baina.");
        }
        if (newPassword == null || newPassword.length() < 6) {
            logger.error("Shine nuuts ug heterhii bogino: '{}'.", username);
            throw new IllegalArgumentException("Shine nuuts ug hamgiin bagaadaa 6 temdegttei baih yostoi.");
        }
        this.password = newPassword;
        logger.info("Nuuts ug amjilttai soligdloo: {}", username);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
               "username='" + username + '\'' +
               ", role='" + getRole() + '\'' +
               ", active=" + isActive +
               ", registrationDate=" + registrationDate +
               '}';
    }
}