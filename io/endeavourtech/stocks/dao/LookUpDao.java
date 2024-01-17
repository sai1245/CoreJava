package io.endeavourtech.stocks.dao;

import io.endeavourtech.stocks.StockException;
import io.endeavourtech.stocks.vo.SectorVo;
import io.endeavourtech.stocks.vo.SubSectorVo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LookUpDao extends BaseDao {


    public List<SubSectorVo> getAllSubSectorID(List<Integer> subSectorIds)  {
        List<SubSectorVo> listOfSubSectors= new ArrayList<>();
        String sqlQuery= """
                select
                    sl.subsector_id,
                    sl.subsector_name,
                    sl.sector_id
                from
                    endeavour.subsector_lookup sl
                where sl.subsector_id in (""";
        String newString="?,".repeat(subSectorIds.size());
        String newSubString=newString.substring(0,newString.length()-1);
        sqlQuery+=newSubString+")";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < subSectorIds.size(); i++) {
                preparedStatement.setInt(i + 1, subSectorIds.get(i));

            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SubSectorVo subSectorVO = new SubSectorVo();
                subSectorVO.setSubSectorId(resultSet.getInt(1));
                subSectorVO.setSubSectorName(resultSet.getString(2));
                subSectorVO.setSectorId(resultSet.getInt(3));

                listOfSubSectors.add(subSectorVO);

            }
        }catch(SQLException e){
                e.printStackTrace();
            throw new StockException("Unable retrieve Sunsector data from teh data set",e);
            }

        return listOfSubSectors;
    }

    public List<SectorVo> getAllSectors() {
        String sqlQuery = """
                select
                	*
                from
                	endeavour.sector_lookup sl
                """;
        List<SectorVo> sectorVoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SectorVo sectorVo = new SectorVo();
                sectorVo.setSectorName(resultSet.getString("sector_name"));
                sectorVo.setSectorID(resultSet.getInt("sector_id"));
                sectorVoList.add(sectorVo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StockException("Unable retrieve SectorLookup data from teh data set",e);
        }
        return sectorVoList;

    }


    public List<SubSectorVo> getAllSubSectors() {
        String sqlQueary = """
                                
                select\s
                	*
                from\s
                	endeavour.subsector_lookup sl\s
                """;
        List<SubSectorVo> subSectorVoList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueary);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SubSectorVo subSectorVo = new SubSectorVo();
                subSectorVo.setSubSectorId(resultSet.getInt("subsector_id"));
                subSectorVo.setSubSectorName(resultSet.getString("subsector_name"));
                subSectorVo.setSectorId(resultSet.getInt("sector_id"));
                subSectorVoList.add(subSectorVo);
            }
        } catch (SQLException e) {
            throw new StockException("Exceptio in LookUp getAllSubsectors",e);
        }
        return subSectorVoList;
    }
}
