package sample;

import java.io.*;
import java.util.Date;

public class FXUserHandler implements UserHandler {

    private final String USER = "user.dat";
    private final double CORRECTION_COEFFICIENT = 1.0;


    @Override
    public void saveUser(User user) throws Exception {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USER)) ) {
            ((FXUser)user).setSaved(true);
            objectOutputStream.writeObject(user);

        }
    }

    @Override
    public User obtainUser() {
        User user;
        if (!new File(USER).exists()) return cleanUser();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER))) {
            user = (FXUser)objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            user = cleanUser();

        }
        return user;
    }

    @Override
    public User cleanUser() {
        new File(USER).delete();
        return new FXUser();
    }

    @Override
    public double calculateLifeSpan(User user) {
        Cuckoo cuckoo = new CubicCuckoo(user);
        //remaining life span
        double initialLifeSpan = cuckoo.getLifeSpan();
        //current age
        double currentAge = cuckoo.getCurrentAge();
        //a remaining life span ratio
        double ratio = (initialLifeSpan - currentAge) / initialLifeSpan;
        //personal correction on INITIAL life span
        double correction = cuckoo.getCorrection();
        //returns corrected life span
        return initialLifeSpan + ratio * correction * CORRECTION_COEFFICIENT;
    }

    @Override
    public Date getLastDate(User user) {
        long spanInMillis = user.getBirthDate().getTime() + (long)(calculateLifeSpan(user) * Cuckoo.MILLIS_IN_YEAR);
        return new Date(spanInMillis);
    }
}
