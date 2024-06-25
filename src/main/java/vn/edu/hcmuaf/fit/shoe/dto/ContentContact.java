package vn.edu.hcmuaf.fit.shoe.dto;

public class ContentContact {
    private int idCus;
    private String title;
    private String content;

    public ContentContact() {
    }

    public ContentContact(int idCus, String title, String content) {
        this.idCus = idCus;
        this.title = title;
        this.content = content;
    }

    public int getIdCus() {
        return idCus;
    }

    public void setIdCus(int idCus) {
        this.idCus = idCus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
