package Model;

import java.io.*;

public class PathSaver {

    public PathSaver(String path) {
        for (int i = path.length()-1 ; i > 0 ; i--) {
            if(path.charAt(i) == '\\'){
                path = path.substring(0,i);
                break;
            }
        }
        try {
            write(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void write(String path) throws IOException {
        FileWriter myWriter = new FileWriter("data/pathToSave.txt");
        myWriter.write(path);
        myWriter.close();
    }

    public static String readPath(){
        try {
            File file = new File("data/pathToSave.txt");

            BufferedReader br = new BufferedReader(new FileReader(file));

            return br.readLine();
        }catch (IOException e) {
            return "";
        }
    }
}
