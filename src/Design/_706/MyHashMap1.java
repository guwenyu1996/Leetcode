package Design._706;

import java.util.ArrayList;
import java.util.List;

class Pair<U, V> {
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

class Bucket{
    List<Pair<Integer, Integer>> bucket;

    public Bucket(){
        bucket = new ArrayList<>();
    }

    public Integer get(int key){
        for(Pair<Integer, Integer> pair: bucket){
            if(pair.first.equals(key))
                return pair.second;
        }
        return -1;
    }

    public void put(int key, int value){
        boolean found = false;
        for(Pair<Integer, Integer> pair: bucket){
            if(pair.first.equals(key)){
                pair.second = value;
                found = true;
                break;
            }
        }

        if(!found)
            bucket.add(new Pair<Integer, Integer>(key, value));
    }

    public void remove(int key){
        for(Pair<Integer, Integer> pair: bucket){
            if(pair.first.equals(key)){
                bucket.remove(pair);
                break;
            }
        }
    }

}

class MyHashMap1 {
    int key;
    List<Bucket> hashTable;

    /** Initialize your data structure here. */
    public MyHashMap1() {
        key = 2069;
        hashTable = new ArrayList<Bucket>();
        for(int i = 0; i < key; i ++)
            hashTable.add(new Bucket());
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hashKey = key % this.key;
        hashTable.get(hashKey).put(key, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hashKey = key % this.key;
        return hashTable.get(hashKey).get(key);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hashKey = key % this.key;
        hashTable.get(hashKey).remove(key);
    }
}
