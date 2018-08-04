package my.hhorushko.otus.library.service;

import my.hhorushko.otus.library.domain.User;

public interface AuthenticaticalService {

    void login(String username);

    void logout();

    void signUp(String username);

    User getCurrentUser();
}
