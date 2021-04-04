package fr.esiea.ex4A.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    final List<UserData> userDataList = new ArrayList<>() {{
        List<UserData> list = List.of(
            new UserData("nathan@eisea.fr","pierre","nathan033", "FR", "M", "F"), //53 ans
            new UserData("damien@eisea.fr","damien","damien032", "RU", "M", "F"), //29 ans
            new UserData("loic@eisea.fr","macron","macron237", "FR", "M", "F"),// 53
            new UserData("loic@eisea.fr","mathieu","mathieu237", "FR", "M", "F")
        );
        addAll(list);
    }};
    final Map<NameCountry, Integer> cachedAges = new HashMap<>();

    /**
     * Ajout d'un nouveau user dans la liste lors de inscription
     * */
    public void addUser (UserData userdata) {
        userDataList.add(userdata);
    }
    public UserData getUserFromUserName(String userName) {
        return userDataList.stream().filter(userData -> userData.userName.equals(userName)).findFirst().orElse(null);
    }

}
