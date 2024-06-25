package vn.edu.hcmuaf.fit.shoe.dto;

public class LoginResponse {
    private int idUser ;
    private String name ;

    public LoginResponse(int idUser , String name){
        this.idUser = idUser ;
        this.name = name ;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                '}';
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}