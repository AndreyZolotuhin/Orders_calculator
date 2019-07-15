package ru.azolotuhin.calculator.Calculations;

public class Calculation {

    private int id;
    private int orderId;
    private Double mass;
    private Double metalPrice;
    private Double paintPrice;
    private Double zincPrice;
    private Double workPrice;
    private Double deliveryPrice;
    private Double totalPrice;
    private String note;
    private Double materialPrice;


    public Calculation(int id, int orderId, Double mass, Double metalPrice, Double paintPrice, Double zincPrice, Double workPrice, Double deliveryPrice,
                       Double totalPrice, String note, Double materialPrice) {
        this.id = id;
        this.orderId = orderId;
        this.mass = mass;
        this.metalPrice = metalPrice;
        this.paintPrice = paintPrice;
        this.zincPrice = zincPrice;
        this.workPrice = workPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
        this.note = note;
        this.materialPrice = materialPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getMetalPrice() {
        return metalPrice;
    }

    public void setMetalPrice(Double metalPrice) {
        this.metalPrice = metalPrice;
    }

    public Double getPaintPrice() {
        return paintPrice;
    }

    public void setPaintPrice(Double paintPrice) {
        this.paintPrice = paintPrice;
    }

    public Double getZincPrice() {
        return zincPrice;
    }

    public void setZincPrice(Double zincPrice) {
        this.zincPrice = zincPrice;
    }

    public Double getWorkPrice() {
        return workPrice;
    }

    public void setWorkPrice(Double workPrice) {
        this.workPrice = workPrice;
    }

    public Double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(Double matherialPrice) {
        this.materialPrice = matherialPrice;
    }


    @Override
    public String toString() {
        return "Calc{" +
                "id=" + id +
                ", mass=" + mass +
                ", metalPrice=" + metalPrice +
                ", paintPrice=" + paintPrice +
                ", zincPrice=" + zincPrice +
                ", workPrice=" + workPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", totalPrice=" + totalPrice +
                ", note='" + note + '\'' +
                '}';
    }
}
