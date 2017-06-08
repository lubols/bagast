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
import pojos.UserTbl;
import utility.HibernateUtil;

/**
 * Trieda na pracu s tabulkou uzivatelov
 * @author Janči
 */
public class UserHelper {
      /**
       * Konstruktor triedy UserHelper
       */
      public UserHelper(){
        
    }
    /**
     * Funkcia saveDataUser - na pridanie noveho uzivatela do tabulky 
     * @param idUser ID uzivatela
     * @param name  meno uzivatela
     * @param gender pohlavie uzivatela
     * @param address emailova adresa uzivatela
     * @param heslo heslo uzivatela
     */
    public void saveDataUser(int idUser,String name,String gender,String address,String heslo){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        UserTbl ut = new UserTbl();
        if(idUser!=0){
            ut.setIdUser(idUser);
        }
        ut.setGender(gender);
        ut.setName(name);
        ut.setAddress(address);
        ut.setHeslo(heslo);
        
        sesion.saveOrUpdate(ut);
        tx.commit();
        sesion.close();
        
    }
    /**
     * Funkcia userList
     * @return vrati zoznam vsetkych uzivatelov z tabulky
     */
    public List<UserTbl> userList(){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        List<UserTbl> l=null;
        Query q=sesion.createQuery("from UserTbl u");
        l=q.list();
        tx.commit();
        sesion.close();
        return l;
    }
    /**
     * Funkcia updateName zmeni udaje uzivatela podla zadaneho ID
     * @param idUser ID uzivatela
     * @param name  meno uzivatela
     * @param gender pohlavie uzivatela
     * @param address emailova adresa uzivatela
     * @param heslo heslo uzivatela
     */
    public void updateName(int idUser, String name,String gender,String address, String heslo){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Query q=sesion.createQuery("update UserTbl u set u.name='"+name+"',u.gender='"+gender+"',u.address='"+address+"',u.heslo='"+heslo+"' where u.idUser="+idUser);
         
 
   
        int i=q.executeUpdate();
        tx.commit();
        sesion.close();
        
        
    }
    /**
     * Funkcia deleteUser vymaze uzivatela z tabulky podla ID
     * @param idUser ID uzivatela
     */
    public void deleteUser(int idUser){
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Query q=sesion.createQuery("delete from UserTbl u where u.idUser="+idUser);
        int i=q.executeUpdate();
        tx.commit();
        sesion.close();
                
    }
    
    /**
     * Funkcia getUserById
     * @param idUser ID  uzivatela
     * @return vrati uzivatela podla ID
     */
    public List<UserTbl> getUserById(int idUser){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = sesion.beginTransaction();
         
         List<UserTbl> l=null;
         Query q=sesion.createQuery("from UserTbl u where u.idUser="+idUser);
         
         l=q.list();
         tx.commit();
         sesion.close();
         return l;
    }
    /**
     * Funkcia getUserByName
     * @param name meno uzivatela
     * @return vrati uzivatela podla mena
     */
     public List<UserTbl> getUserByName(String name){
         Session sesion = HibernateUtil.getSessionFactory().openSession();
         Transaction tx = sesion.beginTransaction();
         
         List<UserTbl> l=null;
         Query q=sesion.createQuery("from UserTbl u where u.name='"+name+"'");
         
         l=q.list();
         tx.commit();
         sesion.close();
         return l;
    }
}


