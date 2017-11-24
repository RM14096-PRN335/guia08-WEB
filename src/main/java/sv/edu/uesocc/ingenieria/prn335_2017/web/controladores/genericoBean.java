/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.AbstractIntarface;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author luis
 * @param <T>
 */
public abstract class genericoBean<T> implements Serializable {

    /**
     * metodo abstracto que maneja las identidades 
     * @return retorna una identidad para trabajar sobre ella
     */
    protected abstract AbstractIntarface<T> getFacadeLocal();  
    
    public abstract T getEntity();
    /**
     * crea una lista para llenar la tabla 
     */
    List<T> listaDatos;
    
    
    @Inject
    lenguajeBean lenguaje;

    
    /**
     * metodo para imprimir un msm en la interfaz 
     * @param Mensaje variable donde se guarda el mensaje a imprimir en jsf
     */
    public void showMessage(String Mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(Mensaje));
    }

    /**
     * Este metodo sirve para llenar una lista de datos obtenidos 
     * de la base de datos
     */
    public void llenarLista() {
        if (getFacadeLocal().findAll() != null) {
            this.listaDatos = getFacadeLocal().findAll();
        } else {
            this.listaDatos = Collections.EMPTY_LIST;
        }
    }

    /**
     * este metodo sirve para ingresar los datos que el usuario a ingresado
     */
    public void guardarRegistro() {
        try {
            if (this.getEntity() != null && this.getFacadeLocal() != null) {
                if (this.getFacadeLocal().create(getEntity())) {
                    System.out.println("SE HA AGREGADO CON EXITO!! YA PODEMOS!!");
                    showMessage(lenguaje.getMensaje("prop.msmGuardar"));
                    llenarLista();
                }
            }
        } catch (Exception e) {
            showMessage(lenguaje.getMensaje("prop.msmNoGuardado"));
            System.out.println("ERROR NO SE AGREGO NADA " + e);
        }
    }

    /**
     * Este metodo sirve para editar un registro 
     */
    public void modificar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().edit(getEntity());
                System.out.println("SE HA MODFIFICADO CON EXITO!! YA PODEMOS!!");
                showMessage(lenguaje.getMensaje("prop.msmModificar"));
                llenarLista();
            } catch (Exception ex) {
                showMessage(lenguaje.getMensaje("prop.msmNoModificado"));
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);

            }
        }
    }
   
    /**
     * este metodo sirve para eliminar un registro ya ingresado 
     */ 
    public void eliminar() {
        if (getFacadeLocal() != null) {
            try {
                getFacadeLocal().remove(getEntity());
                System.out.println("SE HA ELIMINADO CON EXITO!! YA PODEMOS!!");
                showMessage(lenguaje.getMensaje("prop.msmEliminar"));
                llenarLista();
            } catch (Exception ex) {
                showMessage(lenguaje.getMensaje("prop.msmNoEliminado"));
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    
    public abstract void nuevo();
    public abstract void btnCancelar();
    public abstract void botones2();
    public abstract void botones();
}
