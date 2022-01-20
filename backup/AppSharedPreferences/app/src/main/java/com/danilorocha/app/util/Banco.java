package com.danilorocha.app.util;

import com.danilorocha.app.entity.Usuario;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Banco {
    private static Map<String, String> banco = new LinkedHashMap<>();

    public static Usuario pegarUsuario(String login) {
        Usuario usuario = new Usuario();
        banco.forEach(
                (user, senha) -> {
                    if (user.equals(login)) {
                        usuario.setLogin(user);
                        usuario.setSenha(senha);
                    }
                }
        );

        return usuario;
    }//metodo

    public static String salvarUsuario(String user, String senha) {
        String nome = pegarUsuario(user).getLogin();
        System.out.println("NOME: "+ nome);
        if (nome.equals(null)) {
            banco.put(user, senha);
            return "Cadastrado com sucesso";
        } else {
            return "Nome de usuário já cadastrado";
        }
    }//metodo

}//classe
