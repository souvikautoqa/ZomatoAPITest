package com.zomato.qa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstablishmentTypes {

    @SerializedName("establishment_type")
    @Expose
    private Establishment_ establishmentType;

    public Establishment_ getEstablishmentType() {
        return establishmentType;
    }

    public void setEstablishmentType(Establishment_ establishmentType) {
        this.establishmentType = establishmentType;
    }
}
