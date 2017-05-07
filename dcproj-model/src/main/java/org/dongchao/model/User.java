package org.dongchao.model;

import javax.persistence.*;

/**
 * Created by zhaodongchao on 2017/5/3.
 */
@Table(name = "cf_user")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id ;

    private String name ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
