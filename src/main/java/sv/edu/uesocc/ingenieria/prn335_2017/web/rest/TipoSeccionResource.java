/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.TipoSeccionFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoSeccion;


@Path("TipoSeccion") //Este path es utilizado para identificar la clase
public class TipoSeccionResource implements Serializable {

    @EJB
    private TipoSeccionFacadeLocal TipoSeccionFacade;

    /**
     * Devuelve​ ​todos​ ​los​ ​registros​ ​existentes​
     *
     * @return
     */
    @GET
    @Path("/allRegistro") //Este path es utilizado para identificar el metodo
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoSeccion> findAll() {
        List salida = null;
        try {
            if (TipoSeccionFacade != null) {
                salida = TipoSeccionFacade.findAll();
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if (salida == null) {
                salida = new ArrayList();
            }
        }
        return salida;
    }

    /**
     * Busca los registros en un rango, tomando por defecto inicio 0 y cantidad
     * 10
     *
     * @param first
     * @param pageSize
     * @return
     */
    @GET
    @Path("/rangeRegistro") //Este path es utilizado para identificar el metodo
    @Produces({MediaType.APPLICATION_JSON})
    public List<TipoSeccion> findRange(
            @DefaultValue("0") @QueryParam("first") int first,
            @DefaultValue("10") @QueryParam("pagesize") int pageSize
    ) {
        List salida = null;
        try {
            if (TipoSeccionFacade != null) {
                salida = TipoSeccionFacade.findRange(first, pageSize);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if (salida == null) {
                salida = new ArrayList();
            }
        }
        return salida;
    }

    /**
     * Busca un registro por su Identificador
     *
     * @param id
     * @return
     */
    @GET
    @Path("/byIdRegistro/{idTipoSeccion}") //Este path es utilizado para identificar el metodo
    @Produces({MediaType.APPLICATION_JSON})
    public TipoSeccion findById(
            @PathParam("idTipoSeccion") Integer id
    ) {
        try {
            if (TipoSeccionFacade != null && id != null) {
                return TipoSeccionFacade.find(id);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return new TipoSeccion();
    }

}
