//package com.alpha;
//
//import java.util.List;
//
//public class Train {
//    private int id;
//
//    private List<Carriage> carriages;
//
//    public Train(int id, List<Carriage> carriages) {
//        this.id = id;
//        this.carriages = carriages;
//    }
//
//
//    public int getGeneralCapacity(){
//        int capacity = 0;
//        for(Carriage carriage: carriages){
//            capacity+=carriage.getCapacity();
//        }
//        return capacity;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public List<Carriage> getCarriages() {
//        return carriages;
//    }
//
//    public void setCarriages(List<Carriage> carriages) {
//        this.carriages = carriages;
//    }
//
//    @Override
//    public String toString() {
//        return "Train{" +
//                "id=" + id +
//                "\n, carriages=" + carriages +
//                '}';
//    }
//}
