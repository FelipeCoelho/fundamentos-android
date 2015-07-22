package com.example.administrador.myapplication.model.entities;

import com.example.administrador.myapplication.model.persistence.MemoryClientRepoditory;

import java.util.List;

/**
 * Created by Administrador on 20/07/2015.
 */
public class Client {
    private Integer age;
    private String name;
    private Integer fone;
    private String addDress;

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
    public static List<Client> getAll(){
        return MemoryClientRepoditory.getInstance().getAll();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
