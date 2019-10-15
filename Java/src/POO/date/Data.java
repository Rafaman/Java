package POO.date;

import java.util.GregorianCalendar;

public class Data {
    private final int GIORNO, MESE, ANNO;
    public enum Cosa {G, M, A}

    public Data(){
        GregorianCalendar gc = new GregorianCalendar();
        GIORNO = gc.get(GregorianCalendar.DAY_OF_MONTH);
        MESE = gc.get(GregorianCalendar.MONTH) + 1;
        ANNO = gc.get(GregorianCalendar.YEAR);
    }
    public Data(int giorno, int mese, int anno){
        if(anno < 0 || mese < 1 || mese > 12 || giorno > durataMese(mese, anno) || giorno < 1)
            throw new IllegalArgumentException("Argomento/i non validi");
        GIORNO = giorno;
        MESE = mese;
        ANNO = anno;
    }
    public Data(Data d){
        GIORNO = d.GIORNO;
        MESE = d.MESE;
        ANNO = d.ANNO;
    }
    public int get(Data.Cosa cosa){
        switch (cosa) {
            case G: return GIORNO;
            case M: return MESE;
            default: return ANNO;
        }
    }
    @Override
    public String toString() {
        return GIORNO + "/" + MESE + "/" + ANNO;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return GIORNO == data.GIORNO &&
                MESE == data.MESE &&
                ANNO == data.ANNO;
    }
    public static boolean isBisestile(int anno){
        if (anno < 0) throw new IllegalArgumentException("Anno non valido");
        if(anno % 4 != 0) return false;
        if(anno % 100 == 0 && anno % 400 != 0) return false;
        return true;
    }
    public static int durataMese(int mese, int anno){
        int durata = 0;
        if(anno < 0 || mese < 1 || mese > 12)
            throw new IllegalArgumentException("Argomento/i non validi");
        switch (mese) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                durata = 31;
                break;
            case 4: case 6: case 9: case 11:
                durata = 30;
                break;
            case 2:
                durata = isBisestile(anno) ? 29 : 28;
                break;
        }
        return durata;
    }
    public Data giornoSuccessivo(){
        int durata = durataMese(MESE, ANNO), g1, m1, a1;
        if(GIORNO == durata){
            g1 = 1;
            if(MESE == 12) {
                m1 = 1;
                a1 = ANNO + 1;
            } else {
                m1 = MESE + 1;
                a1 = ANNO;
            }
        } else {
            g1 = GIORNO + 1;
            m1 = MESE;
            a1 = ANNO;
        }
        return new Data(g1, m1, a1);
    }
    public Data giornoPrima(){
        int g1, m1, a1;
        if(MESE == 1 && GIORNO == 1 && ANNO == 0)
            throw new IllegalArgumentException("Non esiste giorno precedente");
        else if(MESE == 1 && GIORNO == 1){
            g1 = durataMese(MESE - 1, ANNO);
            m1 = MESE - 1;
            a1 = ANNO - 1;
        } else if(GIORNO == 1){
            g1 = durataMese(MESE - 1, ANNO);
            m1 = MESE - 1;
            a1 = ANNO;
        } else {
            g1 = GIORNO - 1;
            m1 = MESE - 1;
            a1 = ANNO;
        }
        return new Data(g1, m1, a1);
    }
}
