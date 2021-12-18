import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, String> map = new HashMap<Character ,String>();
        map.put('0',"0000");
        map.put('1',"0001");
        map.put('2',"0010");
        map.put('3',"0011");
        map.put('4',"0100");
        map.put('5',"0101");
        map.put('6',"0110");
        map.put('7',"0111");
        map.put('8',"1000");
        map.put('9',"1001");
        map.put('A',"1010");
        map.put('B',"1011");
        map.put('C',"1100");
        map.put('D',"1101");
        map.put('E',"1110");
        map.put('F',"1111");


        String text="";
        String cipher;
        String cipher2;
        System.out.println("Введите: " +
                "\n'1' если хотите определить шифротекст по ключу и открытому тексту " +
                "\n'2' если хотите определить ключ по открытому тексту и шифротексту:");

        int input = Integer.parseInt(reader.readLine());
        if(input==1) {
            System.out.println("Введите ключ шифрования (ключ должен быть в шестнадцатеричной системе счисления и должен быть разделен пробелами):");
            cipher= reader2.readLine();
            System.out.println("Введите открытый текст (размерность текста должна совпадать с размерностью ключа):");
        }else {
            System.out.println("Введите  шифротекст  : ");
            cipher= reader.readLine();

            System.out.println("Введите открытый текст (размерность текста должна совпадать с размерностью шифротекста):");
        }
        cipher2 = reader.readLine();
        cipher2=  characterto16(cipher2,map);

        String shifr = shifrovanie(cipher,cipher2,map);

        if(input==1) {
            System.out.println("Шифротекст : "+shifr);
        }else {
            System.out.println("Ключ : "+shifr);
        }
    }

    public static String characterto16 (String cipher,HashMap<Character, String> map) {
        char[] charArray = cipher.toCharArray();
        String finalcode="";
        for (char character : charArray) {
            String code = Integer.toString((int) character, 2);
            StringBuilder curcode = new StringBuilder(code);
            for (int j = 0; j < 8 - code.length(); j++) {
                curcode.insert(0, "0");
            }
            code = curcode.toString();
            finalcode = getString(map, finalcode, code);
        }
        return finalcode;
    }

    private static String getString(HashMap<Character, String> map, String finalcode, String code) {
        String val = code.substring(0, 4);
        String val2= code.substring(4);
        char nval=' ';
        char nval2=' ';

        for (Map.Entry<Character, String> characterStringEntry : map.entrySet()) {
            if (((Map.Entry) characterStringEntry).getValue().equals(val)) {
                nval = (char) ((Map.Entry) characterStringEntry).getKey();
            }
            if (((Map.Entry) characterStringEntry).getValue().equals(val2)) {
                nval2 = (char) ((Map.Entry) characterStringEntry).getKey();
            }
        }
        String v = String.valueOf(nval)+String.valueOf(nval2);
        finalcode=finalcode+v+" ";
        return finalcode;
    }

    public static String shifrovanie(String cipher, String cipher2,HashMap<Character, String> map) {
        String[] splt = cipher.split("\\s+");
        String[] splt2 = cipher2.split("\\s+");

        String finalcode="";
        for(int i=0;i<splt.length;i++) {

            char[] symbols = splt[i].toCharArray();
            String symbol = map.get(symbols[0])+map.get(symbols[1]);

            char[] symbols2 = splt2[i].toCharArray();
            String symbol2 = map.get(symbols2[0])+map.get(symbols2[1]);

            StringBuilder newSymbol = new StringBuilder();
            for(int j=0;j<symbol2.length();j++) {
                int number=	Character.digit(symbol2.charAt(j), 10);
                int number2 = Character.digit(symbol.charAt(j), 10);
                newSymbol.append(number ^ number2);
            }
            finalcode = getString(map, finalcode, newSymbol.toString());
        }
        return finalcode;
    }
}