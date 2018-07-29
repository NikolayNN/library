package my.hhorushko.otus.library.service.impl;

import my.hhorushko.otus.library.domain.User;
import my.hhorushko.otus.library.service.AuthenticaticalService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticaticalServiceImpl implements AuthenticaticalService {

    private UserServiceImpl userService;
    private User currentUser;

    public AuthenticalServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void login(String username) {
        this.currentUser = userService.getByName(username);
    }

    @Override
    public void logout() {

        this.currentUser = null;
    }

    @Override
    public void signUp(String username) {

        User user = new User();
        user.setName(username);
        userService.save(user);
    }
}
