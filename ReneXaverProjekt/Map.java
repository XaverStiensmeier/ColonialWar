public class Map {
  public static void main(String [] args) {
    Pair[][] lMap = new Pair[5][5];           
        for (int i = 0; i < lMap.length; i++) {
          for (int i2 = 0; i2 < lMap.length; i2++)
          {  
            lMap[i][i2] = new Pair(0,null);
            System.out.println(lMap[i][i2]);
          }
        }
      }
      
  }
