//package com.example.demo.Config;
//
//import com.example.demo.Mapper.UserMapper;
//import com.example.demo.pojo.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsPasswordService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.stereotype.Component;
//
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Component
//public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//
//    @Override
//    public UserDetails updatePassword(UserDetails user, String newPassword) {
//        return null;
//    }
//
//    @Override
//    public void createUser(UserDetails user) {
//
//    }
//
//    @Override
//    public void updateUser(UserDetails user) {
//
//    }
//
//    @Override
//    public void deleteUser(String username) {
//
//    }
//
//    @Override
//    public void changePassword(String oldPassword, String newPassword) {
//
//    }
//
//    @Override
//    public boolean userExists(String username) {
//        return false;
//    }
//
//    /**
//     *最终要的方法，根据用户名获取用户信息
//     * 但这其实应该是Service层的事情，似乎不是很建议在Manager层做这件事情
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userMapper.getByAccount(username);
//
//        if(user == null){
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }else {
//
//            Collection<? extends GrantedAuthority> authorities=new ArrayList<>();
//
//            return new org.springframework.security.core.userdetails.User(
//                    user.getAccount(),      //username
//                    user.getPassword(),     //password
//                    true,                  //enabled
//                    true,                  //用户账号是否未过期
//                    true,                  //用户凭证（密码）是否未过期
//                    true,                  //账户是否未锁定
//                    authorities            //权限列表
//            );
//        }
//    }
//}
