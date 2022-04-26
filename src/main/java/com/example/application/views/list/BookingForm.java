package com.example.application.views.list;

import backend.Booking;
import backend.Docks;
import backend.Fleet;
import backend.Vehicle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookingForm extends FormLayout {
    public TextField registration = new TextField("Matricula");
    public ComboBox<String> type = new ComboBox<>("Tipo de vehiculo");
    public TextField maxLoad = new TextField("Carga máxima");
    public ComboBox<String> status = new ComboBox<>("Estado");
    public ComboBox<String> date = new ComboBox<>("Hora");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");



    public BookingForm() {
        addClassName("contact-form");

        List<String> types = Arrays.asList(("Furgoneta"),("Lona"), ("Trailer")); //DEFINIR TIPOS
        type.setItems(types);

        List<String> statusS = Arrays.asList(("CARGA"),("DESCARGA")); //DEFINIR TIPOS
        status.setItems(statusS);

        List<String> horas = Arrays.asList(("6:00-7:00"), ("7:00-8:00"), ("8:00-9:00"), ("9:00-10:00"), ("10:00-11:00"), ("11:00-12:00"), ("12:00-13:00"), ("13:00-14:00"));
        date.setItems(horas);

        add(registration,
                type,
                maxLoad,
                status,
                date,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        //delete.addClickListener(event -> );
        //close.addClickListener(event ->);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }


    public Booking checkReserva(List<Docks> muelles){

        // Comprobar si se puede reservar
        for (Docks dock: muelles){
            if (dock.getType().equals(type.getValue())) {
                // Se comprueba la disponibilidad del muelle en esa hora
                if (date.getValue().equals("6:00-7:00")){
                    if (dock.getHour1().equals(status.getValue())){
                        dock.setHour1("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("7:00-8:00")) {
                    if (dock.getHour2().equals(status.getValue())){
                        dock.setHour2("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("8:00-9:00")) {
                    if (dock.getHour3().equals(status.getValue())){
                        dock.setHour3("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("9:00-10:00")) {
                    if (dock.getHour4().equals(status.getValue())){
                        dock.setHour4("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("10:00-11:00")) {
                    if (dock.getHour5().equals(status.getValue())){
                        dock.setHour5("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("11:00-12:00")) {
                    if (dock.getHour6().equals(status.getValue())){
                        dock.setHour6("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("12:00-13:00")) {
                    if (dock.getHour7().equals(status.getValue())){
                        dock.setHour7("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                } else if (date.getValue().equals("13:00-14:00")) {
                    if (dock.getHour8().equals(status.getValue())){
                        dock.setHour8("NO DISPONIBLE");
                        return saveBook(dock.getId());
                    }
                }
            }
        }

        return null;
    }

    private Booking saveBook(String id){
        Booking booking1 = new Booking(registration.getValue(), id, date.getValue(), "Conductor por defecto", false);

        return booking1;
    }
}
