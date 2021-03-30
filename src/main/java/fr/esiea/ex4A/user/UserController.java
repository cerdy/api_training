package fr.esiea.ex4A.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class UserController {

    private final UserRepository userRepository;


    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping(path = "/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserTwitterData> sayHello(@RequestParam(name = "userName") String name, @RequestParam(name = "userCountry") String userCountry) {
        final List<UserData> userDatas = new ArrayList<>();
        userDatas.add(userRepository.randomUserData ());
        return userDatas.stream().map(userData -> new UserTwitterData(userData.userTweeter,userData.userName)).collect(Collectors.toList());
    }


    @PostMapping(path = "/api/inscription", consumes = "application/json", produces = "application/json")
    public void addMember(@RequestBody UserData userData) {

        System.out.println(userData.userName);
    }


}
