package com.xf.entity;

/**
 * 岗位表  对应的数据实体类
 */
public class Position {
//    岗位表id
    private Integer pid;
//    岗位名称
    private String name;
//    岗位描述
    private String description;
//    岗位拥有者
    private String leader;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
}
