package bm2.sinif;

import java.util.Stack;

public class Dönüşüm {
    
    // operatörün öceliğini belirleme
    public int oncelik(char c) {
        if(c == '^')
            return 3;
        else if(c == '/' || c == '*')
            return 2;
        else if(c == '+' || c == '-')
            return 1;
        else
            return -1;
    }
    
    public String infixtopostfix(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder sonuc = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //taranan karakter operand(sayı) ise sonuc'a ekle
            if(c >= '0' && c <= '9')
                sonuc.append(c);
            //taranan karakter '(' ise yığıta ekle
            else if(c == '(')
                st.push(c);
            //taranan karakter ')' ise yığıtta '('e kadar olanları sonuca ekle
            else if(c == ')') {
                while(st.peek() != '(') {
                    sonuc.append(st.pop());
                }
                st.pop();
            }
            //taranan karakter operatörse
            else {
                while(!st.isEmpty() && oncelik(c) < oncelik(st.peek())) {
                    sonuc.append(st.pop());
                }
                st.push(c);
            }   
        }
        //yığıttaki bütün elemanları çıkar
        while(!st.isEmpty()) {
            sonuc.append(st.pop());
        }
        return sonuc.toString();
    }
    
    public int hesapla(String s) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //taranan karakter sayı ise yığıta ekle
            if(c >= '0' && c <= '9')  //Character.isDigit(c)
                stack.push(c - '0');
            //taranan karakter operatör ise yığıttaki son iki elemana işlemi uygulayıp sonucu yığıta ekle
            else {
                int eleman1 = stack.pop();
                int eleman2 = stack.pop();
                switch(c) {
                    case '+':
                        stack.push(eleman2 + eleman1);
                        break;
                    case '-':
                        stack.push(eleman2 - eleman1);
                        break;
                    case '/':
                        stack.push(eleman2 / eleman1);
                        break;
                    case '*':
                        stack.push(eleman2 * eleman1);
                        break;
                    case '^':
                        int sonuc = 1;
                        for(int j = 0; j < eleman1; j++)
                            sonuc = sonuc * eleman2;
                        stack.push(sonuc);
                        break;
                }
            }
        }
        return stack.pop();
    }
}    
