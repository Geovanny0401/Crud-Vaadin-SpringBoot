package com.gmendozag.co.UI;

import com.gmendozag.co.Entity.Cliente;
import com.gmendozag.co.Repositorio.ClienteRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import net.amahdy.VaadinGridFilterH;
import org.springframework.util.StringUtils;



@Route
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
public class MainView extends VerticalLayout {





    private final ClienteRepository repo;
    private final ClienteEditor editor;
    final Grid<Cliente> grid;
    final TextField filter;
    private final Button addNewBtn, addNewBtnCar;

    public MainView(ClienteRepository repo, ClienteEditor editor) {

        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Cliente.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("Nuevo Cliente", VaadinIcon.PLUS.create());
        this.addNewBtnCar = new Button("Nuevo Vehiculo", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id","email","telefono","nombre");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
        //grid.setColumnReorderingAllowed(true);

        filter.setPlaceholder("Filtro por Apellido");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        grid.addColumn(new ComponentRenderer<>(VaadinGridFilterH::new,
                   (highlighter, person) -> {
              highlighter.setItem(person.getApellido());
              highlighter.setFilter(filter.getValue());
            })).setHeader("Apellido");

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });


        addNewBtn.addClickListener(e -> editor.editCustomer(new Cliente("", "")));
        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });
        listCustomers(null);
    }

    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByApellidoStartsWithIgnoreCase(filterText));
        }
    }



}
