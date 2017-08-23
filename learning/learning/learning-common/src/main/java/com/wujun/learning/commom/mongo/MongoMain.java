package com.wujun.learning.commom.mongo;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Set;
import java.util.function.Consumer;

public class MongoMain {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);

        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("db1");

        mongoDatabase.listCollectionNames().forEach((Consumer<? super String>) s -> {
                    System.out.println("s = " + s);
                }

        );


        MongoCollection<Document> c1 = mongoDatabase.getCollection("c1");

        c1.updateMany(Filters.eq("name", "wujun"), new Document("$set", new Document("age", 1)));

        c1.find().forEach((Block<? super Document>) document -> {
            System.out.println("document = " + document);
        });


        Mongo mongo = new Mongo("127.0.0.1", 27017);
        //根据mongodb数据库的名称获取mongodb对象 ,
        DB db = mongo.getDB( "db1" );
        Set<String> collectionNames = db.getCollectionNames();
        // 打印出test中的集合
        for (String name : collectionNames) {
            System.out.println("collectionName==="+name);
        }

    }

}
