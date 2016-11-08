package com.example.sriharsha.salesassistant;


public class SalesRepModel {

    int _id;
    String _firstname;
    String _secondname;
    String _email;
    String _phonenumber;
    String _address;
    public SalesRepModel() {
    }

    public SalesRepModel(int _id, String _firstname, String _secondname, String _email, String _phonenumber, String _address) {
        this._id = _id;
        this._firstname = _firstname;
        this._secondname = _secondname;
        this._email = _email;
        this._phonenumber = _phonenumber;
        this._address=_address;
    }

    public SalesRepModel(String _firstname, String _secondname, String _email, String _phonenumber,String _address) {
        this._firstname = _firstname;
        this._secondname = _secondname;
        this._email = _email;
        this._phonenumber = _phonenumber;
        this._address=_address;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_firstname() {
        return _firstname;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_secondname() {
        return _secondname;
    }

    public void set_secondname(String _secondname) {
        this._secondname = _secondname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phonenumber() {
        return _phonenumber;
    }

    public void set_phonenumber(String _phonenumber) {
        this._phonenumber = _phonenumber;
    }
}
