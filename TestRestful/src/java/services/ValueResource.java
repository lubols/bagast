/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import helper.ValueHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.ValueTbl;


/**
 * REST Web Service na obsluhu hodnot
 *
 * @author Janči
 */
@Path("value")
public class ValueResource {

    private ValueHelper helper;
    
    @Context
    private UriInfo context;

    /**
     * Konstruktor triedy ValueResource
     */
    public ValueResource() {
        helper=new ValueHelper();
    }

   
    /**
     * Funkcia getValueList
     * @return vrati zoznam hodnot senzorov vo formate JSON 
     */
    @GET
    @Path("getValueList")
    @Produces("application/json")
    public String getValueList(){
        Gson gson=new Gson();
        List<ValueTbl> l=null;
        try{
            l= new ArrayList(helper.valueList());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return "{\"value\":"+gson.toJson(l)+"}";
    }
    /**
     * Funkcia getValueByType 
     * @param type
     * @return vrati vybrane hodnoty senzoro vo formate JSON podla zadaneho typu 
     */
     @GET
    @Path("getValueByType")
    @Produces("application/json")
    public String getValueByType(@QueryParam("type")String type){
        Gson gson=new Gson();
        ValueTbl ut=new ValueTbl();
        try{
            List<ValueTbl> l= new ArrayList(helper.getValueByType(type));
            for(int i=0;i<l.size();i++){
                ut = new ValueTbl();
                ut.setValue1(l.get(i).getValue1());
                ut.setValue2(l.get(i).getValue2());
                ut.setValue3(l.get(i).getValue3());
                ut.setValue4(l.get(i).getValue4());
                ut.setValue5(l.get(i).getValue5());
                ut.setValue6(l.get(i).getValue6());
                ut.setValue7(l.get(i).getValue7());
                ut.setValue8(l.get(i).getValue8());
                ut.setValue9(l.get(i).getValue9());
                ut.setValue10(l.get(i).getValue10());
                
                
              
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return gson.toJson(ut);
    }
    /**
     * Funkcia saveDataValue - vlozi novy senzor s hodnotami 
     * @param data hodnoty pre novy senzor
     * @return vrati status 200 pri uspesnom vlozeni senzoru s hodnotami
     */
    @POST
    @Path("saveDataValue")
    @Consumes("application/json")
    public Response saveDataValue(String data){
         Gson gson=new Gson();
         ValueTbl ut=gson.fromJson(data, ValueTbl.class);
         
         try{
            helper.saveDataValue(ut.getIdValue(), ut.getType(), ut.getValue1(),  ut.getValue2(), ut.getValue3(), ut.getValue4(), ut.getValue5(), ut.getValue6(), ut.getValue7(), 
                    ut.getValue8(), ut.getValue9(), ut.getValue10());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).entity(ut).build();
    }
    /**
     * Funkcia updateValueByType- podla typu senzoru zmeni jeho hodnoty 
     * @param type typ senzoru
     * @param value1 prva hodnota senzoru 
     * @param value2 druha hodnota senzoru 
     * @param value3 tretia hodnota senzoru 
     * @param value4 stvrta hodnota senzoru 
     * @param value5 piata hodnota senzoru 
     * @param value6 siesta hodnota senzoru 
     * @param value7 siedma hodnota senzoru 
     * @param value8 osma hodnota senzoru 
     * @param value9 deviata hodnota senzoru 
     * @param value10 desiata hodnota senzoru 
     */
     @POST
    @Path("updateValueByType")
    @Consumes("application/json")
    public void updateValue(@QueryParam("type")String type,@QueryParam("value1")int value1,@QueryParam("value2")int value2,@QueryParam("value3")int value3,@QueryParam("value4")int value4,@QueryParam("value5")int value5,@QueryParam("value6")int value6, @QueryParam("value7") int value7,@QueryParam("value8")int value8,@QueryParam("value9")int value9,@QueryParam("value10")int value10){
        
                 try{
            helper.updateValue(type, value1,value2,value3,value4,value5,value6,value7,value8,value9,value10);
       
                 }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * Funkcia deleteValue - vymaze senzor s hodnotami podla ID 
     * @param idValue ID senzoru
     */
     @GET
    @Path("deleteValue")
    @Consumes("application/json")
    public void deleteValue(@QueryParam("idValue")String idValue){
        
        
         try{
            helper.deleteValue(Integer.parseInt(idValue));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
