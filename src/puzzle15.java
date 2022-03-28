import java.util.Scanner;
import java.util.ArrayList;

class puzzle15{
    private int[][] puzzle;
    private char[] moveDirection;
    private boolean usingFile;
    private ArrayList<int[][]> possiblePath;
    private int Xabsis;
    private int Xordinat;
    private int[][] gridcolor;

    public puzzle15(boolean uF){
        this.puzzle = new int[4][4];
        this.usingFile = uF;
        this.possiblePath = new ArrayList<int[][]>();
        this.Xabsis = -1;
        this.Xordinat = -1;
        this.moveDirection = new char[]{'U','R','D','L'};
        this.gridcolor = new int[][]
        {{0,1,0,1},
        {1,0,1,0},
        {0,1,0,1},
        {1,0,1,0}};

        if (uF){ // Input persoalan melalui file
            Scanner input = new Scanner(System.in);
            String file = input.nextLine();
            this.puzzle = FileProcess.matrixProcessing(file);
            input.close();
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

    private void displayPuzzle(int[][] puzzle){
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

    private int kurang(int[][] onepuzzle){
        int kurangI = 0;
        int[] puzzleseq = matToArr(onepuzzle);
        for (int i = 0; i < puzzleseq.length; i++){
            for (int j = i + 1; j < puzzleseq.length; j++){
                if (puzzleseq[i] > puzzleseq[j] && puzzleseq[j] != 0 && puzzleseq[i] != 0){
                    System.out.println(puzzleseq[i]);
                    System.out.println(puzzleseq[j]);
                    System.out.println();
                    kurangI++;
                }
                if (puzzleseq[i] == 0){
                    kurangI++;
                }
            }
        }
        return kurangI;
    }

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
    
    private int valueX(int i, int j){
        return (this.gridcolor[i][j]);
    }

    private boolean isReachable(int[][] onepuzzle){
        int theoremresult = this.kurang(onepuzzle);
        theoremresult += this.valueX(this.Xabsis, this.Xordinat);
        System.out.println("Nilai teorema : " + theoremresult);
        if (theoremresult % 2 == 0){
            return true;
        }
        return false;
        
    }
    /*
    public void solve(){
        for (char direction : this.moveDirection){ 
            statements using var;
        }
    }*/

    private int f(int[][] puzzle){
        return 0;
    }
    private int g(int[][] puzzle){
        return 0;
    }

    public static void main(String[] args) {
        puzzle15 test = new puzzle15(true);
        test.possiblePath.add(test.puzzle);
        test.displayPuzzle(test.possiblePath.get(0));
        System.out.println(test.isReachable(test.possiblePath.get(0)));

    }
}
