package com.ujuezeoke.techplayground.apis.db;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.ujuezeoke.techplayground.apis.model.Course;
import com.ujuezeoke.techplayground.apis.model.Member;
import com.ujuezeoke.techplayground.apis.model.WithJson;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

@Component
public class MongoDbDatabasePopulator implements DatabasePopulator, DatabaseAccess {
    private static final String membersCollectionName = "members";
    private static final String coursesCollectionName = "courses";
    private final MongoDatabase database;

    public MongoDbDatabasePopulator(MongoClient mongoClient, String databaseName) {
        this.database = mongoClient.getDatabase(databaseName);
    }

    @Autowired
    public MongoDbDatabasePopulator(@Value("${mongodb.uri}") String connectionString,
                                    @Value("${mongodb.databasename}") String databaseName){
        this(MongoClients.create(connectionString), databaseName);
    }

    public void saveMembersBatch(Collection<Member> members) {
        final MongoCollection<Document> databaseCollection =
                database.getCollection(membersCollectionName);

        members.forEach(member -> databaseCollection.insertOne(Document.parse(member.asJson())));

    }

    @Override
    public void saveCourseBatch(Collection<Course> courses) {
        final MongoCollection<Document> databaseCollection =
                database.getCollection(coursesCollectionName);

        courses.forEach(course -> databaseCollection.insertOne(Document.parse(course.asJson())));
    }

    @Override
    public void create() {
//        final Spliterator<String> databaseCollectionNames = database.listCollectionNames().spliterator();
//        final boolean membersCollectionDoesNotExist = StreamSupport.stream(databaseCollectionNames, false)
//                .noneMatch(name -> name.equals(membersCollectionName));
//        final boolean coursesCollectionDoesNotExist = StreamSupport.stream(databaseCollectionNames, false)
//                .noneMatch(name -> name.equals(coursesCollectionName));
//
//        if(membersCollectionDoesNotExist){
//            database.createCollection(membersCollectionName);
//        }
//        if(coursesCollectionDoesNotExist){
//            database.createCollection(coursesCollectionName);
//        }
    }

    @Override
    public Collection<Member> getMembers() {
        final FindIterable<Document> documents = database.getCollection(membersCollectionName).find();
        return getMembers(documents);
    }

    @Override
    public Collection<Member> getMembers(int limit) {
        final FindIterable<Document> documents = database.getCollection(membersCollectionName)
                .find().limit(limit);

        return getMembers(documents);
    }

    @Override
    public Collection<Course> getCourses() {
        final FindIterable<Document> documents = database.getCollection(coursesCollectionName)
                .find();

        return getCourses(documents);
    }

    @Override
    public Collection<Course> getCourses(int limit) {
        final FindIterable<Document> documents = database.getCollection(coursesCollectionName)
                .find().limit(limit);

        return getCourses(documents);
    }

    private Collection<Member> getMembers(FindIterable<Document> documents) {
        final Collection<Member> members = new ArrayList<>();
        try(final MongoCursor<Document> cursor = documents.cursor()){
            while (cursor.hasNext()){
                members.add(WithJson.fromJson(cursor.tryNext().toJson(), Member.class));
            }
        }
        return members;
    }
    private Collection<Course> getCourses(FindIterable<Document> documents) {
        final Collection<Course> courses = new ArrayList<>();
        try(final MongoCursor<Document> cursor = documents.cursor()){
            while (cursor.hasNext()){
                courses.add(WithJson.fromJson(cursor.tryNext().toJson(), Course.class));
            }
        }
        return courses;
    }
}
