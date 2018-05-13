package fr.unice.polytech.polyincidents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sokhna on 30/04/2018.
 */

public class User {
    private String userID;
    private String password;
    private String name;
    private String surname;

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

    public void setUserAttributes(String jsonAttributes){
        try {
            JSONObject jsonObj = (new JSONArray(jsonAttributes.toString())).getJSONObject(0);
            this.name = jsonObj.getString("name");
            this.surname = jsonObj.getString("surname");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
