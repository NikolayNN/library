package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.UserRepository;
import my.hhorushko.otus.library.domain.User;
import my.hhorushko.otus.library.exception.LibraryNotFoundException;
import my.hhorushko.otus.library.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(int id){

        User user = getUser(userRepository.findById(id), String.format("User with id: %s not found", id));
        return user;
    }

    @Override
    public User getByName(String name){

        User user = getUser(userRepository.findByName(name), String.format("User with name: %s not found", name));
        return user;
    }

    @Override
    public void deleteById(int id){

        getById(id);
        userRepository.deleteById(id);
    }

    @Override
    public User updateById(int id, User user){

        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    private User getUser(Optional<User> optionalUser, String errorMessage){

        if(!optionalUser.isPresent()){
            throw new LibraryNotFoundException(errorMessage);
        }

        return optionalUser.get();
    }
}
