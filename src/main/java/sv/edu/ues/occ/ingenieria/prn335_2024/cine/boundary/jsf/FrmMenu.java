package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class FrmMenu implements Serializable {
    @Inject
    FacesContext facesContext;
    DefaultMenuModel  model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        DefaultSubMenu tipos = DefaultSubMenu.builder()
                .label("Tipos")
                .expanded(true)
                .build();
        DefaultMenuItem sala=DefaultMenuItem.builder().value("Sala").ajax(true).command("#{frmMenu.navegar('TipoSalaf.jsf')}")
                .build();
        DefaultMenuItem reserva=DefaultMenuItem.builder().value("Rerserva").ajax(true).command("#{frmMenu.navegar('TipoReservaf.jsf')}")
                .build();
        DefaultMenuItem producto=DefaultMenuItem.builder().value("Producto").ajax(true).command("#{frmMenu.navegar('TipoProductof.jsf')}")
                .build();
        DefaultMenuItem pelicula=DefaultMenuItem.builder().value("Pelicula").ajax(true).command("#{frmMenu.navegar('TipoPeliculaf.jsf')}")
                .build();
        DefaultMenuItem pago=DefaultMenuItem.builder().value("Pago").ajax(true).command("#{frmMenu.navegar('TipoPagof.jsf')}")
                .build();
        DefaultMenuItem assiento=DefaultMenuItem.builder().value("Asiento").ajax(true).command("#{frmMenu.navegar('TipoAsientof.jsf')}")
                .build();

   tipos.getElements().add(sala );
   tipos.getElements().add(reserva);
   tipos.getElements().add(producto);
   tipos.getElements().add(pelicula);
   tipos.getElements().add(pago);
   tipos.getElements().add(assiento);


   model.getElements().add(tipos);


        DefaultSubMenu cine = DefaultSubMenu.builder().label("Cine").expanded(true).build();
        DefaultMenuItem cpelicula=DefaultMenuItem.builder().value("Pelicula").ajax(true).command("#{frmMenu.navegar('Peliculaf.jsf')}").build();
        DefaultMenuItem csucursal=DefaultMenuItem.builder().value("Sucursal").ajax(true).command("#{frmMenu.navegar('Sucursalf.jsf')}").build();
        DefaultMenuItem csala=DefaultMenuItem.builder().value("Sala").ajax(true).command("#{frmMenu.navegar('SalaCaracteristicaf.jsf')}").build();
        DefaultMenuItem creserva=DefaultMenuItem.builder().value("Reserva").ajax(true).command("#{frmMenu.navegar('Reservaf.jsf')}").build();

        cine.getElements().add(cpelicula);
        cine.getElements().add(csucursal);
        cine.getElements().add(csala);
        cine.getElements().add(creserva);
        model.getElements().add(cine);


    }
public void navegar(String formulario) throws IOException {
facesContext.getExternalContext().redirect(formulario);
}

    public DefaultMenuModel getModel() {
        return model;
    }
}
