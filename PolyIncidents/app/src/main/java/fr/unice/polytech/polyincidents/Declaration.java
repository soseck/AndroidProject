package fr.unice.polytech.polyincidents;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sokhna on 13/05/2018.
 */

public class Declaration {

    private String title, content, location, tag, importance, urgence;
    private boolean withImage;
    private String dateValue;
    private User author;

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String LOCATION = "location";
    public static final String TAG = "tag";
    public static final String IMPORTANCE = "importance";
    public static final String URGENGY = "urgency";
    public static final String WITH_IMAGE = "withImage";
    public static final String DATE = "date";

    public Declaration(String title, String content, String location) {
        this.title = title;
        this.content = content;
        this.location = location;
        this.tag = null;
        this.urgence = null;
        this.importance = null;
        this.withImage = false;
        this.dateValue = null;
        this.author = null;

    }

    public Declaration() {
    }

    public Map<String, String> getMapPostData(){
        Map<String, String> mapPostData = new HashMap<>();
        mapPostData.put(TITLE, title);
        mapPostData.put(CONTENT, content);
        mapPostData.put(LOCATION, location);
        mapPostData.put(URGENGY, urgence);
        mapPostData.put(IMPORTANCE, importance);
        mapPostData.put(WITH_IMAGE, String.valueOf(withImage));
        mapPostData.put(TAG, tag);
        mapPostData.put(DATE, dateValue);

        return mapPostData;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public void setWithImage(boolean withImage) {
        this.withImage = withImage;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLocation() {
        return location;
    }

    public String getTag() {
        return tag;
    }

    public String getImportance() {
        return importance;
    }

    public String getUrgence() {
        return urgence;
    }

    public boolean isWithImage() {
        return withImage;
    }

    public String getDateValue() {
        return dateValue;
    }

    public User getAuthor() {
        return author;
    }
}
