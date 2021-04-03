package fr.esiea.ex4A.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    public final List<UserData> userDataList = new ArrayList<>();/*List.of*(
        new UserData("nathan@eisea.fr","nathan","nathan033", "FR"),
        new UserData("finn@eisea.fr","finn","finn032", "UK"),
        new UserData("loic@eisea.fr","loic","loic237", "US")
    );*/
    public final Map<NameCountry, Integer> cachedAges = new HashMap<>();
    public UserRepository() {
        /**/
        userDataList.addAll(List.of(
            new UserData("nathan@eisea.fr","pierre","nathan033", "FR", "M", "F"), //53 ans
            new UserData("damien@eisea.fr","damien","damien032", "RU", "M", "F"), //29 ans
            new UserData("loic@eisea.fr","macron","macron237", "FR", "M", "F"),// 53
            new UserData("loic@eisea.fr","mathieu","mathieu237", "FR", "M", "F")));
    }
    private final Random random = new Random();
    UserData randomUserData() {
        return userDataList.get(random.nextInt(userDataList.size()));
    }
    public void addUser (UserData userdata) {  // ajout d'un nouveau user dans la liste lors de inscription

        userDataList.add(userdata);

    }

    public UserData getUserFromUserName(String userName) {
        return userDataList.stream().filter(userData -> userData.userName.equals(userName)).findFirst().orElse(null);
    }

}
