/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import helper.UserHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojos.UserTbl;

/**
 * REST Web Service na obsluhu uzivatelov
 *
 * @author Janči
 */
@Path("user")
public class UserResource {

    private UserHelper helper;
    
    @Context
    private UriInfo context;

    /**
     * Konstruktor triedy UserResource
     */
    public UserResource() {
        helper=new UserHelper();
    }

   
    /**
     * Funkcia getUserList
     * @return vrati zoznam uzivatelov vo formate JSON 
     */
    @GET
    @Path("getUserList")
    @Produces("application/json")
    public String getUserList(){
        Gson gson=new Gson();
        List<UserTbl> l=null;
        try{
            l= new ArrayList(helper.userList());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return "{\"user\":"+gson.toJson(l)+"}";
    }
    /**
     * Funkcia getUserById
     * @param idUser ID uzivatela
     * @return vrati vyybraneho uzivatela vo formate JSON podla zadaneho ID 
     */
    @GET
    @Path("getUserById")
    @Produces("application/json")
    public String getUserById(@QueryParam("idUser")String idUser){
        Gson gson=new Gson();
        UserTbl ut=new UserTbl();
        try{
            List<UserTbl> l= new ArrayList(helper.getUserById(Integer.parseInt(idUser)));
            for(int i=0;i<l.size();i++){
                ut = new UserTbl();
                ut.setAddress(l.get(i).getAddress());
                ut.setGender(l.get(i).getGender());
                ut.setIdUser(l.get(i).getIdUser());
                ut.setName(l.get(i).getName());
                ut.setHeslo(l.get(i).getHeslo());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return gson.toJson(ut);
    }
    
    /**
     * Funkcia getUserByname
     * @param name meno uzivatela
     * @return vrati vyybraneho uzivatela vo formate JSON podla zadaneho mena 
     */
    @GET
    @Path("getUserByName")
    @Produces("application/json")
    public String getUserByName(@QueryParam("name")String name){
        Gson gson=new Gson();
        UserTbl ut=new UserTbl();
        try{
            List<UserTbl> l= new ArrayList(helper.getUserByName(name));
            for(int i=0;i<l.size();i++){
                ut = new UserTbl();
                ut.setName(l.get(i).getName());
                ut.setHeslo(l.get(i).getHeslo());
                ut.setIdUser(l.get(i).getIdUser());
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return gson.toJson(ut);
    }
    /**
     * Funkcia saveDataSensor - vlozi novy senzor
     * @param data parametre noveho uzivatela
     * @return vrati status 200 pri uspesnom vlozeni uzivatela
     */
    @POST
    @Path("saveDataUser")
    @Consumes("application/json")
    public Response saveDataUser(String data){
         Gson gson=new Gson();
         UserTbl ut=gson.fromJson(data, UserTbl.class);
         
         try{
            helper.saveDataUser(ut.getIdUser(), ut.getName(), ut.getGender(), ut.getAddress(),ut.getHeslo());
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).entity(ut).build();
    }
    /**
     * Funkcia updateName - podla ID uzivatela zmeni parametre meno, pohlavie, emailovu adresu, heslo
     * @param idUser ID uzivatela
     * @param name meno uzivatela
     * @param gender pohlavie uzivatela
     * @param address emailova adresa uzivatela
     * @param heslo heslo uzivatela
     */
    @PUT
    @Path("updateName")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateName(@QueryParam("idUser")String idUser,@QueryParam("name")String name,@QueryParam("gender")String gender,@QueryParam("address")String address ,@QueryParam("heslo")String heslo){
        
                 try{
            helper.updateName(Integer.parseInt(idUser), name,gender,address, heslo);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    /**
     * Funkcia deleteUser - vymaze uzivatela podla zadaneho ID
     * @param idUser ID uzivatela
     */
    @GET
    @Path("deleteUser")
    @Consumes("application/json")
    public void deleteUser(@QueryParam("idUser")String idUser){
        
        
         try{
            helper.deleteUser(Integer.parseInt(idUser));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
