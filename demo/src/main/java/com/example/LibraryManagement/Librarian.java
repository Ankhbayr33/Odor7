package com.example.LibraryManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Librarian extends UserAccount {
    private static final Logger logger = LogManager.getLogger(Librarian.class);

    public Librarian(String username, String password) {
        super(username, password);
        logger.info("Nomiin sanchiin account buteegdsen: Username='{}'", username);
    }

    @Override
    public String getRole() {
        return "Nomiin Sanch";
    }

    
}
