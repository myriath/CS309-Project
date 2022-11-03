package com.requests.backend.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "upc")
public class UPC {
    private int fdc_id;
    private int upc;

    public UPC() {}

    public int getFdc_id() {
        return fdc_id;
    }

    public void setFdc_id(int fdc_id) {
        this.fdc_id = fdc_id;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }
}
