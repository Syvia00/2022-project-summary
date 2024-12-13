package vending;

public class User{
    private String name;
    private String password;
    private String type;
    public String holder;
    public Boolean haveCard;
    public Boolean login;

    public User(){}
    public User(String name, String password, String type) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.login = false;
        this.haveCard = false;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType(){
        return this.type;
    }

    public void addCard(String holder){
        this.haveCard = true;
        this.holder = holder;
    }

    public void removeCard(){
        this.haveCard = false;
        this.holder = null;
    }

    public void Login(){
        this.login = true;
    }

    public void Logout(){
        this.login = false;
    }
}
