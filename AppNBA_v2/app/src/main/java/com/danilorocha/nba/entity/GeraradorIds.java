package com.danilorocha.nba.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeraradorIds {

   public static ArrayList<Integer> getIds(int quant, int min, int max) {
       ArrayList<Integer> numeros = new ArrayList<Integer>();
       while (numeros.size() < quant) {
           int num = random(min, max);
           if (!numeros.contains(num))
               numeros.add(num);
       }
       Collections.sort(numeros);
       return numeros;
    }//metodo

    private static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }//metodo

}//classe
