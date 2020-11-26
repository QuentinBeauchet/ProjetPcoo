public class Test{
  public static void main(String[] args) {
    CSV_Reader csv= new CSV_Reader("minutes_info.csv");
    String[] c=csv.getColones();
    String[][] l=csv.getLignes();
  }
}
