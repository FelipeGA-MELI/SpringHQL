package com.example.demo.service;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Service
public class UsuarioService  {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public ArrayList<UsuarioModel> obtenerUsuariosPorNombre(String nombre){
        return (ArrayList<UsuarioModel>) usuarioRepository.findUsuarioModelByName(nombre);
    }

    //Não utilizar entityManager!
    public ArrayList<UsuarioModel> obtenerUsuariosPorNombre2(String nombre){

        ArrayList<UsuarioModel> users = (ArrayList<UsuarioModel>) entityManager.
                createQuery("select u from UsuarioModel u where u.userName like ?1 order by u.userName")
                .setParameter(1, nombre)
                .getResultList();
        return users;
    }

    public void guardarUsuario(UsuarioModel nuevoUsuario){
        usuarioRepository.insertUsuario(nuevoUsuario.getPassword(), nuevoUsuario.getUserName());
    }

}
