package domen.subDomen;

import java.io.Serializable;

public class Genocide implements Serializable {
    String city;
    int howmuch;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHowmuch() {
        return howmuch;
    }

    public void setHowmuch(int howmuch) {
        this.howmuch = howmuch;
    }
}
