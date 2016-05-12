package modeles;

public class Message {
    private int id;
    private int idFrom;
    private int idTo;
    private String messageString;

    public Message(){}

    public Message(int  id, int idFrom, int idTo, String messageString) {
        this.id = id;
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.messageString = messageString;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }
}
