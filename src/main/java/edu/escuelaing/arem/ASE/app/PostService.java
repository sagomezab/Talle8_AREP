package edu.escuelaing.arem.ASE.app;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;

public class PostService {

    public static ArrayList<String> getAllPosts(){
        MongoDatabase database = MongoUtil.getDB();
        PostDAO postDao = new PostDAO(database);
        return postDao.listPosts();
    }

    public static ArrayList<String> postNewPost (String post) {
        MongoDatabase database = MongoUtil.getDB();
        PostDAO postDao = new PostDAO(database);
        postDao.addPost(post);
        return postDao.listPosts();
    }

    public static void deletePost (String post) {
        MongoDatabase database = MongoUtil.getDB();
        PostDAO postDao = new PostDAO(database);
        postDao.deletePost(post);
    }
}
