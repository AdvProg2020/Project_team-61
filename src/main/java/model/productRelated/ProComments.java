package model.productRelated;

import java.util.ArrayList;

public class ProComments {
    public String title;
    public String content;
    public String person;
    public static ArrayList<ProComments> list = new ArrayList<>();

    public ProComments() {
        list.add(this);
    }

    public String getTitle() {
        return title;
    }

    public void setList(ArrayList<ProComments> list) {
        this.list = list;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<ProComments> getList() {
        return list;
    }

    public String getPerson() {
        return person;
    }
}
