package model;

public class Comment {

    private String locationName;
    private String userName;
    private String commentText;

    public Comment(String locationName, String userName, String commentText) {
        this.locationName = locationName;
        this.userName = userName;
        this.commentText = commentText;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getUserName() {
        return userName;
    }

    public String getCommentText() {
        return commentText;
    }

    @Override
    public String toString() {
        return userName + ": " + commentText;
    }
}