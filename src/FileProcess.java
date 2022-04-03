import java.io.FileReader;
import java.io.IOException;

public class FileProcess {
    public static int[][] matrixProcessing(String nameFile){
        String firstStep = fileInput(nameFile);
        int[][] matrixstr = strToMat(firstStep);

/*
        for (int i =  0; i < matrixstr.length; i++){
            for (int j =  0; j < matrixstr[0].length; j++){
                System.out.print(matrixstr[i][j]);
                if (j != matrixstr[0].length - 1){
                    System.out.print("\t");
                }
            }
            System.out.println("");
        }
*/

        return matrixstr;
    }

    public static String fileInput(String strFile)
    {
        String strConv = "";
        String namaFile = "../test/" + strFile;
        try {
            FileReader fRead = new FileReader(namaFile);

            int ch;
            while ((ch = fRead.read()) != -1) {
                strConv += (char)ch;
            }
            fRead.close();
        }
        catch (IOException e) {
            System.out.println("Pembacaan file masukan error.");
        }
        return strConv;
    }

    public static int[][] strToMat(String mat){
        String[] hasilSplit = mat.split("\n");
        int[][] matrix = new int[4][4];
        for (int i = 0; i < 4; i++){
            String[] arr2 = hasilSplit[i].split(" ");
            for (int j = 0; j < 4; j++){
                matrix[i][j] = strToInt(arr2[j]);
            }
        }
        return matrix;
    }

    public static int[][] convertToInt(String[][] matrixstr){
        int[][] matrixint = new int[4][4];
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                matrixint[i][j] = Integer.valueOf(matrixstr[i][j]);
            }
        }

        return matrixint;
    }

    private static int strToInt(String numstr){
        int intresult = 0;
        numstr = numstr.replace("\n", "").replace("\r", "");
        int strlen = numstr.length();
        if(strlen == 1){
            intresult = numstr.charAt(0);
            intresult -= 48;
        }
        else{
            for (int i = 0; i < strlen; i++){
                char tempchar = numstr.charAt(i);
                int tempint = tempchar;
                tempint -= 48;
                intresult = intresult + tempint * (int)Math.pow(10, strlen - 1 - i);
                
            }
        }
        return intresult;
    }

    /*
    public static String[] arrayProcessing(String nameFile){
        String firstStep = fileInput(nameFile);
        return strToArr(firstStep);
    }*/

    /*
    public static String[] strToArr(String arr){
        String[] hasilSplit = arr.split("\n");
        boolean startWordlist = false;
        int i = 0;
        while (!startWordlist){
            boolean isWhitespace = hasilSplit[i].matches("^\\s*$");
            if (isWhitespace){
                startWordlist = true;
            }
            i++;
        }
        String[] wordList = new String[hasilSplit.length - i];
        for (int s = 0; s < wordList.length; s++){
            wordList[s] = hasilSplit[i];
            wordList[s] = wordList[s].replaceAll("\\s","");
            i++;
        }
        return wordList;
    }
    */
}