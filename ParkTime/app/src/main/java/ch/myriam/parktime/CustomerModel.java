package ch.myriam.parktime;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private String username;
    private int localite;
    private int nbreEnfants ;
    private String mdp ;


    //Constructor
    public CustomerModel(int id, String name, int age, String username, int localite,int nbreEnfants , String mdp) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.username = username;
        this.localite = localite;
        this.nbreEnfants = nbreEnfants;
        this.mdp = mdp;
    }


    // toString

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLocalite() {
        return localite;
    }

    public void setLocalite(int localite) {
        this.localite = localite;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public int getNbreEnfants() {
        return nbreEnfants;
    }

    public void setNbreEnfants(int nbreEnfants) {
        this.nbreEnfants = nbreEnfants;
    }




}