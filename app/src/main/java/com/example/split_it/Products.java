package com.example.split_it;

public class Products {
    private int _id;
    private String _productname;
    private int _productcost;

    public Products(){

    }

    public Products(String productName) {
        this._productname = productName;
    }

    public Products(String productName, int cost) {
        this._productname = productName;
        this._productcost = cost;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    /*
    public String get_productcost() {
        return _productcost;
    }

    public void set_productcost(String _cost) {
        this._productcost = _cost;
    }
    */
    public int get_productcost() {
        return _productcost;
    }

    public void set_productcost(int _cost) {
        this._productcost = _cost;
    }

}