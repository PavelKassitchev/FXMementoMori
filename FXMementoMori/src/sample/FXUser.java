package sample;

import java.util.Arrays;
import java.util.Date;

public class FXUser implements User {

    private String name;
    private int gender;
    private Date birthDate;
    private int[] userData;

    //TODO below
    // construct a "non-gender" just-born user with default neutral replies to the questions!
    //check vorkability!


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
