package com.mp.blog.permission.entity;

import com.mp.blog.common.entity.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author duchong
 * @description 权限
 * @date 2019-8-3 10:05:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Identifiable<Long> {

    private Long id;

    private Long roleId;


    private Long menuId;

    private Integer state;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleMenu roleMenu = (RoleMenu) o;
        return Objects.equals(menuId, roleMenu.menuId) &&
                Objects.equals(state, roleMenu.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, state);
    }

    public enum State {
        open(1, "开启"),
        close(0, "冻结");

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

}
