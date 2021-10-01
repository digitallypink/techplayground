package com.ujuezeoke.techplayground.apis.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ujuezeoke.techplayground.apis.model.Member;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class MongoDbDatabasePopulator implements DatabasePopulator {
    private final MongoDatabase database;
    private final String collectionName;

    public MongoDbDatabasePopulator(MongoClient mongoClient, String databaseName, String collectionName) {
        this.database = mongoClient.getDatabase(databaseName);
        this.collectionName = collectionName;
    }

    @Autowired
    public MongoDbDatabasePopulator(@Value("${mongodb.uri}") String connectionString,
                                    @Value("${mongodb.databasename}") String databaseName){
        this(MongoClients.create(connectionString), databaseName, "members");
    }

    @Override
    public void saveBatch(Collection<Member> members) {
        final MongoCollection<Document> databaseCollection =
                database.getCollection(collectionName);

        members.forEach(member -> databaseCollection.insertOne(Document.parse(member.asJson())));

    }

    @PostConstruct
    @Override
    public void create() {
        final boolean collectionNotExists = StreamSupport.stream(database.listCollectionNames().spliterator(), false)
                .noneMatch(name -> name.equals(collectionName));
        if(collectionNotExists){
            database.createCollection(collectionName);
        }
    }
}
