package com.example.coach.controleur;

import static com.example.coach.outils.Serializer.deSerialize;
import static com.example.coach.outils.Serializer.serialize;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;

import java.util.Date;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private AccesLocal accesLocal;

        private Controle(Context context) {
        super();
        accesLocal = AccesLocal.getInstance(context);
        profil = accesLocal.recupDernier();
        //recupSerialize(context);
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context context){
        if (Controle.instance == null){
            Controle.instance = new Controle(context);
        }
        return Controle.instance;
    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil (int poids, int taille, int age, int sexe, Context context){
        profil = new Profil(poids, taille, age, sexe, new Date());
        //serialize(nomFic, profil, context);
        accesLocal.ajout(profil);
    }

    /**
     * getter sur le résultat du calcul de l'IMG pour le profil
     * @return img du profil
     */
    public float getImg(){
        if (profil != null){
            return profil.getImg();
        }else{
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du profil
     * @return message du profil
     */
    public String getMessage(){
        if(profil != null){
            return profil.getMessage();
        }else{
            return "";
        }
    }

    /**
     * getter sur la taille du profil
     * @return taille du profil
     */
    public Integer getTaille(){
        if(profil != null){
            return profil.getTaille();
        }else{
            return null;
        }
    }

    /**
     * getter sur le poids du profil
     * @return poids du profil
     */
    public Integer getPoids(){
        if(profil != null){
            return profil.getPoids();
        }else{
            return null;
        }
    }

    /**
     * getter sur l'age du profil
     * @return age du profil
     */
    public Integer getAge(){
        if(profil != null){
            return profil.getAge();
        }else{
            return null;
        }
    }

    /**
     * getter sur le sexe du profil
     * @return sexe du profil
     */
    public Integer getSexe(){
        if(profil != null){
            return profil.getSexe();
        }else{
            return null;
        }
    }

    /**
     * Récupération du profil sérialisé
     * @param context
     */
   // private static void recupSerialize(Context context){
   //     profil = (Profil)deSerialize(nomFic, context);
   //
   // }

}
