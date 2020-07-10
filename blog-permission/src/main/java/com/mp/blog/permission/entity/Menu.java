package com.mp.blog.permission.entity;

import com.mp.blog.common.model.Identifiable;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Data
public class Menu implements Identifiable<Long> {

    private Long id;

    private String title;

    private String icon;

    private String path;

    private String name;

    private  Integer parentId;

    private Integer type;

    private Date createTime;

    private String component;

    private Integer state;

    private  String remark;

//    private  List<Menu> btnMenu;


    public enum State {
        open(1, "开启的"),
        close(0, "关闭的");

        private Integer code;
        private String desc;

        State(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum Type {
        menu(1, "菜单"),
        btn(2, "按钮");

        private Integer code;
        private String desc;

        Type(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(title, menu.title) &&
                Objects.equals(icon, menu.icon) &&
                Objects.equals(path, menu.path) &&
                Objects.equals(name, menu.name) &&
                Objects.equals(parentId, menu.parentId) &&
                Objects.equals(type, menu.type) &&
                Objects.equals(createTime, menu.createTime) &&
                Objects.equals(component, menu.component) &&
                Objects.equals(state, menu.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, icon, path, name, parentId, type, createTime, component, state);
    }
}
