package poo.util;
//BackTracking
public class NRegine {
	private boolean board[][];
	private int n;
	public NRegine(int n) {
		if(n<4) throw new IllegalArgumentException();
		this.n=n;
		board = new boolean[n][n];//all false by default
	}
	public void risolvi() {
		collocaRegina(0);
	}
	private void collocaRegina(int row) {
		for(int col=0;col<n;++col)
			if(assegnabile(row,col)) {
				assegna(row,col);
				if(row==n-1) scriviSoluzione();
				else collocaRegina(row+1);
				deassegna(row,col);
			}
	}
	public boolean assegnabile(int row, int col) {
		//verifica nord
		for(int r=row-1;r>=0;--r)
			if(board[r][col]) return false;
		//verifica nord-est
		for(int r = row-1, c = col+1;r>=0 && c<n;--r,++c)
			if(board[r][c]) return false;
		//verifica nord-ovest
		for(int r=row-1,c=col-1;r>=0 && c>=0;--r,--c)
			if(board[r][c]) return false;
		return true;
	}
	private void assegna(int row, int col) {
		board[row][col]=true;
	}
	private void deassegna(int row, int col) {
		board[row][col] = false;
	}
	private int numSol = 0;
	private void scriviSoluzione() {
		numSol++;
		System.out.print(numSol + ": ");
		for(int i=0; i<n;++i)
			for(int j = 0;j<n;++j)
				if(board[i][j]) {
					System.out.print("<"+i+","+j+">");
					break;
				}
		System.out.println();//andare a capo
	}
	public static void main(String[] args) {
		new NRegine(5).risolvi();
	}
}
