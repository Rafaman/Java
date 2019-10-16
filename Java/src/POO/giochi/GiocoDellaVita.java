package POO.giochi;

public class GiocoDellaVita {
    private char [][] mappa, nuovaMappa;
    private int n, m;
    public GiocoDellaVita( int n, int m ) {
        if( n<=0 || m<=0 )
            throw new IllegalArgumentException();
        this.n=n; this.m=m;
        mappa=new char[n][m];
        nuovaMappa=new char[n][m];
        for( int i=0; i<n; ++i )
            for( int j=0; j<m; ++j )
                mappa[i][j]='.';
    }
    public void assegnaOrganismo( int i, int j ) {
        if( i<0||i>=n||j<0||j>=m )
            throw new IllegalArgumentException();
        mappa[i][j]='*';
    }
    public void configuraRandom() {
        for( int i=0; i<n; ++i )
            for( int j=0; j<m; ++j )
                if( Math.random()<0.5 ) mappa[i][j]='*';
                else mappa[i][j]='.';
    }
    private int vicini( int i, int j ) {
        int conta=0;
        if( i>0 && mappa[i-1][j]=='*' ) conta++; //NORD
        if( i>0 && j<m-1 && mappa[i-1][j+1]=='*' ) conta++; //NE
        if( j<m-1 && mappa[i][j+1]=='*' ) conta++; //EST
        if( i<n-1 && j<m-1 && mappa[i+1][j+1]=='*') conta++;
        if( i<n-1 && mappa[i+1][j]=='*' ) conta++;
        if( i<n-1 && j>0 && mappa[i+1][j-1]=='*' ) conta++;
        if( j>0 && mappa[i][j-1]=='*' ) conta++;
        if( i>0 && j>0 && mappa[i-1][j-1]=='*' ) conta++;
        return conta;
    }
    public void prossimaGenerazione() {
        for( int i=0; i<n; ++i )
            for( int j=0; j<m; ++j ) {
                int v=vicini(i,j);
                if( mappa[i][j]=='*' )
                    nuovaMappa[i][j]=( v==2 || v==3 ) ? '*' : '.';
                else
                    nuovaMappa[i][j]=( v==3 ) ? '*' : '.';
            }
        char[][] park=mappa;
        mappa=nuovaMappa;
        nuovaMappa=park;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[0].length; j++) {
                sb.append(mappa[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public static void main( String[] args ) {
        GiocoDellaVita g=new GiocoDellaVita(5,7);
        g.configuraRandom();
        System.out.println(g);
        for( int i=0; i<5; ++i ) {
            g.prossimaGenerazione();
            System.out.println(g);
        }
    }
}
