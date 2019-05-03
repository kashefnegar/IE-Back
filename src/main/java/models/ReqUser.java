package models;

public class ReqUser {


    private Register userREQ;
    private int bidAmount ;
    private Project project;

    public ReqUser(Register userREQ, int bidAmount) {
        this.userREQ = userREQ;
        this.bidAmount = bidAmount;
    }

    public Register getUserREQ() {
        return userREQ;
    }

    public int getBidAmount() {
        return bidAmount;
    }
}
