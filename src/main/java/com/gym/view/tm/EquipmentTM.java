package com.gym.view.tm;

public class EquipmentTM {
private String Equip_Id;
private String Name;
private String Date;
private double Price;
private String Status;

    @Override
    public String toString() {
        return "EquipmentTM{" +
                "Equip_Id='" + Equip_Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Date='" + Date + '\'' +
                ", Price=" + Price +
                ", Status='" + Status + '\'' +
                '}';
    }

    public String getEquip_Id() {
        return Equip_Id;
    }

    public void setEquip_Id(String equip_Id) {
        Equip_Id = equip_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public EquipmentTM(String equip_Id, String name, String date, double price, String status) {
        this.Equip_Id = equip_Id;
        this. Name = name;
        this.Date = date;
        this.Price = price;
        this.Status = status;
    }

    public EquipmentTM(String equip_Id) {
        Equip_Id = equip_Id;
    }
}
