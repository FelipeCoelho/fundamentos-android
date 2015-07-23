package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.MemoryClientRepoditory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class Client implements Parcelable {

    private Integer age;
    private String name;
    private Integer fone;
    private String addDress;

    public Client() {
        super();
    }

    public Client(Parcel parcel) {
        super();
        readToParcer(parcel);
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFone() {
        return fone;
    }

    public String getAddDress() {
        return addDress;
    }

    public void setFone(Integer fone) {
        this.fone = fone;
    }

    public void setAddDress(String addDress) {
        this.addDress = addDress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (fone != null ? !fone.equals(client.fone) : client.fone != null) return false;
        return !(addDress != null ? !addDress.equals(client.addDress) : client.addDress != null);

    }

    @Override
    public int hashCode() {
        int result = age != null ? age.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fone != null ? fone.hashCode() : 0);
        result = 31 * result + (addDress != null ? addDress.hashCode() : 0);
        return result;
    }

    public void save() {
        MemoryClientRepoditory.getInstance().save(this);
    }

    public static List<Client> getAll() {
        return MemoryClientRepoditory.getInstance().getAll();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void delete() {
        MemoryClientRepoditory.getInstance().delete(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name == null ? "" : name);
        dest.writeString(addDress == null ? "" : addDress);
        dest.writeInt(fone == null ? -1 : fone);
        dest.writeInt(age == null ? -1 : age);
    }

    private void readToParcer(Parcel parcel) {
        name = parcel.readString();
        addDress = parcel.readString();
        int partialFone = parcel.readInt();
        fone = partialFone == -1 ? null : partialFone;
        int partialAge = parcel.readInt();
        age = partialAge == -1 ? null : partialAge;
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {

        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
