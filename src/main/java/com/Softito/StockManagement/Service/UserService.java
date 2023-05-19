package com.Softito.StockManagement.Service;

import com.Softito.StockManagement.Entity.User;
import com.Softito.StockManagement.Repository.IRepositoryService;
import com.Softito.StockManagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IRepositoryService<User> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User add(User entity) {
        entity.setIsDelete(false);
        entity.setIsStatus(true);
        return userRepository.save(entity);
    }

    @Override
    public String delete(Long id) {
        User user = userRepository.getById(id);
        user.setIsDelete(true);
        userRepository.save(user);
        return "User deleted.";
    }

    @Override
    public String update(Long id, User entity) {
        User user = userRepository.getById(id);
        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setIsStatus(entity.getIsStatus());
        return "User informations has been changed.";
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id)!=null?userRepository.findById(id):null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findEmail(String Email){
        System.out.println("Received Email: "+Email);
        User findUser = null;
        for(User user:userRepository.findAll()){
            if(user.getEmail().equals(Email)){
                findUser=user;
                break;
            }
        }
        return findUser;
    }

    public String changePassword(Long id,String oldPassword,String newPassword){
        User user = userRepository.getById(id);
        if(user.getPassword().equals(oldPassword)){
            user.setPassword(newPassword);
            return "Password changed successfully.";
        }
        else {
            return "Old password didn't match with entered password.";
        }
    }

}
