package fr.esiea.ex4A.user;

import fr.esiea.ex4A.AgifyClient;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final AgifyClient agifyClient;
    UserController(AgifyClient agifyClient) {
        this.userRepository = new UserRepository();
        this.agifyClient = agifyClient;
    }
    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserTwitterData> sayHello( @RequestParam(name = "userName") String name,
                                    @RequestParam(name = "userCountry") String userCountry)
                                    throws IOException {
        UserData referenceUserData = userRepository.getUserFromUserName(name);
        List<UserData> matches = new ArrayList<>();
        for(NameCountry nameCountry : userRepository.cachedAges.keySet()) {
            //int age = userRepository.cachedAges.get(nameCountry);
            UserData userData = userRepository.getUserFromUserName(nameCountry.name);
            if(match(userData, referenceUserData)) matches.add(userData);
        }
        return matches.stream().map(userData -> new UserTwitterData(userData.userTweeter, userData.userName)).collect(Collectors.toList());
    }
    @PostMapping(path = "/api/inscription", consumes = "application/json")
    public void addMember(@RequestBody UserData userData) throws IOException {
        if(!userRepository.cachedAges.containsKey(new NameCountry(userData.userName, userData.userCountry))){
            Response<AgifyData> response = agifyClient.agify(userData.userName, userData.userCountry).execute();
            if(response.isSuccessful()) {
                AgifyData agifyData = response.body();
                userRepository.cachedAges.put(new NameCountry(userData.userName, userData.userCountry), agifyData.age);
                userRepository.addUser(userData);
            } else System.out.println(response.errorBody());
        }
    }
    public boolean match(UserData userDataReference, UserData userData) {
        int referenceAge = userRepository.cachedAges.get(new NameCountry(userDataReference.userName, userDataReference.userCountry));
        int otherAge = userRepository.cachedAges.get(new NameCountry(userData.userName, userData.userCountry));
        return !userData.userName.equals(userDataReference.userName) && Math.abs(referenceAge - otherAge) <= 4
            && userDataReference.userSex.equals(userData.userSexPref) && userDataReference.userCountry.equals(userData.userCountry);
    }
}
