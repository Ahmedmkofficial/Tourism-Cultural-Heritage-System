package service;

import model.Comment;
import java.util.ArrayList;

public class CommentService {

    private ArrayList<Comment> comments;

    public CommentService() {
        comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
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

    public ArrayList<Comment> getAllComments() {
        return comments;
    }
}
