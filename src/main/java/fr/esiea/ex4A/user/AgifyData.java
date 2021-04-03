package fr.esiea.ex4A.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgifyData {

    public final String name;
    public final int age;
    public final String count;
    public final String country_id;

    public AgifyData(@JsonProperty("name") String name, @JsonProperty("age") int age,
                     @JsonProperty("count") String count, @JsonProperty("country_id") String country_id) {
        this.name = name;
        this.age = age;
        this.count = count;
        this.country_id = country_id;
    }
}
