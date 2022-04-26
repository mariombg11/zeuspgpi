package com.example.application.views.list;


import backend.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.annotation.security.PermitAll;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.copy;
import static java.util.Collections.max;

@PageTitle("Flota")
@Route(value="", layout = MainLayout.class)
@RouteAlias(value = "")
@PermitAll
public class ListView extends VerticalLayout {

    Fleet flota = new Fleet();

    List<Docks> muelles = null;
    List<Docks> muellesOriginales;
    boolean cargado = false;



    ArrayList<Booking> reservas = new ArrayList<>();

    Grid<Docks> grid = new Grid<>();

    Grid<Booking> tablaReservas = new Grid<>();
    TextField filterText = new TextField();
    ContactForm form;
    BookingForm formReserva;
    Barrier formBarrier;
    Informes informes;
    Boolean visible = false;
    Boolean reservasVisible = false;
    Boolean gridVisible = true;
    Boolean simulacionVisible = false;
    Boolean informeVisible = false;
    int contadorCancelaciones = 0;
    int contadorReservas = 0;

    public ListView() {
        Vehicle vehicle1 = new Vehicle("Carga", "34762HRD", "1000", "OCUPADO");
        flota.addVehicle(vehicle1);

        addClassName("list-view");
        setSizeFull();
        configureGrid(flota);
        configurarTablaReservas();
        configureForm();

        add(getToolbar(), getContent());
        closeEditor();
    }

    private void configureGrid(Fleet flota) {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        //grid.setItems(muelles);
        grid.addColumn(Docks::getId).setHeader("Nº MUELLE");
        grid.addColumn(Docks::getType).setHeader("VEHÍCULO");
        grid.addColumn(Docks::getHour1).setHeader("6:00-7:00");
        grid.addColumn(Docks::getHour2).setHeader("7:00-8:00");
        grid.addColumn(Docks::getHour3).setHeader("8:00-9:00");
        grid.addColumn(Docks::getHour4).setHeader("9:00-10:00");
        grid.addColumn(Docks::getHour5).setHeader("10:00-11:00");
        grid.addColumn(Docks::getHour6).setHeader("11:00-12:00");
        grid.addColumn(Docks::getHour7).setHeader("12:00-13:00");
        grid.addColumn(Docks::getHour8).setHeader("13:00-14:00");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        /*grid.addItemClickListener(
                event -> registration.setText("Clicked Item: " + event.getItem()));*/
    }

    private void configurarTablaReservas()
    {
        tablaReservas.addClassNames("contact-grid");
        tablaReservas.setSizeFull();
        tablaReservas.setItems(reservas);
        tablaReservas.addColumn(Booking::getMatricula).setHeader("MATRICULA");
        tablaReservas.addColumn(Booking::getMuelle).setHeader("MUELLE");
        tablaReservas.addColumn(Booking::getRango).setHeader("RANGO DE HORAS");
        tablaReservas.getColumns().forEach(col -> col.setAutoWidth(true));
        tablaReservas.addItemDoubleClickListener(event -> {
            System.out.println(event.getItem().getMatricula());
            actualizarMuelles(event.getItem());
            reservas.remove(event.getItem());
            tablaReservas.setItems(reservas);
            Notification notification = Notification.show("SE HA CANCELADO LA RESERVA SELECCIONADA!");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            grid.setItems(muelles);
            contadorCancelaciones = contadorCancelaciones + 1;
        });
    }


    private HorizontalLayout getToolbar() {

        Button addVehicleButton = new Button("Añadir vehiculo");
        addVehicleButton.addClickListener(click -> editVehicle());

        Button reservar = new Button("Reservar muelle");
        reservar.addClickListener(click -> editarReserva());

        Button loadCsv = new Button("Cargar csv");
        loadCsv.addClickListener(click -> cargarCsv());

        Button misReservas = new Button("Mis reservas");
        misReservas.addClickListener(click -> verReservas());

        Button simulaciónBarreras = new Button("Simular barreras");
        simulaciónBarreras.addClickListener(click -> simularBarreras());

        Button generarInforme = new Button("Generar informe");
        generarInforme.addClickListener(click -> mostrarInforme());

        HorizontalLayout toolbar = new HorizontalLayout(loadCsv, addVehicleButton, reservar, misReservas, simulaciónBarreras, generarInforme);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void simularBarreras(){
        simulacionVisible = !simulacionVisible;
        formBarrier.setVisible(simulacionVisible);
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, tablaReservas, form, formReserva, formBarrier, informes);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(2, tablaReservas);
        content.setFlexGrow(1, form);
        content.setFlexGrow(1, formReserva);
        content.setFlexGrow(1, formBarrier);
        content.setFlexGrow(2, informes);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new ContactForm();
        form.setWidth("25em");
        form.save.addClickListener(event -> addVehicle());

        formReserva = new BookingForm();
        formReserva.setWidth("25em");
        formReserva.save.addClickListener(event -> checkReserva());

        formBarrier = new Barrier();
        formBarrier.setWidth("25em");
        formBarrier.simular.addClickListener(event -> checkSimulacion());

        informes = new Informes();
        informes.setWidth("25em");
    }

    /*private void closeEditor() {
        form.setVisible(false);
        removeClassName("editing");
    }*/

    private void addVehicle() {
        //grid.setItems(form.setVehicle(flota));
        closeEditor();
    }

    public void editVehicle() {
        if (visible == true) {
            closeEditor();
            visible = false;
        } else {
            grid.asSingleSelect().clear();
            form.setVisible(true);
            visible = true;
            addClassName("editing");

            //form.save.addClickListener(event -> addVehicle());
        }
    }

    public void checkSimulacion(){
        if(formBarrier.checkBarrier(reservas)){
            System.out.println("BARRERA ABRIENDOSE");
            Notification notification = Notification.show("ABRIENDO BARRERA!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } else {
            System.out.println("NO SE PUEDE ABRIR LA BARRERA");
            Notification notification = Notification.show("NO SE PUEDE ABRIR LA BARRERA!");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    public void editarReserva() {
        if (visible == true) {
            closeEditor();
            visible = false;
        } else {
            grid.asSingleSelect().clear();
            formReserva.setVisible(true);
            visible = true;
            addClassName("editing");
            //formReserva.save.addClickListener(event -> checkReserva());
        }
    }
    private void checkReserva(){
        System.out.println("CHECK RESERVA");
        Booking reserva = formReserva.checkReserva(muelles);
        if (reserva != null){
            reservas.add(reserva);
            contadorReservas = contadorReservas + 1;
            System.out.println("RESERVA REALIZADA CON EXITO");
            System.out.println("Primera hora Original " + muellesOriginales.get(0).getHour1());
            Notification notification = Notification.show("SE HA REALIZADO LA RESERVA CON EXITO!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

            for(Booking book: reservas){
                System.out.println("Reserva " + book.toString());
            }
            closeEditor();
        } else {
            System.out.println("LA RESERVA NO SE HA PODIDO REALIZAR");
            Notification notification = Notification.show("LA RESERVA NO SE HA PODIDO REALIZAR!");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
        grid.setItems(muelles);
    }

    private void closeEditor() {
        //form.setVehicle(null);
        form.setVisible(false);
        formReserva.setVisible(false);
        formBarrier.setVisible(false);
        tablaReservas.setVisible(false);
        visible = false;
        removeClassName("editing");
    }

    private void cargarCsv(){
        if (cargado == false){
            cargado = true;
            muelles = CargarCsv.load();
            muellesOriginales = CargarCsv.load();
        }
        gridVisible = true;
        grid.setVisible(gridVisible);
        grid.setItems(muelles);

        reservasVisible = false;
        tablaReservas.setVisible(reservasVisible);

    }

    private void verReservas(){
        tablaReservas.setVisible(!reservasVisible);
        reservasVisible = !reservasVisible;
        gridVisible = false;
        grid.setVisible(gridVisible);
        tablaReservas.setItems(reservas);
    }

    private void actualizarMuelles(Booking booking){
        String muelleaux = booking.getMuelle();
        String horaAux = booking.getRango();
        String tipoAux = null;


        for (int i = 0; i < muelles.toArray().length; i++){
            if (muellesOriginales.get(i).getId().equals(muelleaux)){
                if (horaAux.equals("6:00-7:00")){
                    tipoAux = muellesOriginales.get(i).getHour1();
                    muelles.get(i).setHour1(tipoAux);
                } else if (horaAux.equals("7:00-8:00")){
                    tipoAux = muellesOriginales.get(i).getHour2();
                    muelles.get(i).setHour2(tipoAux);
                } else if (horaAux.equals("8:00-9:00")){
                    tipoAux = muellesOriginales.get(i).getHour3();
                    muelles.get(i).setHour3(tipoAux);
                } else if (horaAux.equals("9:00-10:00")){
                    tipoAux = muellesOriginales.get(i).getHour4();
                    muelles.get(i).setHour4(tipoAux);
                } else if (horaAux.equals("10:00-11:00")){
                    tipoAux = muellesOriginales.get(i).getHour5();
                    muelles.get(i).setHour5(tipoAux);
                } else if (horaAux.equals("11:00-12:00")){
                    tipoAux = muellesOriginales.get(i).getHour6();
                    muelles.get(i).setHour6(tipoAux);
                } else if (horaAux.equals("12:00-13:00")){
                    tipoAux = muellesOriginales.get(i).getHour7();
                    muelles.get(i).setHour7(tipoAux);
                } else if (horaAux.equals("13:00-14:00")){
                    tipoAux = muellesOriginales.get(i).getHour8();
                    muelles.get(i).setHour8(tipoAux);
                }
                System.out.println("TIPO ORIGINAL " + tipoAux);

                return;
            }
        }
    }


    private void mostrarInforme(){
        form.setVisible(false);
        tablaReservas.setVisible(false);
        formBarrier.setVisible(false);
        formReserva.setVisible(false);
        visible = false;
        reservasVisible = false;
        gridVisible = true;
        simulacionVisible = false;
        informeVisible = !informeVisible;

        informes.setVisible(informeVisible);

        if (contadorReservas > 0){
            float porcentajeCancelaciones = contadorCancelaciones * 100/contadorReservas;
            String sPorcentajeCancelaciones = String.valueOf(porcentajeCancelaciones);
            System.out.println("PORCENTAJE CANCELACIONES " + sPorcentajeCancelaciones);
            informes.reservasCanceladas.setText("El % de reservas canceladas es "+ sPorcentajeCancelaciones + "%. ");
        }

        int maxutilizacion = 0;
        int utilizacion = 0;
        for (Docks dock : muelles){
            if(dock.getHour1().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour2().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour3().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour4().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour5().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour6().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour7().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            if(dock.getHour8().equals("NO DISPONIBLE")){
                utilizacion = utilizacion + 1;
            }
            maxutilizacion = maxutilizacion + 8;
        }

        informes.retrasosLlegada.setText("El % de retrasos en las llegadas es de un 0.0%.  ");

        if (maxutilizacion > 0){
            float porcentajeUtilizado = utilizacion * 100/maxutilizacion;
            String sPorcentajeUtilizado = String.valueOf(porcentajeUtilizado);
            System.out.println("PORCENTAJE CANCELACIONES " + sPorcentajeUtilizado);
            informes.utilizacionMuelles.setText("El % de utilización de los muelles en base a la disponibilidad es "+ sPorcentajeUtilizado + "%. ");
        }

    }


}
