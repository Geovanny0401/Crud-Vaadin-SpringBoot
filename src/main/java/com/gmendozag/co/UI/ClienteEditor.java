package com.gmendozag.co.UI;

import com.gmendozag.co.Entity.Cliente;
import com.gmendozag.co.Repositorio.ClienteRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;




@SpringComponent
@UIScope
public class ClienteEditor extends VerticalLayout implements KeyNotifier {


    private final ClienteRepository repository;


    /**
     * The currently edited customer
     */
    private Cliente cliente;

    /* Campos entidad Cliente */
    TextField nombre = new TextField("Nombre");
    TextField apellido = new TextField("Apellido");
    TextField email = new TextField("Email");
    TextField telefono = new TextField("Telefono");


    /* Acciones botonos */
    Button save = new Button("Guardar", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancelar");
    Button delete = new Button("Eliminar", VaadinIcon.TRASH.create());
    //VerticalLayout actions = new VerticalLayout(save, cancel, delete);
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    Binder<Cliente> binder = new Binder<>(Cliente.class);
    private ChangeHandler changeHandler;

    @Autowired
    public ClienteEditor(ClienteRepository repository) {
        this.repository = repository;

        add(nombre, apellido, email, telefono, actions);
        // bind using naming convention
        binder.bindInstanceFields(this);
        // Configure and style components
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");
        addKeyPressListener(Key.ENTER, e -> save());
        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(cliente));
        setVisible(false);
    }



    void delete() {
        repository.delete(cliente);
        changeHandler.onChange();
    }

    void save() {
        repository.save(cliente);
        changeHandler.onChange();
    }



    public interface ChangeHandler {
        void onChange();
    }

    public final void editCustomer(Cliente c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            cliente = repository.findById(c.getId()).get();
        }
        else {
            cliente = c;
        }
        cancel.setVisible(persisted);

        binder.setBean(cliente);

        setVisible(true);

        nombre.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }

    

}
