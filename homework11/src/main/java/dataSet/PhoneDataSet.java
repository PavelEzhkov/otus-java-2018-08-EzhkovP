package dataSet;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {

    @Column (name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDataSet userDataSet;

    public void setUserDataSet(UserDataSet userDataSet) {
        this.userDataSet = userDataSet;
    }

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet( id: " + getId() + "; number:" + number + ')';
    }

    public void setUser(UserDataSet user) {
        this.userDataSet = user;
    }
}
