package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.SQLiteClientRepository;
import com.example.administrador.myapplication.model.persistence.SQLiteUserRepository;
import com.example.administrador.myapplication.model.persistence.UserRepository;

import java.util.List;

/**
 * Created by Administrador on 30/07/2015.
 */
public class User implements Parcelable {

    private Integer id;
    private String name;
    private String password;

    public User() {

    }

    public User(Parcel parcel) {
        super();
        readToParcer(parcel);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private void readToParcer(Parcel parcel) {
        int partialId = parcel.readInt();
        id = partialId == -1 ? null : partialId;
        name = parcel.readString();
        password = parcel.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id == null ? -1 : id);
        dest.writeString(name == null ? "" : name);
        dest.writeString(password == null ? "" : password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void save() {
        SQLiteUserRepository.getInstance().save(this);
    }

    public void save(User user) {
        SQLiteUserRepository.getInstance().save(this);
    }


    public static List<User> getAll() {
        return SQLiteUserRepository.getInstance().getAll();
    }

    public static User getUser(User user) {
        return SQLiteUserRepository.getInstance().getUser(user);
    }


    public void delete(User user) {
        SQLiteUserRepository.getInstance().delete(this);
    }
}
