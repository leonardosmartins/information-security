public class Utils{
  public static double freq(char c){
    int i = (int)c;
    double vet[] = {0.1463, 0.104, 0.388, 0.499, 0.1257, 0.102, 0.130, 0.128, 0.618, 0.040, 0.002, 0.278, 0.474, 0.505, 0.1073, 0.252, 0.120, 0.653, 0.781, 0.474, 0.463, 0.167, 0.001, 0.021, 0.001, 0.047, 0.1538};
    if (c == ' ')
    	return 0.1538;
    else if (c == '.')
    	return 0.010;
    else if (c == ',')
    	return 0.017;
    else if((i - 97) < 0 || (i - 97) > 26)
    	return 0;


    return vet[i-97];
  }


  public static char intToChar(int a){
    return (char)(a+97);
  }

}
