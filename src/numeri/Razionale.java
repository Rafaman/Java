package numeri;

/**
I numeri razionali.
Sono dotati delle principali funzioni dei razionali, 
tra le quali somma, differenza, rapporto, reciproco, etc.
La classe genera oggetti mutabili
*/

public class Razionale implements Comparable<Razionale>
{	/**
    Il numero viene rappresentato mediante
    una coppia di interi.
    Il denominatore è sempre positivo
    Il numero è sempre ridotto ai minimi termini
    */
    private int numeratore;
	private int denominatore;

	/**
    Costruisce un razionale a partire da
    due interi.
    @param numeratore sarà il numeratore del razionale
    @param denominatore sarà il denominatore del razionale.
    Deve essere diverso da 0.
    */
    public Razionale(int numeratore, int denominatore)
	{	this.numeratore = numeratore;
		this.denominatore = denominatore;
		if(denominatore < 0)
		{	this.numeratore *= -1;
			this.denominatore *= -1;
		}
		semplifica();
	}
    
    /**
    Costruttore per copia.
    Costruisce un razionale identico a quello ricevuto
    @param r il razionale identico a quello che vogliamo costruire
    */
    public Razionale(Razionale r)
    {	numeratore = r.numeratore;
    	denominatore = r.denominatore;
    	// Soluzione più elegante ma meno efficiente:
		//this(r.numeratore,r.denominatore);
    }
    
    /**
    Costruttore di default. Genera il razionale 0.
    */
    public Razionale()
    {	numeratore = 0;
    	denominatore = 1;
    	// Soluzioni più eleganti ma meno efficienti:
		//this(0);
    	//oppure
    	//this(0,1);
    }

    /**
    Costruisce un razionale a partire da un intero.
    Il denominatore vale 1, per default.
    @param numero l'intero dal quale costruiamo il razionale
    */
    public Razionale(int numero)
	{	numeratore = numero;
		denominatore = 1;
		// Soluzione più elegante ma meno efficiente:
		// this(numero,1);
	}

    /**
    Confronta il proprietario del metodo con un altro razionale.
    @param r il razionale con cui fare il confronto
    @return il risultato del confronto, per come previsto dall'interfaccia Comparable
    */
    public int compareTo(Razionale r)
	{	int sinistra = numeratore * r.denominatore;
		int destra = r.numeratore * denominatore;
		if(sinistra < destra)
			return -1;
		if(sinistra == destra)
			return 0;
		return 1;		
	}
	
    /**
    Rappresenta l'oggetto come stringa.
    @return una stringa che rappresenta l'oggetto
    */
    public String toString()
	{	if(denominatore == 1)
			return ""+numeratore;
		return numeratore + "/" + denominatore;
	}

	/**
    Verifica se l'oggetto è uguale ad un altro razionale.
    @param  o un oggetto che deve essere
    un razionale
    @return true, se l'oggetto è uguale
    ad o; false, altrimenti
    */
    public boolean equals(Object o)
	{	if(this==o)
			return true;
		if(!(o instanceof Razionale))
			return false;
		Razionale r = (Razionale)o;
		return numeratore == r.numeratore &&
				denominatore == r.denominatore;
	}
	
    /**
    Calcola un valore hash per l'oggetto
    @return il valore hash
    */
    public int hashCode()
	{	return toString().hashCode();		
	}
	
    /**
    Restituisce il numeratore dell'oggetto
    @return il numeratore dell'oggetto
    */
    public int getNumeratore()
	{	return numeratore;		
	}
	
    /**
    Restituisce il denominatore dell'oggetto
    @return il denominatore dell'oggetto
    */
    public int getDenominatore()
	{	return denominatore;		
	}
	
    /**
    Aggiunge il razionale r all'oggetto
    @param r il razionale da aggiungere
    */
    public void aggiungi(Razionale r)
	{	numeratore = numeratore * r.denominatore + denominatore * r.numeratore;
		denominatore *= r.denominatore;
		semplifica();
	}
	
    /**
    Sottrae il razionale r dall'oggetto
    @param r il razionale da sottrarre
    */
    public void sottrai(Razionale r)
	{	Razionale s = new Razionale(-1*r.numeratore,r.denominatore);
		aggiungi(s);
	}

	/**
    Moltiplica l'oggetto per il razionale r.
    @param r il razionale da moltiplicare
    */
    public void moltiplica(Razionale r)
	{	numeratore *= r.numeratore;
		denominatore *= r.denominatore;
		semplifica();
	}
	
    /**
    Eleva l'oggetto ad una potenza intera
    @param p la potenza a cui elevare l'oggetto
    */
    public void eleva(int p)
	{	numeratore = (int) Math.pow(numeratore, p);
		denominatore = (int) Math.pow(denominatore, p);
	}
	
    /**
    Calcola il reciproco dell'oggetto
    @return il reciproco dell'oggetto
    */
    public Razionale reciproco()
	{	return new Razionale(denominatore,numeratore);		
	}
	
    /**
    Semplifica l'oggetto.
    */
    private void semplifica()
	{	if (numeratore == 0)
			denominatore = 1;
		else
		{	int divisore = mcd(Math.abs(numeratore),denominatore);
			numeratore /= divisore;
			denominatore /= divisore;
		}
	}

    /**
    Calcola il massimo comun divisore di due numeri.
    @param a uno dei numeri
    @param b l'altro numero
    @return il massimo comun divisore di a e b
     */
    private static int mcd(int a, int b) {
        int m = a;
        if (a>b)
            m = b;
        boolean trovato = false;
        while (!trovato)
            if ((a%m==0) && (b%m==0))
                trovato = true;
            else
                m--;
        return m;
    }
    
    /**
    Calcola la somma di due razionali.
    @param r1 un razionale
    @param r2 un razionale
    @return un nuovo razionale pari alla somma di r1 ed r2
    */
    public static Razionale somma(Razionale r1, Razionale r2)
    {	Razionale s = new Razionale(r1);
    	s.aggiungi(r2);
    	return s;    	
    }
    
    /**
    Calcola la differenza di due razionali.
    @param r1 un razionale
    @param r2 un razionale
    @return un nuovo razionale pari alla differenza tra r1 ed r2
    */
    public static Razionale differenza (Razionale r1, Razionale r2) {
	    Razionale r3 = new Razionale(r1);
	    r3.sottrai(r2);
	    return r3;
    }

    /**
    Calcola il rapporto di due razionali.
    @param r1 un razionale
    @param r2 un razionale diverso da 0
    @return un nuovo razionale pari al rapporto r1 ed r2
    */
    public static Razionale rapporto (Razionale r1, Razionale r2) {
	    Razionale r3 = new Razionale(r1);
	    r3.moltiplica(r2.reciproco());
	    return r3;
    }

    /**
    Calcola il prodotto di due razionali.
    @param r1 un razionale
    @param r2 un razionale
    @return un nuovo razionale pari al prodotto di r1 ed r2
    */
    public static Razionale prodotto (Razionale r1, Razionale r2) {
	    Razionale r3 = new Razionale(r1);
	    r3.moltiplica(r2);
	    return r3;
    }

    /**
    Calcola la potenza intera di un razionale.
    @param r un razionale
    @param p la potenza intera
    @return un nuovo razionale pari a r elevato p
    */
    public static Razionale eleva(Razionale r,int p) {
        Razionale ret=new Razionale(r);
        ret.eleva(p);
        return ret;
    }
}
