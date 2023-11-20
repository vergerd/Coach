package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {
    private static Controle instance;
    //private static Profil profil;
    private static String nomFic = "saveprofil";
    //private AccesLocal accesLocal;
    private static AccesDistant accesDistant;

    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();

        private Controle() {
            super();
        }


    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(){
        if (instance == null){
            instance = new Controle();
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("tous", new JSONObject());
        }
        return instance;
    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil (Integer poids, Integer taille, Integer age, Integer sexe){
        Profil unProfil= new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(unProfil);
        accesDistant.envoi("enreg", unProfil.convertToJSONObject());
    }

    /**
     * Suppression d'un profil
     * @param unProfil
     */
    public void delProfil (Profil unProfil){
        accesDistant.envoi("suppr", unProfil.convertToJSONObject());
        lesProfils.remove(unProfil);
    }

    /**
     * getter sur le résultat du calcul de l'IMG du dernier profil
     * @return img du profil
     */
    public float getImg(){
        if (lesProfils.size() > 0){
            return lesProfils.get(lesProfils.size() - 1).getImg();
        }else{
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du dernier profil
     * @return message du profil
     */
    public String getMessage(){
        if(lesProfils.size() > 0){
            return lesProfils.get(lesProfils.size() - 1).getMessage();
        }else{
            return "";
        }
    }

    /**
     * getter sur la liste de profil
     * @return
     */
    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    /**
     * setter sur la liste de profil
     * @param lesProfils
     */
    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }
}
