package com.example.application.views.list;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class Informes extends FormLayout {
    public Text reservasCanceladas = new Text("");
    public Text retrasosLlegada = new Text("");
    public Text utilizacionMuelles = new Text("");

    public Text getReservasCanceladas() {
        return reservasCanceladas;
    }

    public void setReservasCanceladas(Text reservasCanceladas) {
        this.reservasCanceladas = reservasCanceladas;
    }

    public Text getRetrasosLlegada() {
        return retrasosLlegada;
    }

    public void setRetrasosLlegada(Text retrasosLlegada) {
        this.retrasosLlegada = retrasosLlegada;
    }

    public Text getUtilizacionMuelles() {
        return utilizacionMuelles;
    }

    public void setUtilizacionMuelles(Text utilizacionMuelles) {
        this.utilizacionMuelles = utilizacionMuelles;
    }

    private VerticalLayout createButtonsLayout() {

        return new VerticalLayout(reservasCanceladas, retrasosLlegada, utilizacionMuelles);
    }

    public Informes() {
        addClassName("informes-form");


        add(createButtonsLayout());
    }
}
