/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.SensorTbl;
import pojos.UserTbl;
import utility.HibernateUtil;

/**
 * Trieda na pracu s tabulkou senzorov
 * @author Janči
 */
public class SensorHelper {
    /**
     * Konstruktor triedy SensorHelper
     */
    public SensorHelper(){
        
    }
    /**
     * Funkcia saveDataSensor na pridanie noveho senzoru do tabulky
     * @param idSensor ID senzoru
     * @param name meno senzoru
     * @param type typ senzoru
     */
    public void saveDataSensor(int idSensor,String name,String type){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        SensorTbl ut = new SensorTbl();
        if(idSensor!=0){
            ut.setIdSensor(idSensor);
        }
        ut.setName(name);
        ut.setType(type);
        
        sesion.saveOrUpdate(ut);
        tx.commit();
        sesion.close();
        
    }
    /**
     * Funkcia sensorList 
     * @return vrati zoznam vsetkych senzorov z tabulky 
     */
    public List<SensorTbl> sensorList(){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        List<SensorTbl> l=null;
        Query q=sesion.createQuery("from SensorTbl u");
        l=q.list();
        tx.commit();
        sesion.close();
        return l;
    }
    /**
     * Funkcia getSensorById 
     * @param idSensor ID senzoru
     * @return vrati senzor z tabulky podla ID senzoru
     */
    public List<SensorTbl> getSensorById(int idSensor){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = sesion.beginTransaction();
         
         List<SensorTbl> l=null;
         Query q=sesion.createQuery("from SensorTbl u where u.idSensor="+idSensor);
         
         l=q.list();
         tx.commit();
         sesion.close();
         return l;
    }
    
    /**
     * Funkcia updateSensor - zmeni parametre nazov a typ senzoru podla ID
     * @param idSensor ID senzoru
     * @param name nazov senzoru
     * @param type typ senzoru
     */
    public void updateSensor(int idSensor, String name,String type){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
    
        Query q=sesion.createQuery("update SensorTbl u set u.name='"+name+"', u.type='"+type+"' where u.idSensor="+idSensor);
        
        int i=q.executeUpdate();
        tx.commit();
        sesion.close();
        
        
    }
    /**
     * Funckia deleteSensor - vymaze senzor z tabulky podla ID
     * @param idSensor 
     */
    public void deleteSensor(int idSensor){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Query q=sesion.createQuery("delete from SensorTbl u where u.idSensor="+idSensor);
        int i=q.executeUpdate();
        tx.commit();
        sesion.close();
                
    }
}
