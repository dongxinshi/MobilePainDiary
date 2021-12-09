package com.example.mobilepaindiary.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    public LoggedInUser( String displayName) {

        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}