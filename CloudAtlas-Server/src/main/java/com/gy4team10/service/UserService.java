package com.gy4team10.service;

import com.gy4team10.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-11.
 */
public interface UserService {

    public boolean add(final User user);

    public void delete(String key);

    public boolean update(User user);

    public void delete(List<String> list);

    public boolean add(List<User> list);

    public User get(String id);

    public List<User> list();

    /**
     * 列出所有的key
     * @return
     */
    public Set<String> listKeys();
}
