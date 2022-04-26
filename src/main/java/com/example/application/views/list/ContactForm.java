package com.example.application.views.list;
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
import java.util.List;

public class ContactForm extends FormLayout { 

  public TextField registration = new TextField("Matricula");
  public ComboBox<String> type = new ComboBox<>("Tipo de vehiculo");
  public TextField maxLoad = new TextField("Carga máxima");
  public ComboBox<String> status = new ComboBox<>("Estado");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");



  public ContactForm() {
    addClassName("contact-form");

    List<String> types = Arrays.asList(("Furgoneta"),("Lona"), ("Trailer")); //DEFINIR TIPOS
    type.setItems(types);

    List<String> statusS = Arrays.asList(("CARGA"),("DESCARGA")); //DEFINIR TIPOS
    status.setItems(statusS);

    add(registration,
        type,
        maxLoad,
        status,
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


  public List<Vehicle> setVehicle(Fleet fleet) {
    Vehicle vehicle1 = new Vehicle(registration.getValue(), type.getValue(), maxLoad.getValue(), status.getValue());
    fleet.addVehicle(vehicle1);

    List<Vehicle> vehicles = fleet.getVehicles();
    return vehicles;
  }
}