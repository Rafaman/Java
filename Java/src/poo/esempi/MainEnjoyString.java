package poo.esempi;
import poo.util.*;

public class MainEnjoyString {
    public static void main(String[] args) {
        String[] s = {"zaino", "tana", "lupo", "abaco"};
        Vector<String> v = new EnjoyVector<>(s.length);
        for(int i = 0; i < s.length; i++){
            int j = 0;
            while(j < v.size() && v.get(j).compareTo(s[i]) < 0) j++;
            v.add(j, s[i]);
        }
        System.out.println(v);
        System.out.println(v.isEmpty());

        Character c = new Character('F');
        System.out.println(Character.toLowerCase(c));
        Character c1 = new Character('3');
        System.out.println(Character.getNumericValue(c1));
        System.out.println(Boolean.parseBoolean(null));
    }
}
