package org.dongchao.model;

import javax.persistence.*;

/**
 * Created by zhaodongchao on 2017/5/3.
 */
@Table(name = "cf_user")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    private String name ;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
