package beans;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
public class Up {

    private int i = 2;
    private Sub[] sub;

    public Sub[] getSub() {
        Sub[] sub = new Sub[i];
        for (int j = 0; j<i; j++) {
            sub[j] = new Sub();
        }
        return sub;
    }

    public void setSub(Sub[] sub) {
        this.sub = sub;
    }
}
