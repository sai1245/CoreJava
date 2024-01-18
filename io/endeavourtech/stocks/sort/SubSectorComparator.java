package io.endeavourtech.stocks.sort;

import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.util.Comparator;

public class SubSectorComparator implements Comparator<SubSectorVo> {
    @Override
    public int compare(SubSectorVo o1, SubSectorVo o2) {
        return o2.getSubSectorName().compareTo(o1.getSubSectorName());
    }
}
