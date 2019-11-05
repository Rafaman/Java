package poo.razionali;

class Razionale {
    private int NUMERATORE, DENOMINATORE;
    private static int contatore = 0;

    public Razionale(int NUMERATORE, int DENOMINATORE){
        if (DENOMINATORE == 0)
            throw new IllegalArgumentException("DENOMINATORE uguale a zero!");
        if (NUMERATORE != 0) {
            if (DENOMINATORE < 0){
                this.NUMERATORE = -1 * NUMERATORE;
                this.DENOMINATORE = -1 * DENOMINATORE;
            } else {
                this.NUMERATORE = NUMERATORE;
                this.DENOMINATORE = DENOMINATORE;
            }
            semplifica();
        } else {
            DENOMINATORE = 1;
            this.NUMERATORE = 0;
            this.DENOMINATORE = DENOMINATORE;
        }
        contatore++;
    }
    public Razionale(Razionale r){
        NUMERATORE = r.NUMERATORE;
        DENOMINATORE = r.DENOMINATORE;
        contatore++;
    }
    public int getNUMERATORE() {
        return NUMERATORE;
    }
    public int getDENOMINATORE() {
        return DENOMINATORE;
    }
    @Override
    public String toString() {
        if (DENOMINATORE == 1)
            return "Razionale{" + NUMERATORE + "}";
        if(NUMERATORE == 0)
            return "Razionale{0}";
        return "Razionale{" + NUMERATORE + "/" + DENOMINATORE +
                '}';
    }
    public Razionale moltiplica(int f){
        int n1 = 0;
        return new Razionale(n1 = NUMERATORE * f, DENOMINATORE);
    }
    public Razionale moltiplica(Razionale r){
        return new Razionale(NUMERATORE * r.NUMERATORE, DENOMINATORE * r.DENOMINATORE);
    }
    public Razionale dividi(Razionale r){
        return new Razionale(NUMERATORE * r.DENOMINATORE, DENOMINATORE * r.NUMERATORE);
    }
    public Razionale aggiungi(Razionale r){
        int mcm = (DENOMINATORE * r.DENOMINATORE) / mcd(NUMERATORE, DENOMINATORE);
        int d = mcm;
        int n = mcm / DENOMINATORE * NUMERATORE + mcm / r.DENOMINATORE * r.NUMERATORE;
        return new Razionale(n, d);
    }
    public Razionale sottrai(Razionale r){
        return aggiungi(moltiplica(-1));
    }
    private void semplifica(){
        int n = Math.abs(NUMERATORE);
        int d = Math.abs(DENOMINATORE);
        int cd = mcd(n, d);
        NUMERATORE /= cd;
        DENOMINATORE /= cd;
    }
    /* Static significa che il metodo lavora solo con i parametri che gli vengono passati */
    public static int mcd_euclide(int x, int y){
        if(x <= 0 || y <= 0)
            throw new IllegalArgumentException("Argomenti non positivi|");
        return mcd(x, y);
    }
    private static int mcd(int x, int y){
        if(y == 0) return x;
        return mcd(y, x % y);
    }
    public static int quantiRazionali(){
        return contatore;
    }
    protected void finalize(){
        contatore--;
    }
}
