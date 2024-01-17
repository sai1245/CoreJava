package io.endeavourtech.stocks.vo;

public class SectorVo{
    private int sectorID;

    private String sectorName;

    public int getSectorID() {
        return sectorID;
    }

    public void setSectorID(int sectorID) {
        this.sectorID = sectorID;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    @Override
    public String toString() {
        return "SectorVo{" +
                "sectorID=" + sectorID +
                ", sectorName='" + sectorName + '\'' +
                '}';
    }
}
