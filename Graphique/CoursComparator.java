package Graphique;

import java.util.Comparator;

public class CoursComparator implements Comparator<String> {
    private int MAX_LENGHT=0;

    @Override
    public int compare(String arg0, String arg1) {
      if(arg0.length()>MAX_LENGHT){
        MAX_LENGHT=arg0.length();
      }
      if(arg1.length()>MAX_LENGHT){
        MAX_LENGHT=arg1.length();
      }
      while(arg0.length()<MAX_LENGHT){
        arg0="0"+arg0;
      }
      while(arg1.length()<MAX_LENGHT){
        arg1="0"+arg1;
      }
      return arg0.compareTo(arg1);
    }
}
