package com.fasttrackit.BugetPersonal.model;

import java.util.Date;

public record CheltuieliAnLunaTip(Date data , TipCheltuiala tip) {
    @Override
    public Date data() {
        return data;
    }

    @Override
    public TipCheltuiala tip() {
        return tip;
    }
}
