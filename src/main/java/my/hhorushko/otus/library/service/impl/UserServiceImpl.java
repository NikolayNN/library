package my.hhorushko.otus.library.service.impl;

import lombok.AllArgsConstructor;
import my.hhorushko.otus.library.dao.UserRepository;
import my.hhorushko.otus.library.domain.User;
import my.hhorushko.otus.library.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(int id){
        return userRepository.findById(id);
    }

    @Override
    public User getByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    @Override
    public User updateById(int id, User user){
        user.setId(id);
        return userRepository.update(user);
    }

    @Override
    public User save(User user) {
        return userRepository.insert(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


}
