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

    static void keyLength(byte[] textoCifradoByte, int keyLengthProvavel) throws Exception {

    	float vetS[] = new float[textoCifradoByte.length/2];
    	byte[] byteSelecionados;

    	for (int i = 1; i < textoCifradoByte.length/2; i++){
    		byteSelecionados = new byte[(textoCifradoByte.length+(i-1))/i];
    		for(int j = 0, k = 0; j < textoCifradoByte.length; j += i, k++){
    			byteSelecionados[k] = textoCifradoByte[j];	
    		}

    		float vetQ[] = new float[256];
    		
    		for(int j = 0; j < byteSelecionados.length; j++){
    			if( (byteSelecionados[j] & 255) != 0){
    				vetQ[byteSelecionados[j] & 255] += 1 ;
       			}
    		}


    		for(int j = 0; j < vetQ.length; j++){
    			vetQ[j] /= byteSelecionados.length;
    		}

    		for(int j = 0; j < vetQ.length; j++){
    			vetS[i] += (vetQ[j] * vetQ[j]);
    		}

    	}
    		
    	for(int i = 0; i<vetS.length; i++){
    		// System.out.println(vetS[i]);
    	}

    	attack(keyLengthProvavel, textoCifradoByte);

    }

    static void attack(Integer keyLength, byte[] textoCifradoByte) throws Exception {

    	char[] keys = new char[keyLength];
		int []lengthV = new int[keyLength];

		int resto= textoCifradoByte.length % keyLength;
		for (int i=0; i<resto; i++){
			lengthV[i] = (textoCifradoByte.length+(keyLength-1))/keyLength;
		}
		if (resto != 0){
			for (int i=resto; i<keyLength; i++){
				lengthV[i] = ((textoCifradoByte.length+(keyLength-1))/keyLength) - 1;
			}
		}else{
			for (int i=resto; i<keyLength; i++){
				lengthV[i] = ((textoCifradoByte.length+(keyLength-1))/keyLength);
			}
		}


    	for(int numberKey = 0; numberKey < keyLength; numberKey++){
    		float[] vetX = new float[27];
    		byte[] byteSelecionados = new byte[lengthV[numberKey]];
    		byte[] byteXOR = new byte[byteSelecionados.length];
    		float[] vetQ = new float[256];



    		for(int i = numberKey, k = 0; i < textoCifradoByte.length; i += keyLength, k++){
    			byteSelecionados[k] = textoCifradoByte[i];
    		}

    		int z = 0;
    		byte []teste = new byte[1];

    		for(char c = 'a'; c <= 'z'; c++, z++){
    			for(int i = 0; i < byteSelecionados.length; i++){
	    			byteXOR[i] = xor(byteSelecionados[i], c);
	    		}
	    		for(int i = 0; i < byteXOR.length; i++){
	    			vetQ[byteXOR[i] & 255]++;
	    		}
	    		for(int i = 0; i < vetQ.length; i++){
	    			vetQ[i] /= byteXOR.length;
	    		}
	    		for(int i = 0; i < byteXOR.length; i++){
	    			teste[0] = byteXOR[i];
	    			vetX[z] += Utils.freq(((char) (byteXOR[i] & 255) )) * vetQ[byteXOR[i] & 255];
	    		}
    		}
	    		
    		float maior = -1;
    		int iMaior = -1;

    		for(int i = 0; i < vetX.length; i++){
    			if(vetX[i] > maior){
    				maior = vetX[i];
    				iMaior = i;	
    			}
    		}

    		keys[numberKey] = Utils.intToChar(iMaior);
   		}
		
		for (int k=0; k<keys.length; k++)
			System.out.print(keys[k]);
		System.out.print("\n");

    	
	}


    public static byte xor(byte a, char b) throws Exception{
    	a ^= b;
    	return a;
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

           keyLength(bytes, Integer.parseInt(args[3]));

        }

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