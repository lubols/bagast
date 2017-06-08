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
import pojos.ValueTbl;
import utility.HibernateUtil;

/**
 * Trieda na pracu s tabulkou hodnot senzorov
 * @author Janči
 */
public class ValueHelper {
    /**
     * Konstruktor triedy ValueHelper
     */
    public ValueHelper(){
        
    }
    /**
     * Funkcia saveDataValue na pridanie noveho senzoru s hodnotami do tabulky
     * @param idValue ID senzoru
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
    public void saveDataValue(int idValue,String type, int value1, int value2, int value3, int value4, int value5, int value6, int value7, int value8, int value9, int value10){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        ValueTbl ut = new ValueTbl();
        if(idValue!=0){
            ut.setIdValue(idValue);
        }
        ut.setType(type);
        ut.setValue1(value1);
        ut.setValue2(value2);
        ut.setValue3(value3);
        ut.setValue4(value4);
        ut.setValue5(value5);
        ut.setValue6(value6);
        ut.setValue7(value7);
        ut.setValue8(value8);
        ut.setValue9(value9);
        ut.setValue10(value10);
          
        sesion.saveOrUpdate(ut);
        tx.commit();
        sesion.close();
        
    }
    /**
     * Funckia valueList
     * @return vrati zoznam vsetky senzorov s hodnotami z tabulky
     */
    public List<ValueTbl> valueList(){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        List<ValueTbl> l=null;
        Query q=sesion.createQuery("from ValueTbl u");
        l=q.list();
        tx.commit();
        sesion.close();
        return l;
    }
    /**
     * Funkcia updateValue zmeni hodnoty senzoru v tabulke podla jeho typu
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
    public void updateValue(String type, int value1, int value2, int value3, int value4, int value5, int value6, int value7, int value8, int value9, int value10){
    
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Query q=sesion.createQuery("update ValueTbl u set u.value1='"+value1+"',u.value2='"+value2+"',u.value3='"+value3+"',u.value4='"+value4+"',u.value5='"+value5+"',u.value6='"+value6+"',u.value7='"+value7+"',u.value8='"+value8+"',u.value9='"+value9+"',u.value10='"+value10+"' where u.type='"+type+"'");
       
        int i=q.executeUpdate();
        tx.commit();
        sesion.close();
        
        
    }
   
    
    /**
     * Funkcia deleteValue vymaze senzor s hodnotami z tabulky podla ID
     * @param idValue ID senzoru
     */
    public void deleteValue(int idValue){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Query q=sesion.createQuery("delete from ValueTbl u where u.idValue="+idValue);
        int i=q.executeUpdate();
        tx.commit();
        sesion.close();
                
    }
    /**
     * Funkcia getValueByType
     * @param type typ senzoru
     * @return vrati senzor s hodnotami z tabulky podla jeho typu
     */
    public List<ValueTbl> getValueByType(String type){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = sesion.beginTransaction();
         
         List<ValueTbl> l=null;
         Query q=sesion.createQuery("from ValueTbl u where u.type='"+type+"'");
         
         l=q.list();
         tx.commit();
         sesion.close();
         return l;
    }
}
