package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SharedMemory {
    private final List<Token> tokens=new ArrayList<>();
    public SharedMemory(int n) {
        for(int i=1;i<n*n*n;i++)
            tokens.add(new Token(i));
        Collections.shuffle(tokens);

    }
    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            Random random=new Random();
            int index;
            if(tokens.size()>1)
                index= random.nextInt(0,tokens.size());
            else index=0;

            extracted.add(tokens.get(index));
            tokens.remove(tokens.get(index));

        }
        return extracted;
    }
}