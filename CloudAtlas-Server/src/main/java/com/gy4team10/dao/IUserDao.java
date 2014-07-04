package com.gy4team10.dao;

import com.gy4team10.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by zhou on 14-6-10.
 */
public interface IUserDao {

    /**
     * 新增
     * <br>------------------------------<br>
     *
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * 批量新增 使用pipeline方式
     * <br>------------------------------<br>
     *
     * @param list
     * @return
     */
    boolean add(List<User> list);

    /**
     * 删除
     * <br>------------------------------<br>
     *
     * @param key
     */
    void delete(String key);

    /**
     * 删除多个
     * <br>------------------------------<br>
     *
     * @param keys
     */
    void delete(List<String> keys);

    /**
     * 修改
     * <br>------------------------------<br>
     *
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 通过key获取
     * <br>------------------------------<br>
     *
     * @param keyId
     * @return
     */
    User get(String keyId);

    /**
     * 列出所有的key
     * @return
     */
    public Set<String> listKeys();
}
