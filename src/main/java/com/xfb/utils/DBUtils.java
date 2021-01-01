package com.xfb.utils;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xfb
 */
public class DBUtils {

    public static Connection getConnection() throws Exception {

        final  String driver = "com.mysql.cj.jdbc.Driver";
        final  String url = "jdbc:mysql://localhost:3306/offers?user=root&password=123456&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
        Connection conn;
        // 取得数据库连接
        try {
            // 加载驱动程序
            Class.forName(driver);
            // 取得数据库连接，获得资源句柄
            conn = DriverManager.getConnection(url);
        }catch(SQLException e) {
            throw e;
        }
        return conn;
    }
    public static <T>List<T> getList(Class<T> clazz,String sql,Object...args) {
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<T> userList=null;
        try{
            connection = DBUtils.getConnection();
            ps= connection.prepareStatement(sql);
            if(args!=null && args.length>0) {
                for(int i=0;i<args.length;i++){
                    ps.setObject(1+i,args[i]);
                }
            }
            rs=ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            int colsnum=rsmd.getColumnCount();
            userList=new ArrayList<T>();
            while(rs.next()){
                Map<String,Object> rowMap=new HashMap<String,Object>();
                for (int i=1;i<=colsnum;i++){
                    String columnName= rsmd.getColumnLabel(i);
                    Object columnValue=rs.getObject(columnName);
                    if(columnValue instanceof  Date){
                        Date date=(Date)columnValue;
                        columnValue=new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName,columnValue);
                }
                T bean=clazz.getDeclaredConstructor().newInstance();

                for(Map.Entry<String,Object> entry:rowMap.entrySet()){
                    String propertyName= entry.getKey();
                    Object propertyValue=entry.getValue();
                    String methodName="set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
                    Method method = clazz.getMethod(methodName, propertyValue.getClass());
                    method.invoke(bean, propertyValue);
                }
                userList.add(bean);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(connection, ps, rs);
        }
        return userList;
    }

    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
        if(connection !=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static boolean save(String sql,Object...args) {
        Connection connection=null;
        PreparedStatement ps=null;
        Integer count=null;
        try{
            connection= DBUtils.getConnection();
            ps= connection.prepareStatement(sql);
            if(args!=null && args.length>0) {
                for(int i=0;i<args.length;i++){
                    ps.setObject(1+i,args[i]);
                }
            }
            count=ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(connection, ps, null);
        }
        return count!=null&&count>0?true:false;
    }

}
