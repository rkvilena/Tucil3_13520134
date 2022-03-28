import java.util.Scanner;
import java.util.ArrayList;

class puzzle15{
    private int[][] puzzle;
    private char[] moveDirection;
    private boolean usingFile;
    private ArrayList<int[][]> possiblePath;

    public puzzle15(boolean uF){
        this.puzzle = new int[4][4];
        this.usingFile = uF;
        this.possiblePath = new ArrayList<int[][]>();
        this.moveDirection = new char[]{'U','R','D','L'};

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
        for (int x = 0; x < onepuzzle.length; x++){
            for (int y = 0; y < onepuzzle[0].length; y++){
                System.out.println(onepuzzle[x][y]);
                System.out.println();
                for (int i = 0; i < onepuzzle.length; i++){
                    for (int j = 0; j < onepuzzle[0].length; j++){
                        if ((onepuzzle[x][y] > onepuzzle[i][j]) && (onepuzzle[i][j] != 0)){
                            System.out.println(onepuzzle[x][y]);
                            System.out.println(onepuzzle[i][j]);
                            System.out.println();
                            kurangI++;
                        }
                    }
                }
            }
        }
        
        return kurangI;
    }

    private boolean isReachable(int[][] onepuzzle){

        return true;
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
        System.out.println(test.kurang(test.possiblePath.get(0)));

    }
}
