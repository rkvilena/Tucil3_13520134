import java.util.Scanner;

class puzzle15{
    private int[][] puzzle;
    private char[] moveDirection;
    private boolean usingFile;

    public puzzle15(boolean uF){
        this.puzzle = new int[4][4];
        this.usingFile = uF;
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
        this.displayPuzzle();
    }

    private void displayPuzzle(){
        for (int i =  0; i < this.puzzle.length; i++){
            for (int j =  0; j < this.puzzle[0].length; j++){
                System.out.print(this.puzzle[i][j]);
                if (j != this.puzzle[0].length - 1){
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        puzzle15 test = new puzzle15(false);
    }
}
