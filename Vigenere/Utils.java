public class Utils{
  public static double freq(char c){
    int i = (int)c;
    double vet[] = {14.63, 1.04, 3.88, 4.99, 12.57, 1.02, 1.30, 1.28, 6.18, 0.40, 0.02, 2.78, 4.74, 5.05, 10.73, 2.52, 1.20, 6.53, 7.81, 4.74, 4.63, 1.67, 0.01, 0.21, 0.01, 0.47};
    return vet[i-97];
  }


  public static char intToChar(int a){
    return (char)(a+97);
  }

}
