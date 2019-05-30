package Esami.Teatro;

import java.util.ArrayList;

public class Gestione {
    private ArrayList<Attore> attori;
    private ArrayList<Spettacolo> spettacoli;


}

/*Si definiscano in Java le classi Attore, Spettacolo, Gestione. Oltre a scrivere eventuali metodi che si ritengano necessari
per realizzare l&rsquo;applicazione, occorre fornire almeno i seguenti metodi nella classe Gestione:
    1. public int spettacoliRicchi(String t). Il metodo restituisce il numero massimo di attori recitanti in uno spettacolo di tipologia t;
    2. public ArrayList<Spettacolo> spettacoliGiovani(). Il metodo restituisce la lista degli spettacoli tali che l&rsquo;et&agrave; media degli attori che
       vi partecipano &egrave; minima (tra tutti gli spettacoli presenti);
    3. public HashMap<String, Integer> attoriImpegnati(String t). Il metodo restituisce una HashMap in cui, per ogni attore che ha partecipato
       ad almeno uno spettacolo di tipologia t, &egrave; riportato il suo nome ed il numero complessivo di spettacoli di tipologia t in cui ha recitato.
    4. public ArrayList<Attore> attoriEclettici(). Il metodo restituisce la lista degli attori che hanno recitato in spettacoli di tutte le tipologie disponibili.*/