/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author luis
 */
@Named(value = "lenguajeBean")
@ViewScoped
public class lenguajeBean implements Serializable{
    
    /**
     * varible de control que perdime definir el idioma por defecto español
     */
    private String idioma="es";
    
    
    /**
     * coleccion estatica de los locales que soporta
     */
    private static Map<String, Object> paises;
    /**
     * metodo estatico
     */
    static {
        paises = new LinkedHashMap<>();
        paises.put("Español", Locale.forLanguageTag("es"));
        paises.put("English", Locale.US);
    }

    /**
     * metodo para cambiar el idioma en la interfaz
     * @param vce varible que controla el cambio de idioma
     */
    public void cambioDeIdioma(ValueChangeEvent vce) {
        if (vce.getNewValue() != null) {
            try {
                String nuevoCodigo = vce.getNewValue().toString();
                for (Map.Entry<String, Object> entrySet : paises.entrySet()) {
                    Locale value = (Locale) entrySet.getValue();
                    if (value.toString().compareTo(nuevoCodigo) == 0) {
                        FacesContext.getCurrentInstance().getViewRoot().setLocale(value);
                        return;
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
       
    /**
     * metodo que devuelve los mensajes utilizados en los metodos del CRUD
     * @param clave variable para guardar 
     * @return devuelve un mensaje al jsf 
     */
    public String getMensaje(final String clave) {
        try {
            ResourceBundle propiedades = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "componente");
            if (propiedades != null && propiedades.containsKey(clave)) {
                return propiedades.getString(clave);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }
    
    //set y get 
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Map<String, Object> getPaises() {
        return paises;
    }
    
}

