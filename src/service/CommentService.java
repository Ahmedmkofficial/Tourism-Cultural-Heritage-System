package service;

import model.Comment;

import java.util.ArrayList;

public class CommentService {

    private ArrayList<Comment> comments;

    public CommentService() {
        comments = new ArrayList<>();
    }

    public void addComment(String locationName,
                           String userName,
                           String commentText) {

        comments.add(
                new Comment(locationName, userName, commentText)
        );
    }

    public ArrayList<Comment> getCommentsForLocation(String locationName) {

        ArrayList<Comment> result = new ArrayList<>();

        for (Comment comment : comments) {
            if (comment.getLocationName().equalsIgnoreCase(locationName)) {
                result.add(comment);
            }
        }

        return result;
    }

    public ArrayList<Comment> getAllComments() {
        return comments;
    }

    public void displayComments() {

        if (comments.isEmpty()) {
            System.out.println("No comments available.");
            return;
        }

        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }
}