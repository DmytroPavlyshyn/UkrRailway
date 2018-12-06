package com.alpha;

import java.util.List;

public class Train {
    private int id = count++;
    private static int count = 1;
    private List<Carriage> carriages;
    public Train(List<Carriage> carriages) {
        this.carriages = carriages;
    }
}
