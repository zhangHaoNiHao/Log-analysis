package com.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class JDBCUtil {
	public static String URL="jdbc:mysql://192.168.120.100:3306/log?useUnicode=true&characterEncoding=utf8";
	public static String USER="root";
	public static String PWD="123456";
	
	public static Connection getConnection(){
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static void Close(Connection con,PreparedStatement pstmt,ResultSet rs){
		try {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//增删改操作
	public static int executeUpdate(String sql,Object... params){
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Close(con,pstmt,null);
		}
		return result;
	}


	public static void main(String[] args) {
		
		/* 测试executeQuery()类
		List<Student> students = JDBCUtil.executeQuery("select * from student", new RowMap<Student>(){
			
			public Student rowMapping(ResultSet rs) {
				Student student = new Student();
				
				try {
					student.setId(rs.getInt("id"));
					student.setName(rs.getString("name"));
					student.setAge(rs.getInt("age"));
					student.setSex(rs.getString("sex"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return student;
			}
			
		}, null);
		*/
		/*
		Student s = new Student();
		s.setId(100);
		s.setName("zhagn");
		s.setAge(10);
		s.setSex("男");
		insert(s);
		*/
	}
	/*
	//查询操作 标识泛型方法
	public static <T> List<T> executeQuery(String sql,RowMap<T> rowMap,Object... params){
		List<T> list=new ArrayList<>();
		Connection con=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=con.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs=pstmt.executeQuery();
			while(rs.next()){
				T t=rowMap.rowMapping(rs);
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Close(con,pstmt,rs);
		}
		return list;
	}
	
	*/
	/*
	public static <T> int insert(T t){
		List<String> columns=new ArrayList<>();
		List<String> values=new ArrayList<>();
		List<Object> params=new ArrayList<>();
		
		//获取泛型的类型，就可以获取到属性
		Class clz= t.getClass();
		Student s = new Student();
		System.out.println(s.getName());
		//获取注解
		Table ann = (Table)clz.getAnnotation(Table.class);
		System.out.println(ann.value());
		//获取属性
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			//将递增的id(Id注解的)属性排除
			if(!field.isAnnotationPresent(Id.class)){
				//如果Column注解不为空，则为字段
				if(field.isAnnotationPresent(Column.class)){
					//获取注解
					Column column= field.getAnnotation(Column.class);
					//将注解值放到集合中
					columns.add(column.value());
					values.add("?");
					//获取权限
					field.setAccessible(true);
					try {
						//获得t对象中该属性的值
						params.add(field.get(t));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		//将对应的值放到集合中并用，分隔
		String sql="insert into "+ann.value()+Arrays.toString(columns.toArray()).replaceAll("\\[", "(").replaceAll("\\]", ")")+" values"+Arrays.toString(values.toArray()).replaceAll("\\[", "(").replaceAll("\\]", ")");
		System.out.println(sql);
		System.out.println(Arrays.toString(params.toArray()) );
		return 0;
		
	}
	*/
	
}
