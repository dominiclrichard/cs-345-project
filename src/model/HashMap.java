package model;

public class HashMap <K, V> {
        private static final double LOAD_FACTOR_THRESHOLD = 0.75;
        private Entry<K, V>[] buckets;
        private int size;
    
        @SuppressWarnings("unchecked")
        public HashMap() {
            // initallized at 128 because thats how many ASCII codes there are.
            buckets = new Entry[128];
            size = 0;
        }

        public int size() {
            return this.size;
        }

        public boolean containsKey(char character){
            for(char key: keySet()){
                if(key == character ){
                    return true;
                }
            }
            return false;
        }

        public char[] keySet() {
            char[] keysArray = new char[size];
            int index = 0;
    
            for (Entry<K, V> entry : buckets) {
                while (entry != null) {
                    if (entry.key instanceof Character) {
                        char key = (Character) entry.key;
                        if (index == 0 || !contains(keysArray, key, index)) {
                            keysArray[index++] = key;
                        }
                    }
                    entry = entry.next;
                }
            }
    
            // Create a new array with the correct size
            char[] uniqueKeysArray = new char[index];
            System.arraycopy(keysArray, 0, uniqueKeysArray, 0, index);
            return uniqueKeysArray;
        }
    
        private boolean contains(char[] array, char key, int size) {
            for (int i = 0; i < size; i++) {
                if (array[i] == key) {
                    return true;
                }
            }
            return false;
        }
    
        static class Entry<K, V> {
            K key;
            V value;
            Entry<K, V> next;
    
            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    
        private int hash(K key) {
            return key.hashCode() % buckets.length;
        }
    
        public void put(K key, V value) {
            if (loadFactor() >= LOAD_FACTOR_THRESHOLD) {
                resize();
            }
            int index = hash(key);
    
            while (buckets[index] != null && !buckets[index].key.equals(key)) {
                index = (index + 1) % buckets.length;
            }
    
            Entry<K, V> newEntry = new Entry<>(key, value);
    
            if (buckets[index] == null) {
                buckets[index] = newEntry;
                size++;
            } else {
                buckets[index].value = value;
            }
        }
    
        public V get(K key) {
            int index = hash(key);
    
            while (buckets[index] != null) {
                if (buckets[index].key.equals(key)) {
                    return buckets[index].value;
                }
                index = (index + 1) % buckets.length;
            }
            return null;
        }
    
        private double loadFactor() {
            return (double) size / buckets.length;
        }
    
        private void resize() {
            int newCapacity = buckets.length * 2;
            Entry<K, V>[] newBuckets = new Entry[newCapacity];
    
            for (Entry<K, V> entry : buckets) {
                if (entry != null) {
                    int index = entry.key.hashCode() % newCapacity;
                    while (newBuckets[index] != null) {
                        index = (index + 1) % newCapacity;
                    }
                    newBuckets[index] = entry;
                }
            }
            buckets = newBuckets;
        }
    }

