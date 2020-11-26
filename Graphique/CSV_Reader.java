import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSV_Reader{
  private String[] colones;
  private String[][] lignes;

//Il y a une erreur si toutes les lignes n'ont pas la même longeure (colone inclu)

  public CSV_Reader(String FileName){
    try{
      BufferedReader src = new BufferedReader(new FileReader(FileName));
      String line = src.readLine();
      colones=line.replace("\"","").split(",");
      lignes=new String[0][colones.length];
      line = src.readLine();
      while (line != null) {
        lignes=Arrays.copyOf(lignes,lignes.length+1);
        lignes[lignes.length-1]=line.replace("\"","").split(",");
        line = src.readLine();
      }
    }
  catch(FileNotFoundException e){
    System.err.println("fichier non trouvé: " + e.toString());
    }
  catch(IOException e){
    e.printStackTrace();
    }
  }

  public String[] getColones(){
    return colones;
  }

  public String[][] getLignes(){
    return lignes;
  }
}
