package edu.escuelaing.arem.ASE.app;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
public class PostDAO {
    private final MongoCollection<Document> postsCollection;

    public PostDAO(MongoDatabase database) {
        postsCollection = database.getCollection("posts");
    }

    public String addPost(String post) {
        LocalDateTime currentDate = LocalDateTime.now();
        Document newPost = new Document("post", post)
                .append("date", currentDate.toString());
        postsCollection.insertOne(newPost);
        return newPost.toJson();
    }

    public ArrayList<String> listPosts() {
        FindIterable<Document> posts = postsCollection.find();
        ArrayList<String> temporalList = new ArrayList<>();
        for (Document post : posts) {
            temporalList.add(post.toJson());
        }
        if (temporalList.size() > 10) {
            ArrayList<String> finalList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                finalList.add(temporalList.get(temporalList.size() - 10 + i));
            }
            return finalList;
        } else {
            return temporalList;
        }
    }

    public void deletePost(String post) {
        postsCollection.deleteOne(eq("post", post));
    }
}
