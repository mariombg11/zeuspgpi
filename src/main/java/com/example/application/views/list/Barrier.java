package com.example.application.views.list;

import backend.Booking;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Barrier extends FormLayout {
    public TextField matricula = new TextField("Matricula");
    public ComboBox<String> rangoHoras = new ComboBox<>("Rango de horas");

    Button simular = new Button("Simular");


    public Barrier() {
        addClassName("barrier-form");

        List<String> horas = Arrays.asList(("6:00-7:00"), ("7:00-8:00"), ("8:00-9:00"), ("9:00-10:00"), ("10:00-11:00"), ("11:00-12:00"), ("12:00-13:00"), ("13:00-14:00"));
        rangoHoras.setItems(horas);


        add(matricula,
                rangoHoras,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        simular.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        simular.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(simular);
    }


    public boolean checkBarrier(ArrayList<Booking> reservas) {
        for (Booking reserva : reservas){
            System.out.println("Reserva Rango " + reserva.getRango() + reserva.getMatricula());
            System.out.println("Form Rango " + rangoHoras.getValue() + matricula.getValue());
            if (reserva.getMatricula().equals(matricula.getValue()) && reserva.getRango().equals(rangoHoras.getValue())){
                return true;
            }
        }
        return false;
    }

}
