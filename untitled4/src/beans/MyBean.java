package beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class MyBean implements Serializable{

    private int i = 4;
    private String name;

    private Sub[] sub;

    MyBean() {
        sub = new Sub[i];
        for (int j = 0; j<i; j++) {
            sub[j] = new Sub();
            sub[j].setQuestion("Question No."+j);
        }
    }

    public Sub[] getSub() {
        return sub;
    }

    public void setSub(Sub[] sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
