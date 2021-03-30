package fr.esiea.ex4A.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Random;
@Repository
public class UserRepository {

    final List<UserData> userData = List.of(
        new UserData("nathan@eisea.fr","nathan","nathan033", "FR"),
        new UserData("finn@eisea.fr","finn","finn032", "UK"),
        new UserData("loic@eisea.fr","loic","loic237", "US")
    );

    private final Random random = new Random();

    UserData randomUserData() {
        return userData.get(random.nextInt(userData.size()));
    }
}
