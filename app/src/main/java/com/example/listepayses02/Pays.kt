package com.example.listepayses02

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

// Classe implantant un descriptif de pays
class Pays {
    private var nom: String? = null // nom du pays
    private var descriptif: String? = null // description textuelle du pays
    private var wikiUrl: String? = null // URL de la page Wiki du pays
    private var monnaie: String? = null // La monnaie du pays
    private var indicatif: String? = null // Indicatif du pays
    private var imageUrl: String? = null // URL de de l'image du pays

    // Constructeur par défaut
    constructor() {
        setNom(null)
        setDescriptif(null)
        setWikiUrl(null)
        setMonnaie(null)
        setIndicatif(null)
        setImageUrl(null)
    }

    // Constructeur paramétré
    constructor(nom: String?, descriptif: String?, wikiUrl: String?,monnaie: String?, indicatif: String?, imageUrl: String?) {
        setNom(nom)
        setDescriptif(descriptif)
        setWikiUrl(wikiUrl)
        setMonnaie(monnaie)
        setIndicatif(indicatif)
        setImageUrl(imageUrl)
    }

    // Accesseur de l'attribut nom
    fun getNom(): String? {
        return nom
    }
    // Mutateur de l'attribut nom
    fun setNom(nom: String?) {
        this.nom = nom
    }

    // Accesseur de l'attribut wikiUrl
    fun getWikiUrl(): String? {
        return wikiUrl
    }
    // Mutateur de l'attribut wikiUrl
    fun setWikiUrl(wikiUrl: String?) {
        this.wikiUrl = wikiUrl
    }
    //Accesseur de l'attribut monnaie
    fun getMonnaie ():String? {
        return monnaie
    }
    // Mutateur de l'attribut monnaie
    fun setMonnaie(monnaie: String?) {
        this.monnaie = monnaie
    }
    //Accesseur de l'attribut indicatif
    fun getIndicatif():String?{
        return indicatif
    }
    // Mutateur de l'attribut indicatif
    fun setIndicatif(indicatif: String?) {
        this.indicatif = indicatif
    }
    // Accesseur de l'attribut descriptif mais jamais utilise dans ce projet
    fun getDescriptif(): String? {
        return descriptif
    }

    // Mutateur de l'attribut descriptif
    fun setDescriptif(descriptif: String?) {
        this.descriptif = descriptif
    }

    // Accesseur de l'attribut imageUrl
    fun getImageUrl(): String? {
        return imageUrl
    }

    // Mutateur de l'attribut imageUrl
    fun setImageUrl(imageUrl: String?) {
        this.imageUrl = imageUrl
    }

    // Retourne une chaîne décrivant brièvement le pays
    override fun toString(): String {
        return getNom()!!
    }

    companion object {
        // Désérialiser une liste de pays d'un fichier JSON
        fun lireFichier(nomFichier: String, contexte: Context): ArrayList<Pays> {
            val liste = ArrayList<Pays>()
            try {
                // Charger les données dans un ArrayList
                val jsonString = readJson(nomFichier, contexte)
                val json = JSONObject(jsonString)
                val pays = json.getJSONArray("pays")

                // Lire chaque pays du fichier
                for (i in 0 until pays.length()) {
                    val p = Pays()
                    p.nom = pays.getJSONObject(i).getString("nom")
                    p.descriptif = pays.getJSONObject(i).getString("description")
                    p.wikiUrl = pays.getJSONObject(i).getString("wiki")
                    p.monnaie = pays.getJSONObject(i).getString("Monnaie")
                    p.indicatif = pays.getJSONObject(i).getString("Indicatif téléphonique")
                    p.imageUrl = pays.getJSONObject(i).getString("image")
                    liste.add(p)
                }
            } catch (e: JSONException) {
                // Une erreur s'est produite (on la journalise)
                e.printStackTrace()
            }
            return liste
        }

        //Read Json file into string
        fun readJson(nomFichier: String, contexte: Context): String? {
            var json: String? = null
            try {
                val inputStream: InputStream = contexte.assets.open(nomFichier)
                json = inputStream.bufferedReader().use { it.readText() }
            }catch (ex: IOException){
                // Une erreur s'est produite (on la journalise)
                ex.printStackTrace()
                return null
            }
            return json
        }
    }
}
