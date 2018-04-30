package fr.unice.polytech.polyincidents;

/**
 * Created by Sokhna on 30/04/2018.
 */

public class User {
    private String userID;
    private String password;
    private String name;
    private String surname;

    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }



    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
