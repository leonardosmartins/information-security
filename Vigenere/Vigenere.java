
import java.io.*;
import java.util.Scanner;
import java.lang.Math.*;

public class Vigenere {

    static String encripta(String texto, String chave) throws Exception {

        byte[] bytes = texto.getBytes();

        int k = 0;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= chave.charAt(k);
            k = (++k) % chave.length();
        }

        return Util.byteArrayToHexString(bytes);
    }

    static String decripta(String textoCifrado, String chave) throws Exception {

        byte[] bytes = Util.hexStringToByteArray(textoCifrado);

        int k = 0;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= chave.charAt(k);
            k = (++k) % chave.length();
        }

        return new String(bytes, "ASCII");
    }

    static Integer keyLength(byte[] textoCifradoByte, int keyLengthProvavel, char letraFrequente) throws Exception {

    	float vetS[] = new float[textoCifradoByte.length/2];
    	byte[] byteSelecionados;
    	
    	for (int i = 1; i < textoCifradoByte.length/2; i++){
    		byteSelecionados = new byte[textoCifradoByte.length];
    		for(int j = 0, k = 0; j < textoCifradoByte.length; j += i, k++){
    			byteSelecionados[k] = textoCifradoByte[j];	
    		}

    		float vetQ[] = new float[256];
    		
    		for(int j = 0; j < byteSelecionados.length; j++){
    			if( (byteSelecionados[j]&255) != 0){
    				vetQ[byteSelecionados[j] & 255] += 1 ;
       			}
    		}


    		for(int j = 0; j < vetQ.length; j++){
    			vetQ[j] /= byteSelecionados.length;
    		}

    		for(int j = 0; j < vetQ.length; j++){
    			vetS[i] += (vetQ[j] * vetQ[j]);
    		}

    		//System.out.println("Tamanho chave: "+i+" => S = "+vetS[i]);
    		System.out.println(vetS[i]);
    	}

    	String a = attack(keyLengthProvavel, textoCifradoByte, letraFrequente);

    	return 1;
    }

    static String attack(Integer keyLength, byte[] textoCifradoByte, char letraFrequente) throws Exception {

    	byte[] keys = new byte[keyLength];
    		
    	for(int numberKey = 0; numberKey < keyLength; numberKey++){
    		int caracteres[] = new int[256];

    		for(int i = numberKey; i < textoCifradoByte.length; i += keyLength){
    			caracteres[textoCifradoByte[i]&255]++;
    		}

    		int maisFrequente = -1;
    		int iMaior = -1;

    		for(int i = 0; i < caracteres.length; i++){
    			if(caracteres[i] > maisFrequente){
    				maisFrequente = caracteres[i];
    				iMaior = i;
    			}
    		}

    		//byte[] b = Util.hexStringToByteArray("61");
    		//System.out.println("---");
    		//System.out.println("iMaior=" + iMaior);
    		// //System.out.println(bytes[iMaior]);
    		byte letraCifradaFrequente = (byte) iMaior;


    		//System.out.println(b[0]);
    		//System.out.println("---");
    		
    		keys[numberKey] = xor(letraCifradaFrequente,letraFrequente);

    		/*byte[] teste = new byte[1];
    		teste[0] = keys[ex];
    		String seila = Util.byteArrayToHexString(teste);
    		String lele = tt(seila);
    		keysCert[ex] = xor(betera, lele.charAt(0));
    		System.out.println("keysCert=" + keysCert[ex]);
    		*/
    		//System.out.println("Keys = " + (char)keys[numberKey]);
    	}


    	String keyCerta = Util.byteArrayToHexString(keys);

    	////System.out.println(keyCerta);

    		
    	return "ey";
    }


    public static byte xor(byte a, char b) throws Exception{
    	a ^= b;
    	return a;
    }

    public static String tt(String hex) {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < hex.length(); i+=2) {
        String str = hex.substring(i, i+2);
        output.append((char)Integer.parseInt(str, 16));
    }
    return output.toString();
}


    public static void main(String args[]) throws Exception {

      if (args.length < 2) { 
        System.err.println("java Vigenere [encripta|decripta|attack] [textoemclaro.txt ou textocifrado.txt] [chave.txt]");
        System.exit(-1);
      }

        if (args[0].startsWith("enc") ) {

           BufferedReader br = new BufferedReader(new FileReader(args[1]));
           StringBuffer textoEmClaro = new StringBuffer();
           while (br.ready()) {
            textoEmClaro.append(br.readLine()+"\n");
           }
           br.close();

           BufferedReader cFile = new BufferedReader(new FileReader(args[2])); 
           String chave = cFile.readLine();

           String textoCifrado = encripta(textoEmClaro.toString(), chave);
           System.out.println(textoCifrado);

        } else if (args[0].startsWith("dec")) {

           BufferedReader br = new BufferedReader(new FileReader(args[1]));
           StringBuffer textoCifrado = new StringBuffer();
           textoCifrado.append(br.readLine());
           br.close();

           BufferedReader cFile = new BufferedReader(new FileReader(args[2])); 
           String chave = cFile.readLine();

           String textoEmClaro = decripta(textoCifrado.toString(), chave);
           System.out.print(textoEmClaro);
        } else if (args[0].startsWith("att")){

           BufferedReader br = new BufferedReader(new FileReader(args[1]));
           StringBuffer textoCifrado = new StringBuffer();
           textoCifrado.append(br.readLine());
           br.close();

           byte[] bytes = Util.hexStringToByteArray(textoCifrado.toString());

           int tam = keyLength(bytes, Integer.parseInt(args[3]), args[4].charAt(0));

/*           BufferedReader cFile = new BufferedReader(new FileReader(args[2])); 
           String chave = cFile.readLine();

           String textoEmClaro = decripta(textoCifrado.toString(), chave);
           //System.out.print(textoEmClaro);
*/        }

    }

}


class Util {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String byteArrayToHexString(byte[] bytes) throws Exception {

        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}