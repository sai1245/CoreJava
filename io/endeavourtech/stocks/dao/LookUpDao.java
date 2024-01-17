package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LookUpDao extends BaseDao{
    public List<SectorVo> getAllSectors() throws SQLException {
        String sqlQuery = """
                select
                	*
                from
                	endeavour.sector_lookup sl
                """;
        List<SectorVo> sectorVoList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()){
             SectorVo sectorVo= new SectorVo();
             sectorVo.setSectorName(resultSet.getString("sector_name"));
             sectorVo.setSectorID(resultSet.getInt("sector_id"));
             sectorVoList.add(sectorVo);
         }
        return sectorVoList;
    }

    public List<SubSectorVo> getAllSubSectors() throws SQLException{
        String sqlQueary= """
                
                select\s
                	*
                from\s
                	endeavour.subsector_lookup sl\s
                """;
        List<SubSectorVo> subSectorVoList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQueary);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            SubSectorVo subSectorVo = new SubSectorVo();
            subSectorVo.setSubSectorId(resultSet.getInt("subsector_id"));
            subSectorVo.setSubSectorName(resultSet.getString("subsector_name"));
            subSectorVo.setSectorId(resultSet.getInt("sector_id"));
            subSectorVoList.add(subSectorVo);
        }
        return subSectorVoList;
    }
}
