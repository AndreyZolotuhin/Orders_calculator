package ru.azolotuhin.calculator.Orders;

import java.util.Date;

public class Order implements Cloneable {

    private int id;
    private Customer customer;
    private String orderNumber;
    private String contract;
    private Double mass;
    private Date date;
    private String detailsNumber;
    private Double metalPrice;
    private Double paintPrice;
    private Double zincPrice;
    private Double workPrice;
    private Double deliveryPrice;
    private Double totalPrice;
    private String note;
    private boolean tolling;

    {
        this.customer = new Customer("Unknown");
        this.metalPrice = 0.0;
        this.paintPrice = 0.0;
        this.zincPrice = 0.0;
        this.workPrice = 0.0;
        this.deliveryPrice = 0.0;
        this.totalPrice = 0.0;
        this.note = " ";
    }

    public Order(int id, String orderNumber, Customer customer, String contract, Double mass, String detailsNumber,
                 Double metalPrice, Double paintPrice, Double zincPrice, Double workPrice, Double deliveryPrice,
                 Double totalPrice, String note, boolean tolling) {
        this.id = id;
        this.customer = customer;
        this.orderNumber = orderNumber;
        this.contract = contract;
        this.mass = mass;
        this.detailsNumber = detailsNumber;
        this.metalPrice = metalPrice;
        this.paintPrice = paintPrice;
        this.zincPrice = zincPrice;
        this.workPrice = workPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
        this.note = note;
        this.tolling = tolling;
    }

    public Order(){
        this.id = 0;
        this.orderNumber = "";
        this.contract = "";
        this.mass = 0.0;
        this.detailsNumber = "";
        this.tolling = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =  id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetailsNumber() {
        return detailsNumber;
    }

    public void setDetailsNumber(String detailsNumber) {
        this.detailsNumber = detailsNumber;
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

    public boolean isTolling() {
        return tolling;
    }

    public void setTolling(boolean tolling) {
        this.tolling = tolling;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", CustomerId=" + customer +
                ", orderNumber='" + orderNumber + '\'' +
                ", contract='" + contract + '\'' +
                ", mass=" + mass +
                ", date=" + date +
                ", detailsNumber='" + detailsNumber + '\'' +
                ", metalPrice=" + metalPrice +
                ", paintPrice=" + paintPrice +
                ", zincPrice=" + zincPrice +
                ", workPrice=" + workPrice +
                ", deliveryPrice=" + deliveryPrice +
                ", totalPrice=" + totalPrice +
                ", note='" + note + '\'' +
                ", tolling=" + tolling +
                '}';
    }
}



