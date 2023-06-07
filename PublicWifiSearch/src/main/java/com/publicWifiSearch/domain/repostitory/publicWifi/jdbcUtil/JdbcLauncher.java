package com.publicWifiSearch.domain.repostitory.publicWifi.jdbcUtil;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class JdbcLauncher {
    private static final int BATCH_SIZE = 1000;
    private static final int ZERO = 0;
    private static final int NO_REMINDER = 0;
    private final Connection connection;


    public JdbcLauncher(Connection connection) {
        this.connection = connection;
    }

    public ResultSet executeQueryWithPreparedStatement(SqlStatement sqlStatement) throws SQLException{
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = sqlStatement.makePreparedStatement(connection);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }

        return resultSet;
    }
    public ResultSet executeQueryWithPreparedStatement(SqlStatement sqlStatement, ParameterHelper parameterHelper) throws SQLException{
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = sqlStatement.makePreparedStatement(connection);
            parameterHelper.setParameter(preparedStatement, 0);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }

        return resultSet;
    }

    public void executeUpdateWithPreparedStatement(SqlStatement sqlStatement) throws SQLException{
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = sqlStatement.makePreparedStatement(connection);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    public void executeBatch(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        preparedStatement.clearParameters();
    }
    public <T> void executeUpdateBatchWithPreparedStatement(SqlStatement sqlStatement, List<T> publicWifiDetail, ParameterHelper parameterHelper) throws SQLException {
        PreparedStatement preparedStatement=null;
        long totalData = publicWifiDetail.size();
        long numberOfCurrentData = ZERO;
        try {
            connection.setAutoCommit(false);
            preparedStatement = sqlStatement.makePreparedStatement(connection);

            for (int idx = ZERO; idx < publicWifiDetail.size(); idx++) {

                parameterHelper.setParameter(preparedStatement, idx);
                preparedStatement.addBatch();

                if (idx % BATCH_SIZE == NO_REMINDER) {
                    numberOfCurrentData = idx;
                    executeBatch(preparedStatement);
                }
            }
            if (numberOfCurrentData < totalData) {
                executeBatch(preparedStatement);
            }
            connection.commit();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        finally {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
        }
    }
}
