package com.azz;

import jakarta.persistence.*;

import java.util.Objects;

@Entity

public class Member {
    @Id
    @SequenceGenerator(
            name = "member_Id_sequence",
            sequenceName = "member_id_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_id_sequence"
    )

    private int idmember;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Member() {}

    public Member(int idmember, String name, String phone, String email, String address) {
        this.idmember = idmember;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getIdmember() {
        return idmember;
    }

    public void setIdmember(int idmember) {
        this.idmember = idmember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return idmember == member.idmember && Objects.equals(name, member.name) && Objects.equals(phone, member.phone) && Objects.equals(email, member.email) && Objects.equals(address, member.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmember, name, phone, email, address);
    }

    @Override
    public String toString() {
        return "member{" +
                "idmember=" + idmember +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
