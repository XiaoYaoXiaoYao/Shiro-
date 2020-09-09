package cn.xiaoyao.shiro_demo.entity;

public class Menu {

    private int id;
    private  String perms;
    private  String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", perms='" + perms + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
