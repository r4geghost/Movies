package com.example.movies.data.response;

import com.example.movies.data.models.Person;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonResponse {

    // list of persons in movie
    // first one is director
    @SerializedName("persons")
    private List<Person> persons;

    public PersonResponse(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "persons=" + persons +
                '}';
    }
}
