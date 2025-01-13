package com.example.erronka;
public class Reservation {
    private int workerId;
    private int tableId;
    private String date;
    private String time;
    private String clientDNI;

    public Reservation(int workerId, int tableId, String date, String time, String clientDNI) {
        this.workerId = workerId;
        this.tableId = tableId;
        this.date = date;
        this.time = time;
        this.clientDNI = clientDNI;
    }

    public int getWorkerId() {
        return workerId;
    }

    public int getTableId() {
        return tableId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getClientDNI() {
        return clientDNI;
    }
}
