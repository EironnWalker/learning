package com.eironn.learning.demo.structure.linked.test;

public interface Linked {

    public Node get(int p);

    public void insert(int p, Object data);

    public void delete(int p);

    public void clean();

    public int size();

}
