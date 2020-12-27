package Design._706;

import java.util.ArrayList;
import java.util.List;

class MyHashMap {

    List<Integer> keyList;
    List<Integer> valueList;

    public MyHashMap() {
        keyList = new ArrayList<>();
        valueList = new ArrayList<>();
    }

    public void put(int key, int value) {
        boolean found = false;
        for(int i = 0; i < keyList.size(); i ++){
            if(key == keyList.get(i)){
                found = true;
                valueList.set(i, value);
                break;
            }
        }

        if(!found){
            keyList.add(key);
            valueList.add(value);
        }

    }

    public int get(int key) {
        for(int i = 0; i < keyList.size(); i ++){
            if(key == keyList.get(i))
                return valueList.get(i);
        }
        return -1;

    }

    public void remove(int key) {
        for(int i = 0; i < keyList.size(); i ++){
            if(key == keyList.get(i)){
                keyList.remove(i);
                valueList.remove(i);
                break;
            }
        }
    }
}
