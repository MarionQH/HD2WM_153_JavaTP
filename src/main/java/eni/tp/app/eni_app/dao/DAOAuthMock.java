package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DAOAuthMock implements IDAOAuth {

    List<User> users = Arrays.asList(
            new User("licorne@rainbow.com","123"),
            new User("unicorn@rainbow.com","456")
    );

    @Override
    public User login(String email, String password) {

        users.stream().filter(
                user -> user.email.equals(email) && user.password.equals(password))
                .findFirst().orElse(null);
        return null;
    }
}
