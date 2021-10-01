package com.ujuezeoke.techplayground.apis.controller;

import com.github.javafaker.Faker;
import com.ujuezeoke.techplayground.apis.model.Member;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ujuezeoke.techplayground.apis.utils.MemberListGenerator.getMembers;


@RestController
@RequestMapping("/members")
public class MembersController {

    @GetMapping(path = "/json", produces = "application/json")
    public Collection<Member> getMembersAsJson(){
        return getMembers(100);
    }

    @GetMapping(path = "/xml", produces = "text/xml")
    public Collection<Member> getMembersAsXml(){
        return getMembers(100);
    }
}
