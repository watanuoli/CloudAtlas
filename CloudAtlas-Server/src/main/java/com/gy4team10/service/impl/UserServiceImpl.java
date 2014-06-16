package com.gy4team10.service.impl;

import com.gy4team10.dao.impl.UserDao;
import com.gy4team10.entity.User;
import com.gy4team10.service.GreetingService;
import com.gy4team10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-11.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    public boolean add(final User user){
        return userDao.add(user);
    }

    public void delete(String key){
        userDao.delete(key);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public void delete(List<String> list) {
        userDao.delete(list);
    }

    @Override
    public boolean add(List<User> list) {
        return userDao.add(list);
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public Set<String> listKeys() {
        return this.userDao.listKeys();
    }


}
