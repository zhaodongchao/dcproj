package org.dongchao.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by zhaodongchao on 2017/5/26.
 */
@Entity
@Table(name = "dc_menu")
public class Menu implements Serializable{
    @Id
    private String id ;
    private String text ;
    private Integer level ;
    private Boolean leaf ;
    private String menuUrl ;
    private String icon ;
    private String parentId;
    private Date createTime ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id) &&
                Objects.equals(text, menu.text) &&
                Objects.equals(level, menu.level) &&
                Objects.equals(leaf, menu.leaf) &&
                Objects.equals(menuUrl, menu.menuUrl) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(parentId, menu.parentId) &&
                Objects.equals(createTime, menu.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, level, leaf, menuUrl, icon, parentId, createTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("id='").append(id).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", level=").append(level);
        sb.append(", leaf=").append(leaf);
        sb.append(", menuUrl='").append(menuUrl).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", parentId='").append(parentId).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}
