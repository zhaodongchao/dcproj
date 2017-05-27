package org.dongchao.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Created by zhaodongchao on 2017/5/26.
 */
@Entity
@Table(name = "dc_menu")
public class Menu implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer menuId ;
    private String text; //菜单标题
    private String icon ;//菜单右边图标
    private String selectdIcon ;//菜单被选择后显示的图标
    private String herf ;//菜单链接
    private Integer selectable ;//是否可选
    private String  leaf ;//是否为叶子节点
    private Integer parentId ;//父节点ID
    private Integer sequenceNum ;//排序编号
    @OneToMany(targetEntity = Menu.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "parentId")
    private Set<Menu> nodes ;//其子节点集合

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSelectdIcon() {
        return selectdIcon;
    }

    public void setSelectdIcon(String selectdIcon) {
        this.selectdIcon = selectdIcon;
    }

    public String getHerf() {
        return herf;
    }

    public void setHerf(String herf) {
        this.herf = herf;
    }

    public Integer getSelectable() {
        return selectable;
    }

    public void setSelectable(Integer selectable) {
        this.selectable = selectable;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(Integer sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public Set<Menu> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Menu> nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(menuId, menu.menuId) &&
                Objects.equals(text, menu.text) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(selectdIcon, menu.selectdIcon) &&
                Objects.equals(herf, menu.herf) &&
                Objects.equals(selectable, menu.selectable) &&
                Objects.equals(leaf, menu.leaf) &&
                Objects.equals(parentId, menu.parentId) &&
                Objects.equals(sequenceNum, menu.sequenceNum) &&
                Objects.equals(nodes, menu.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, text, icon, selectdIcon, herf, selectable, leaf, parentId, sequenceNum, nodes);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Menu{");
        sb.append("menuId=").append(menuId);
        sb.append(", text='").append(text).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", selectdIcon='").append(selectdIcon).append('\'');
        sb.append(", herf='").append(herf).append('\'');
        sb.append(", selectable=").append(selectable);
        sb.append(", leaf='").append(leaf).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", sequenceNum=").append(sequenceNum);
        sb.append(", nodes=").append(nodes);
        sb.append('}');
        return sb.toString();
    }
}
