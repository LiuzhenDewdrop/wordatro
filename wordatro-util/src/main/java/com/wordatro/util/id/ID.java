package com.wordatro.util.id;

public interface ID<T> {
    /**
     * 生成新的ID
     * @return
     */
    T nextId();
}
