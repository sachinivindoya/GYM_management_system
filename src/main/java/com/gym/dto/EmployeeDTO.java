package com.gym.dto;

public class EmployeeDTO {
    private String Emp_Id;
    private String Name;
    private String Address;
    private String NIC;
    private String Contact;
    private String Joined_Date;
    private String Occupation;

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "Emp_Id='" + Emp_Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", NIC='" + NIC + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Joined_Date='" + Joined_Date + '\'' +
                ", Occupation='" + Occupation + '\'' +
                '}';
    }

    public EmployeeDTO(String emp_Id, String name, String address, String NIC, String contact, String joined_Date, String occupation) {
        Emp_Id = emp_Id;
        Name = name;
        Address = address;
        this.NIC = NIC;
        Contact = contact;
        Joined_Date = joined_Date;
        Occupation = occupation;
    }

    public String getEmp_Id() {
        return Emp_Id;
    }

    public void setEmp_Id(String emp_Id) {
        Emp_Id = emp_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getJoined_Date() {
        return Joined_Date;
    }

    public void setJoined_Date(String joined_Date) {
        Joined_Date = joined_Date;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public EmployeeDTO() {
    }
}
