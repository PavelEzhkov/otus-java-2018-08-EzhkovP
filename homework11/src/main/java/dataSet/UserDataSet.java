package dataSet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(mappedBy = "userDataSet", cascade = CascadeType.ALL)
    private List<PhoneDataSet> phones;

    public UserDataSet() {
        super();
    }

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserDataSet(String name, int age,List<PhoneDataSet> phones, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.phones = phones;
        for (PhoneDataSet p : phones){
            p.setUser(this);}
        this.address = address;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "UserDataSet (id: " + getId() + ", name: " + getName() + ", age: " + getAge() +
                " address: " + address+ ", " +
                " phones: " + phones+ ")";
    }

}
