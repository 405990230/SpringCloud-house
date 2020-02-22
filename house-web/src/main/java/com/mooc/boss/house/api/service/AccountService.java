package com.mooc.boss.house.api.service;

import com.google.common.collect.Lists;
import com.mooc.boss.house.api.feign.UserFeignService;
import com.mooc.boss.house.api.model.User;
import com.mooc.boss.house.api.utils.BeanHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录，注册，个人信息服务
 */
@Service
public class AccountService {

    @Value("${domain.name}")
    private String domainName;


    @Autowired
    private FileService fileService;


    @Autowired
    private UserFeignService userFeignService;


    public User getUserById(Long id) {
        User queryUser = new User();
        queryUser.setId(id);
        List<User> users = getUserByQuery(queryUser);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public List<User> getUserByQuery(User query) {
        List<User> users = userFeignService.getUserList(query).getResult();
        return users;
    }

    public boolean addAccount(User account) {
        if (account.getAvatarFile() != null) {
            List<String> imags = fileService.getImgPaths(Lists.newArrayList(account.getAvatarFile()));
            account.setAvatar(imags.get(0));
        }
        account.setEnableUrl(domainName + "/accounts/verify");
        BeanHelper.setDefaultProp(account, User.class);
        userFeignService.addUser(account);
        return true;
    }


    public boolean isExist(String email) {
        return getUser(email) != null;
    }

    private User getUser(String email) {
        User queryUser = new User();
        queryUser.setEmail(email);
        List<User> users = getUserByQuery(queryUser);
        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }


    /**
     * @param key
     * @return 返回成功与失败
     */
    public boolean enable(String key) {
        userFeignService.enable(key);
        return true;
    }


    /**
     * 调用重置通知接口
     *
     * @param email
     */
    @Async
    public void remember(String email) {
        userFeignService.resetNotify(email, "http://" + domainName + "/accounts/reset");
    }

    /**
     * 重置密码操作
     *
     * @param password
     * @param key
     */
    public User reset(String key, String password) {
        return userFeignService.reset(key, password).getResult();
    }


    public String getResetEmail(String key) {
        String email = userFeignService.getEmail(key).getResult();
        return email;
    }


    public User updateUser(User user) {
        if (user.getAvatarFile() != null) {
            List<String> imgList =
                    fileService.getImgPaths(Lists.newArrayList(user.getAvatarFile()));
            if (!imgList.isEmpty()) {
                user.setAvatar(imgList.get(0));
            }
        }
        BeanHelper.onUpdate(user);
        return userFeignService.updateUser(user).getResult();
    }


    /**
     * 校验用户名密码并返回用户对象
     *
     * @param username
     * @param password
     * @return
     */
    public User auth(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return null;
        }
        User user = new User();
        user.setEmail(username);
        user.setPasswd(password);
        try {
            user = userFeignService.authUser(user).getResult();
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public void logout(String token) {
        userFeignService.logout(token);
    }


}
