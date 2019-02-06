package ru.inno.task05;

import java.util.Set;

/**
 * Возвращает набор предложений, извлеченных из
 * полученного текстового источника
 *
 * @author Alexey Balakin
 */
public interface ResourceParser {

    /**
     * @param source источник, содержащий предложения
     * @return набор извлеченных предложений
     */
    Set<String> getSentences(String source);
}
