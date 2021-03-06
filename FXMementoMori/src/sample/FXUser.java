package sample;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class FXUser implements User, Serializable {

    private String name;
    private int gender;
    private Date birthDate;
    private int[] userData;


    private boolean isSaved;

    public boolean isSaved() {
        return isSaved;
    }
    public void setSaved (boolean isSaved) {
        this.isSaved = isSaved;
    }

    //TODO below
    // construct a "non-gender" just-born user with default neutral replies to the questions!
    //check vorkability!
    public FXUser() {
        name = "Пользователь";
        gender = 0;
        birthDate = new Date();
        userData = new int[Questions.getLength()];
        isSaved = false;
    }



    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return new Date(birthDate.getTime());
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    //returns an array of the user's replies'
    public int[] getUserData() {
        return Arrays.copyOf(userData, userData.length);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    //returns the user's reply to question# i.
    public int getReply(int i) {
        return userData[i];
    }

    @Override
    //sets the user's reply to question# i.
    public void setReply(int i, int reply) {
        userData[i] = reply;
    }
}
