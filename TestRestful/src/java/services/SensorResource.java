/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import helper.SensorHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.SensorTbl;

/**
 * REST Web Service na obsluhu senzorov
 *
 * @author Janči
 */


@Path("sensor")
public class SensorResource {

    private SensorHelper helper;
    
    
    @Context
    private UriInfo context;

    /**
     * Konstruktor triedy SensorResource
     */
    public SensorResource() {
        helper= new SensorHelper();
    }


    /**
     * Funkcia getSensorList
     * @return vrati zoznam senzorov vo formate JSON 
     */
    @GET
    @Path("getSensorList")
    @Produces("application/json")
    public String getSensorList(){
        Gson gson=new Gson();
        List<SensorTbl> l=null;
        try{
            l= new ArrayList(helper.sensorList());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return "{\"sensor\":"+gson.toJson(l)+"}";
    }
    /**
     * Funkcia getSensorById
     * @param idSensor ID senzoru
     * @return vrati vyybrany senzor vo formate JSON podla zadaneho ID 
     */
     @GET
    @Path("getSensorById")
    @Produces("application/json")
    public String getSensorById(@QueryParam("idSensor")String idSensor){
        Gson gson=new Gson();
        SensorTbl ut=new SensorTbl();
        try{
            List<SensorTbl> l= new ArrayList(helper.getSensorById(Integer.parseInt(idSensor)));
            for(int i=0;i<l.size();i++){
                ut = new SensorTbl();
                ut.setIdSensor(l.get(i).getIdSensor());
                ut.setName(l.get(i).getName());
                ut.setType(l.get(i).getType());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return gson.toJson(ut);
    }
    /**
     * Funkcia saveDataSensor - vlozi novy senzor 
     * @param data parametre noveho senzoru
     * @return vrati status 200 pri uspesnom vlozeni senzoru
     */
    @POST
    @Path("saveDataSensor")
    @Consumes("application/json")
    public Response saveDataSensor(String data){
         Gson gson=new Gson();
         SensorTbl ut=gson.fromJson(data, SensorTbl.class);
         
         try{
            
            helper.saveDataSensor(ut.getIdSensor(),ut.getName(),ut.getType());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).entity(ut).build();
    }
    /**
     * Funkcia updateSensor - podla ID senzoru zmeni parametre nazov a typ a senzoru
     * @param idSensor ID sensoru
     * @param name  nazov senzoru
     * @param type typ senzoru
     */
    @POST
    @Path("updateSensor")
    @Consumes("application/json")
    public void updateSensorName(@QueryParam("idSensor")String idSensor,@QueryParam("name")String name,@QueryParam("type")String type){
        
                 try{
            helper.updateSensor(Integer.parseInt(idSensor), name, type);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * Funkcia deleteSensor - vymaze senzor podla zadaneho ID
     * @param idSensor ID senzoru
     */
    @GET
    @Path("deleteSensor")
    @Consumes("application/json")
    public void deleteSensor(@QueryParam("idSensor")String idSensor){
        
        
         try{
            helper.deleteSensor(Integer.parseInt(idSensor));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
