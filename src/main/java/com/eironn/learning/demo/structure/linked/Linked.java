package com.eironn.learning.demo.structure.linked;

public interface Linked {

    Node get(int p);

    void insert(int p, Object data);

    void delete(int p);

    void clean();

    int size();

}
