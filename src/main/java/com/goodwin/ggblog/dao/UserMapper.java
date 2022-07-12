package com.goodwin.ggblog.dao;

import com.goodwin.ggblog.entity.FansVo;
import com.goodwin.ggblog.entity.User;
import com.goodwin.ggblog.entity.UserVo;

import java.util.List;


/**
 * @author goodwin
 */
public interface UserMapper {
    /**
     * 查询是否有与参数一致的user记录
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 查询指定id用户除了密码以外的所有记录
     * @param id
     * @return
     */
    User getUserExceptPsw(int id);

    /**
     * 根据用户名获得用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 添加一个用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据ID列表获得用户id,username,头像链接信息列表
     * @param idList ID列表
     * @return 用户列表
     */
    List<UserVo> getUserList(List<Integer> idList);

    /**
     * 为粉丝系统根据ID列表获得用户id，username，头像链接，个性签名信息列表
     * @param idList
     * @return
     */
    List<FansVo> getUserList4Fans(List<Integer> idList);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 粉丝加1
     * @param id
     * @return
     */
    int fansIncrease(int id);

    /**
     * 粉丝减1
     * @param id
     * @return
     */
    int fansDecrease(int id);

    /**
     * 关注加1
     * @param id
     * @return
     */
    int followIncrease(int id);

    /**
     * 关注减1
     * @param id
     * @return
     */
    int followDecrease(int id);

    /**
     * 动态加1
     * @param id
     * @return
     */
    int dynamicIncrease(int id);

    /**
     * 动态减1
     * @param id
     * @return
     */
    int dynamicDecrease(int id);

    /**
     * 根据id获得用户名
     * @param userId
     * @return
     */
    String getUsername(int userId);
}
