package com.example.a59070035.healthy.post;

public class Comment {
    /**
     * postId : 1
     * id : 1
     * name : id labore ex et quam laborum
     * email : Eliseo@gardner.biz
     * body : laudantium enim quasi est quidem magnam voluptate ipsam eos
     tempora quo necessitatibus
     dolor quam autem quasi
     reiciendis et nam sapiente accusantium
     */

    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;

    public Comment(){}

    public Comment(String postId, String name, String email, String body, String id){
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
