package vn.edu.hcmuaf.fit.shoe.dto;

public class ResponseObject {
    private String message ;
    private Object data ;

    public ResponseObject(String message , Object data){
        this.message = message ;
        this.data = data ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
