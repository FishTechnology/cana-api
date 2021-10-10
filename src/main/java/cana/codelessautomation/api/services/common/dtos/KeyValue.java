package cana.codelessautomation.api.services.common.dtos;

import lombok.Data;

@Data
public class KeyValue<K,V> {
    private K key;
    private V value;
}
