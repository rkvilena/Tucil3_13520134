// import java.util.Scanner;
import java.util.*;
class puzzle15{
    private int[][] puzzle;
    private char[] moveDirection;
    private boolean usingFile;
    private int Xabsis;
    private int Xordinat;
    private int[][] gridcolor;
    public ArrayList<PuzzleState> possiblePath;
    public Deque<Integer> solutionpath;
    public int[] kurangItable;
    
    // Khusus untuk visualisasi di GUI
    public double time;
    public int visualidx;
    public int kurangI;
    public int X;

    // Konstruktor kelas puzzle15
    public puzzle15(boolean uF, String file){
        this.puzzle = new int[4][4];
        this.usingFile = uF;
        this.possiblePath = new ArrayList<>();
        this.solutionpath = new ArrayDeque<>();
        this.Xabsis = -1;
        this.Xordinat = -1;
        this.moveDirection = new char[]{'U','R','D','L'};
        this.kurangItable = new int[16];
        this.time = 0.0000;
        this.visualidx = 0;
        this.gridcolor = new int[][]
        {{0,1,0,1},
        {1,0,1,0},
        {0,1,0,1},
        {1,0,1,0}};

        if (this.usingFile){ // Input persoalan melalui file
            this.puzzle = FileProcess.matrixProcessing(file);
        }
        else{ // Persoalan dibuat secara acak menggunakan random()
            int[] alreadyGenerated = new int[16];
            for (int i =  0; i < this.puzzle.length; i++){
                for (int j =  0; j < this.puzzle[0].length; j++){
                    int generatedInt = (int)(Math.random()*16);
                    while (alreadyGenerated[generatedInt] == 1){
                        generatedInt = (int)(Math.random()*16);
                    }
                    this.puzzle[i][j] = generatedInt;
                    alreadyGenerated[generatedInt] = 1;
                }
            }
        }
    }
    
    public int[][] getPuzzle(){
    	return this.puzzle;
    }

    // Method untuk tampilkan matriks puzzle
    public void displayPuzzle(int[][] puzzle){
        for (int i =  0; i < puzzle.length; i++){
            for (int j =  0; j < puzzle[0].length; j++){
                System.out.print(puzzle[i][j]);
                if (j != puzzle[0].length - 1){
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
    }

    // Method implementasi KURANG(i)
    private int kurang(int[][] onepuzzle){
        int kurangI = 0;
        int[] puzzleseq = matToArr(onepuzzle);
        for (int i = 0; i < puzzleseq.length; i++){
        	int kItablevis = 0;
            for (int j = i + 1; j < puzzleseq.length; j++){
                if (puzzleseq[i] > puzzleseq[j] && puzzleseq[j] != 0 && puzzleseq[i] != 0){
                    kurangI++;
                    kItablevis++;
                }
                if (puzzleseq[i] == 0){
                    kurangI++;
                    kItablevis++;
                }
            }
            if (puzzleseq[i] == 0) {
            	this.kurangItable[15] = kItablevis;
            }
            else {
            	this.kurangItable[puzzleseq[i] - 1] = kItablevis;
            }
        }
        return kurangI;
    }

    // Method ubah matriks menjadi array
    // Khusus digunakan untuk perhitungan KURANG(i)
    private int[] matToArr(int[][] onepuzzle){
        int size = onepuzzle.length * onepuzzle.length;
        int k = 0;
        int[] sequence = new int[size];
        for (int i =  0; i < onepuzzle.length; i++){
            for (int j =  0; j < onepuzzle[0].length; j++){
                if (onepuzzle[i][j] == 0){
                    this.Xabsis = i;
                    this.Xordinat = j;
                }
                sequence[k] = onepuzzle[i][j];
                k++;
            }
        }
        return sequence;
    }
    
    // Method untuk menentukan nilai X
    private int valueX(int i, int j){
        return (this.gridcolor[i][j]);
    }

    // Method untuk menentukan bisa tidaknya puzzle diselesaikan
    // Mengembalikan true apabila bisa
    // Mengembalikan false apabila tidak bisa
    public boolean isReachable(int[][] onepuzzle){
    	this.kurangI = this.kurang(onepuzzle);
    	this.X = this.valueX(this.Xabsis, this.Xordinat);
    	
        int theoremresult = this.kurangI;
        theoremresult += this.X;
        if (theoremresult % 2 == 0){
            return true;
        }
        return false;
        
    }

    // Method untuk memulai penyelesaian puzzle
    // dengan cara mencatat koordinat 0
    // dan membangkitkan antrian child pertama
    private void solveInitial(){
        for (int i =  0; i < this.puzzle.length; i++){
            for (int j =  0; j < this.puzzle[0].length; j++){
                if (this.puzzle[i][j] == 0){
                    this.Xabsis = i;
                    this.Xordinat = j;
                }
            }
        }
        PuzzleState firstPuzzle = new PuzzleState(this.puzzle, -999, this.Xabsis, this.Xordinat, 0, 'X', -1, false);
        firstPuzzle.alreadyChoosed = true;
        this.possiblePath.add(firstPuzzle);
        this.move(this.puzzle, this.Xabsis, this.Xordinat, 1, 'X', 0, 0);
    }

    // Method untuk melakukan pencarian setelah antrian
    // child yang pertama dibangkitkan 
    private PuzzleState solveTheRest(){
        boolean finished = false;
        PuzzleState lastPS = new PuzzleState();
        while (!finished){
            PuzzleState chosenp = new PuzzleState();
            int maxDepth = 0;
            int pPathIdx = 0;
            for (int i = 0; i < this.possiblePath.size(); i++){
                if (maxDepth < this.possiblePath.get(i).depth && (!this.possiblePath.get(i).alreadyChoosed)){
                    chosenp.setPuzzleState(this.possiblePath.get(i));
                    pPathIdx = i;
                }
            }
            for (int k = 0; k < this.possiblePath.size(); k++){
                int cost = this.possiblePath.get(k).cost;
                if ((cost != -999) && (!this.possiblePath.get(k).alreadyChoosed)){
                    if (chosenp.cost > cost){
                    	this.possiblePath.get(k).alreadyChoosed = true;
                        chosenp.setPuzzleState(this.possiblePath.get(k));
                        pPathIdx = k;
                    }
                    else if (chosenp.cost == cost){
                        if (chosenp.depth < this.possiblePath.get(k).depth){
                        	this.possiblePath.get(k).alreadyChoosed = true;
                            chosenp.setPuzzleState(this.possiblePath.get(k));
                            pPathIdx = k;
                        }
                    }
                }
            } // Puzzle dengan cost paling kecil sudah dipilih
            
            // Memastikan apakah puzzle yang dipilih sudah memenuhi solusi
            if (pToSolution(chosenp) == 0){
                finished = true;
                lastPS.setPuzzleState(chosenp);
                this.solutionpath.push(pPathIdx);
            }
            else{
//            	System.out.println("Chosen : ");
//                displayPuzzle(chosenp.instancepuzzle);
                this.move(chosenp.instancepuzzle, chosenp.x0, chosenp.y0, 1, chosenp.prevMove, chosenp.depth, pPathIdx);
            }
        }
        return lastPS;
    }
    public puzzle15 solve(){
    	System.out.println("Solving...");
        // Mulai waktu pencarian
        long start = System.nanoTime();

        // Lakukan pencarian
        solveInitial();
        PuzzleState finalState = solveTheRest();

        // Hentikan dan catat waktu pencarian
        long elapsedTime = System.nanoTime() - start;
        this.time = elapsedTime / 1_000_000_000.0;

        // Menuliskan indeks dari jalan start menuju solusi puzzle
        this.writePathIdx(finalState);
        System.out.println("Puzzle Solved!");
        return this;
    }

    // Method untuk mencatat indeks solusi
    // Dilakukan rekursi dari daun ke akar pohon
    private void writePathIdx(PuzzleState ps){
        // Melakukan pencatatan indeks solusi
        // dengan syarat indeks != -1
        if (ps.prevStateIdx != -1){
            this.solutionpath.push(ps.prevStateIdx);
            this.writePathIdx(this.possiblePath.get(ps.prevStateIdx));
        }
        
    }
    
    // Method untuk memeriksa apakah puzzle yang dibangkitkan
    // sudah pernah ada di possible path
	public boolean isPuzzleInPP(int[][] a) {
		int p = 0;
		while (p < this.possiblePath.size()) {
			int[][] b = this.possiblePath.get(p).instancepuzzle;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(a[i][j] != b[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

    // Method untuk menghitung cost
    private int cost(PuzzleState puzzle){
        return rootToP(puzzle) + pToSolution(puzzle);
    }

    // Method untuk menghitung f(P) dari permasalahan puzzle
    // f(P) adalah jarak dari akar ke node P
    private int rootToP(PuzzleState puzzle){
        return puzzle.depth;
    }

    // Method untuk menghitung g(P) dari permasalahan puzzle
    // g(P) adalah estimasi jarak dari P ke solusi yang diharapkan
    private int pToSolution(PuzzleState puzzle){
        int k = 1; // Value acuan posisi puzzle
        int gridstillwrong = 0; // Jumlah posisi yang tidak tepat
        for (int i =  0; i < puzzle.instancepuzzle.length; i++){
            for (int j =  0; j < puzzle.instancepuzzle[0].length; j++){
                if (puzzle.instancepuzzle[i][j] == 0){ // Penanganan khusus untuk value nol
                    // Gagalkan pengecekan ketidakcocokan
                }
                else if (puzzle.instancepuzzle[i][j] != k){ // Value di [i][j] tidak tepat
                    gridstillwrong++;
                }
                k++;
            }   
        }
        return gridstillwrong;
    }

    // Method untuk membangkitkan semua kemungkinan langkah
    private void move(int[][] puzzle, int zeroX, int zeroY, int zeroMov, char lastMovement, int lastLevel, int thisidx){	
        int[][] arisedstate = new int[4][4];
        for (int i =  0; i < arisedstate.length; i++){
            for (int j =  0; j < arisedstate[0].length; j++){
                arisedstate[i][j] = puzzle[i][j];
            }
        }
        int x = zeroX;
        int y = zeroY;
        int temp = puzzle[x][y];
        int currentZM = lastLevel + zeroMov;

        for (int i = 0; i < this.moveDirection.length; i++){
            switch(this.moveDirection[i]) {
                case 'U':
                    if (x != 0 && lastMovement != 'D'){
                        // Lakukan pergerakan 0
                        arisedstate[x][y] = arisedstate[x-1][y];
                        arisedstate[x-1][y] = temp;
                        
                        if (!isPuzzleInPP(arisedstate)) {
                        	// Bangkitkan puzzle dengan posisi baru
                            // Beserta informasi relevan seperti cost, posisi x dan y nya 0
                            PuzzleState possiblestate;
                            possiblestate = new PuzzleState(arisedstate, 0, x-1, y, currentZM, 'U', thisidx, false);
                            possiblestate.cost = cost(possiblestate);
                            this.possiblePath.add(possiblestate);
                        }
                        // Kembalikan ke kondisi semula
                        temp = arisedstate[x-1][y];
                        arisedstate[x-1][y] = arisedstate[x][y];
                        arisedstate[x][y] = temp;
                    }
                    break;
                case 'R':
                    if (y != arisedstate[0].length - 1  && lastMovement != 'L'){
                        arisedstate[x][y] = arisedstate[x][y+1];
                        arisedstate[x][y+1] = temp;
                        
                        if (!isPuzzleInPP(arisedstate)) {
                        	// Bangkitkan puzzle dengan posisi baru
                            // Beserta informasi relevan seperti cost, posisi x dan y nya 0
                        	PuzzleState possiblestate;
                            possiblestate = new PuzzleState(arisedstate, 0, x, y+1, currentZM, 'R', thisidx, false);
                            possiblestate.cost = cost(possiblestate);
                            this.possiblePath.add(possiblestate);
                        }

                        temp = arisedstate[x][y+1];
                        arisedstate[x][y+1] = arisedstate[x][y];
                        arisedstate[x][y] = temp;
                    }
                    break;
                case 'D':
                    if (x != arisedstate.length - 1 && lastMovement != 'U'){
                        arisedstate[x][y] = arisedstate[x+1][y];
                        arisedstate[x+1][y] = temp;
                        
                        if (!isPuzzleInPP(arisedstate)) {
                        	// Bangkitkan puzzle dengan posisi baru
                            // Beserta informasi relevan seperti cost, posisi x dan y nya 0
                        	PuzzleState possiblestate;
                            possiblestate = new PuzzleState(arisedstate, 0, x+1, y, currentZM, 'D', thisidx, false);
                            possiblestate.cost = cost(possiblestate);
                            this.possiblePath.add(possiblestate);
                        }

                        temp = arisedstate[x+1][y];
                        arisedstate[x+1][y] = arisedstate[x][y];
                        arisedstate[x][y] = temp;
                    }
                    break;
                case 'L':
                    if (y != 0 && lastMovement != 'R'){
                        arisedstate[x][y] = arisedstate[x][y-1];
                        arisedstate[x][y-1] = temp;
                        
                        if (!isPuzzleInPP(arisedstate)) {
                        	// Bangkitkan puzzle dengan posisi baru
                            // Beserta informasi relevan seperti cost, posisi x dan y nya 0
                        	PuzzleState possiblestate;
                            possiblestate = new PuzzleState(arisedstate, 0, x, y-1, currentZM, 'L', thisidx, false);
                            possiblestate.cost = cost(possiblestate);
                            this.possiblePath.add(possiblestate);
                        }

                        temp = arisedstate[x][y-1];
                        arisedstate[x][y-1] = arisedstate[x][y];
                        arisedstate[x][y] = temp;
                    }
                    break;
                default:
                    break;
            }
        }
    }

//    public static void main(String[] args) {
//        puzzle15 test = new puzzle15(true);
//        if (test.isReachable(test.puzzle)){
//            test.solve();
//        }
//        else{
//            System.out.println("Unreachable");
//        }
//    }
}

// Kelas untuk menampung data hasil pembangkitan
// Bertujuan untuk efisiensi waktu algoritma
class PuzzleState{
    public int[][] instancepuzzle;
    public int cost;
    public int x0;
    public int y0;
    public int depth;
    public char prevMove;
    public int prevStateIdx;
    public boolean alreadyChoosed;

    public PuzzleState(){
        this.instancepuzzle = new int[4][4];
        this.cost = 0;
        this.x0 = -1;
        this.y0 = -1;
        this.depth = 0;
        this.prevMove = 'X';
        this.prevStateIdx = -1;
        this.alreadyChoosed = false;
    }
    public PuzzleState(int[][] p, int c, int x, int y, int d, char m, int psi, boolean ac){
        this.instancepuzzle = new int[4][4];
        this.cost = c;
        this.x0 = x;
        this.y0 = y;
        this.depth += d;
        this.prevMove = m;
        this.prevStateIdx = psi;
        this.alreadyChoosed = ac;
        for (int i =  0; i < this.instancepuzzle.length; i++){
            for (int j =  0; j < this.instancepuzzle[0].length; j++){
                this.instancepuzzle[i][j] = p[i][j];
            }
        }
    }
    public void setPuzzleState(PuzzleState ps){
        this.instancepuzzle = new int[4][4];
        this.cost = ps.cost;
        this.x0 = ps.x0;
        this.y0 = ps.y0;
        this.depth = ps.depth;
        this.prevMove = ps.prevMove;
        this.prevStateIdx = ps.prevStateIdx;
        this.alreadyChoosed = ps.alreadyChoosed;

        for (int i =  0; i < this.instancepuzzle.length; i++){
            for (int j =  0; j < this.instancepuzzle[0].length; j++){
                this.instancepuzzle[i][j] = ps.instancepuzzle[i][j];
            }
        }
    }
}