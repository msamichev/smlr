package ru.sam.smlr.service;

import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by msamichev on 31.03.2017.
 */
@Component
public class DefaultKeyConverterService implements KeyConverterService {

    private char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-_".toCharArray();
    private Map<Character, Integer> charToLong = IntStream.range(0, chars.length)
            .collect(HashMap::new, (m, i) -> m.put(chars[i], i), Map::putAll);

    @Override
    public String idToKey(Long id) {
        long n = id;
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            sb.append(chars[(int) (n % chars.length)]);
            n /= chars.length;
        }

        return sb.reverse().toString();
    }

    @Override
    public Long keyToId(String key) {
        long res = 0L;
        for (int i = 0; i < key.length(); i++) {
            res = res * chars.length + charToLong.get(key.charAt(i));
        }
        return res;
    }
}
