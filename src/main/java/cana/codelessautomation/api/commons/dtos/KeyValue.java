package cana.codelessautomation.api.commons.dtos;

import lombok.Data;

@Data
public class KeyValue<K,V> {
    private K key;
    private V value;
}
