package service;

import model.Comment;


import java.util.*;

public class CommentService {

    private final Map<String, List<Comment>> comments = new HashMap<>();


    public void addComment(String locationName,
                           String author,
                           String text) {

        Comment comment = new Comment();

        comments.computeIfAbsent(
                        locationName,
                        k -> new ArrayList<>())
                .add(comment);
    }
    

    public List<Comment> getCommentsForLocation(String locationName) {
        return comments.getOrDefault(
                locationName,
                new ArrayList<>());
    }
}