package fr.esiea.ex4A.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {


    public final String userEmail;
    public final String userName;
    public final String userTweeter;
    public final String userCountry;


    public UserData( @JsonProperty("userEmail") String userEmail,
                     @JsonProperty("userName") String userName,
                     @JsonProperty("userTweeter") String userTweeter,
                     @JsonProperty("userCountry") String userCountry) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userTweeter = userTweeter;
        this.userCountry = userCountry;
    }
}


// "userSex": "M",
//"userSexPref": "M"
