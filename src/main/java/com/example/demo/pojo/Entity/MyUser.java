package com.example.demo.pojo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//User其实才是与数据库中的User表对应的实体类，MyUser实现了UserDetails接口，用在Spring Security中。
public class MyUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private List<Long> allowedQuestionIds;
    private Collection<? extends GrantedAuthority> authorities;

    //这几个字段存在的意义是为了反序列化时，通过setter赋值给MyUser对象。
    //? 因为jackson从UserDetails接口中看收到了isEnable()等方法，所以序列化了这四个字段！
    private  boolean enabled = true;
    private  boolean accountNonExpired = true;
    private  boolean credentialsNonExpired = true;
    private  boolean accountNonLocked = true;


    //构造方法
    public MyUser(User user, List<Long> allowedQuestionIds ,Collection<? extends GrantedAuthority> authorities){
        this.id = user.getId();
        this.username = user.getUsername();
        //TODO:临时方案，之后数据库中密码加密
        this.password = "{noop}"+user.getPassword();

        this.allowedQuestionIds = allowedQuestionIds;
        this.authorities = authorities;
    }

    public MyUser(Long id,List<Long> allowedQuestionIds,Collection<? extends GrantedAuthority> authorities){
        this.setId(id);
        this.allowedQuestionIds = allowedQuestionIds;
        this.authorities = authorities;
    }

}
