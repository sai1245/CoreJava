package io.endeavourtech.stocks.vo;

import java.util.Objects;

public class SubSectorVo implements Comparable<SubSectorVo> {

    private int subSectorId;

    private int sectorId;
    private String subSectorName;

    public int getSubSectorId() {
        return subSectorId;
    }

    public void setSubSectorId(int subSectorId) {
        this.subSectorId = subSectorId;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getSubSectorName() {
        return subSectorName;
    }

    public void setSubSectorName(String subSectorName) {
        this.subSectorName = subSectorName;
    }

    @Override
    public String toString() {
        return "SubSectorVo{" +
                "subSectorId=" + subSectorId +
                ", sectorId=" + sectorId +
                ", subSectorName='" + subSectorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubSectorVo that = (SubSectorVo) o;
        return subSectorId == that.subSectorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subSectorId);
    }

    @Override
    public int compareTo(SubSectorVo otherObj) {
        if(this.getSubSectorId()==otherObj.subSectorId)
        return 0;
        else if (this.getSubSectorId() > otherObj.subSectorId)
            return -1;
        else
            return 1;
    }
}
