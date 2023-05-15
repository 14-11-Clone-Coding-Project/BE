package com.example.clone_be.domain.chat.util;

import com.example.clone_be.domain.chat.dto.ChatDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
@Component
public class BadWordFiltering implements BadWords {
    private final Set<String> set = new HashSet<>(List.of(badWords));


    public ChatDto change(ChatDto chatDto) {
        String text = chatDto.getMessage();
        StringBuilder singBuilder = new StringBuilder("[");
        for (String sing : sings) singBuilder.append(Pattern.quote(sing));
        singBuilder.append("]*");
        String patternText = singBuilder.toString();

        for (String word : set) {
            if (word.length() == 1) text = text.replace(word, substituteValue);
            String[] chars = word.split("");
            text = Pattern.compile(String.join(patternText, chars))
                    .matcher(text)
                    .replaceAll(v -> substituteValue.repeat(v.group().length()));
        }
        chatDto.setMessage(text);
        return chatDto;
    }
}