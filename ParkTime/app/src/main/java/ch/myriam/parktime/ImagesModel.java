package ch.myriam.parktime;

import java.util.Arrays;

public class ImagesModel {
  private int id ;
  private String name;
  private String desc;
  private String rue;
  private String localite;

  private byte[] img;

    public ImagesModel(int id, String name, String desc, String rue, String localite, byte[] img) {
        this.name = name;
        this.desc = desc;
        this.rue = rue;
        this.localite = localite;
        this.img = img;
    }

    @Override
    public String toString() {
        return "ImagesModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", rue='" + rue + '\'' +
                ", localite='" + localite + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getLocalite() {
        return localite;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
