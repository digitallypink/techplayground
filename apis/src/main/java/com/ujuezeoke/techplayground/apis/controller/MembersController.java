package com.ujuezeoke.techplayground.apis.controller;

import com.ujuezeoke.techplayground.apis.db.DatabaseAccess;
import com.ujuezeoke.techplayground.apis.model.Member;
import com.ujuezeoke.techplayground.apis.utils.CsvWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/members")
public class MembersController {

    private final DatabaseAccess databaseAccess;

    @Autowired
    public MembersController(DatabaseAccess databaseAccess){
        this.databaseAccess = databaseAccess;
    }

    @GetMapping(path = "/json", produces = "application/json")
    public Collection<Member> getMembersAsJson(){
        return databaseAccess.getMembers();
    }

    @GetMapping(path = "/xml", produces = "text/xml")
    public Collection<Member> getMembersAsXml(){
        return databaseAccess.getMembers();
    }

    @GetMapping(path = "/members.csv", produces = "text/csv")
    public String getMembersAsCsv(){
        final Collection<Member> members = databaseAccess.getMembers();
        return CsvWriter.toCsv(members, Member.class);
    }
}
