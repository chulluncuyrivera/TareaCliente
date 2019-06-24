/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import interfaces.ICliente;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;

/**
 *
 * @author USUARIO
 */
public class ClienteDao implements ICliente {

    @Override
    public boolean guardarCliente(Cliente cliente) {
     Session session = null;
        boolean resp = true;
        try {
            //construir una nueva session y transaccion
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaccion = session.beginTransaction(); //inicia
            //registra en la base de datos
            session.save(cliente);
            transaccion.commit();
        } catch (Exception e) {
            System.out.println("Error al guardar. " + e);
            resp = false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resp;
    }
 
    

    @Override
    public ArrayList<Cliente> listarCliente() {
     Session session = null;
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //consulta hacia la base de datos
            String hql = "FROM Cliente";
            Query query = session.createQuery(hql);
            //ejecuta la consulta y obtener la lista. array: castea
            lista = (ArrayList<Cliente>) query.list();
        } catch (Exception e) {
            System.out.println("ERROR EN LISTAR::" + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return lista;
    }  
    

    @Override
    public boolean actualizarCliente(Cliente cliente) {
       System.out.println("erer" + cliente.getNombre());
        boolean resp = true;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaccion = session.beginTransaction();
            session.update(cliente);
            transaccion.commit();
        } catch (Exception e) {
            resp = false;
            System.out.println("ERROR EN ACTU::" + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resp;
    }
    @Override
    public boolean eliminarCliente(Cliente cliente) {
       Session sesion = null;
        boolean resp = true;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(cliente);
            sesion.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("ERROR DAO::" + e);
            resp = false;
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            
         
    }
        }
        return resp;
    
}
}
