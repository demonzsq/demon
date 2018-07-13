package com.zsq.easyui.util;

import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;




/** 
 * @ClassName: GeneratingEntityClass 
 * @Description: TODO(根据数据库表名生成对应实体类，注意：同一路径下对已存在相同类名的类会被替换) 
 * @author Uncle liu
 * @date 2018年6月11日 下午2:11:18 
 *  
 */
public class GeneratingEntityClass {
    private String packageOutPath;	//指定实体生成所在包的路径  
    private String authorName;		//作者名字  
    private String tablename;		//表名  
    private String explain;			//类说明
    private String propertiesPath;	//数据库连接路径
    private String[] colnames;		// 列名数组  
    private String[] colTypes; 		//列名类型数组  
    private int[] colSizes; 		//列名大小数组  
    private boolean f_util = false; // 是否需要导入包java.util.*  
    private boolean f_sql = false; 	// 是否需要导入包java.sql.*  
      
    //数据库连接  
    private String url ;  //链接
    private String user;  //账户名
    private String pwd ;  //密码
	private String driver ;  //驱动
	
	/**
	 * 
	 * @Description: TODO(无参构造方法)
	 */
	public GeneratingEntityClass() {
		
	}
    
    /**
     * 
     * @Description: TODO(有参构造函数/用于生成一个表实体类) 
     * @param packageOutPath 指定实体生成所在包的路径 (com.ly.calculator.entity)
     * @param tablename 表名  
     * @param authorName 作者名字
     * @param explain 类说明
     * @param propertiesPath 数据库文件连接路径
     * 根据数据库表名生成对应实体类，注意：同一路径下对已存在相同类名的类会被替换
     */
    public GeneratingEntityClass(String packageOutPath,String tablename,String authorName,String explain,String propertiesPath){  
    	this.packageOutPath=packageOutPath;
    	this.tablename=tablename;
    	this.authorName=authorName;
    	this.explain=explain;
    	this.propertiesPath=propertiesPath;
    }  
 
    /**
     * 
     * @Title: ToPerformAll 
     * @Description: TODO(匹配无参构造方法一起使用，生成对应数据库所有表实体类) 
     * @param packageOutPath 指定实体生成所在包的路径 (com.ly.calculator.entity)
     * @param authorName 作者名字
     * @param explain 类说明
     * @param propertiesPath 数据库文件连接路径 
     */
    public void ToPerformAll(String packageOutPath,String authorName,String propertiesPath) {
    	this.packageOutPath=packageOutPath;
    	this.authorName=authorName;
    	this.propertiesPath=propertiesPath;
    	//读取配置文件
    	Path();
    	//建立连接
		Connection conn = null;
        try {  
            Class.forName(driver);  
            conn = DriverManager.getConnection(url,user,pwd); 
        } catch (Exception e1) {  
            // TODO Auto-generated catch block  
            e1.printStackTrace();  
        }  
    	Statement stmt = null;
		try {
			stmt = conn.createStatement();
//	    	执行语句!
	    	ResultSet rs = stmt.executeQuery("show tables; ");
//	    	使用ResultSet对象!
	    	while (rs.next()) {
	    		this.tablename=rs.getString(1);//表名
	            //生成实体类
	           generate();
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 
     * @Title: Path 
     * @Description: TODO(读取配置文件)  void
     */
    public void Path() {
		try {
			InputStream is = GeneratingEntityClass.class.getResourceAsStream(propertiesPath);
			//实例化Properties对象
			Properties pt = new Properties();
			//加载文件内容
			pt.load(is);
			//通话对象获取属性值
			driver = pt.getProperty("driver");
			url = pt.getProperty("url");
			user = pt.getProperty("user");
			pwd = pt.getProperty("pwd");
			
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * 
     * @Title: generate 
     * @Description: TODO(生成实体类)  void
     */
    private void generate() {
    	//读取*.properties文件
    	Path();
		//-----------------------------------------------
		
        //创建连接  
        Connection con;  
        //查要生成实体类的表  
        String sql = "select * from " + tablename;  
        PreparedStatement pStemt = null;  
        try {  
            try {  
                Class.forName(driver);  
            } catch (ClassNotFoundException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
            con = DriverManager.getConnection(url,user,pwd);  
            pStemt = con.prepareStatement(sql);  
            ResultSetMetaData rsmd = pStemt.getMetaData();  
            int size = rsmd.getColumnCount();   //统计列  
            colnames = new String[size];  
            colTypes = new String[size];  
            colSizes = new int[size];  
            for (int i = 0; i < size; i++) {  
                colnames[i] = rsmd.getColumnName(i + 1);  
                colTypes[i] = rsmd.getColumnTypeName(i + 1);  
                  
                if(colTypes[i].equalsIgnoreCase("datetime")){  
                    f_util = true;  
                }  
                if(colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")){  
                    f_sql = true;  
                }  
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);  
            }  
            String content = parse(colnames,colTypes,colSizes);  
            try {  
                File directory = new File("");  
                //String path=this.getClass().getResource("").getPath();  
                String outputPath = directory.getAbsolutePath()+ "\\src\\"+this.packageOutPath.replace(".", "\\")+"\\"+initcap(tablename) + ".java";  
                FileWriter fw = new FileWriter(outputPath);  
                PrintWriter pw = new PrintWriter(fw);  
                pw.println(content);  
                pw.flush();  
                pw.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }
    }

    /** 
     * 功能：生成实体类主体代码 
     * @param colnames 
     * @param colTypes 
     * @param colSizes 
     * @return 
     */  
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {  
        StringBuffer sb = new StringBuffer();  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        sb.append("package " + this.packageOutPath + ";\r\n");  
        sb.append("\r\n"); 
        //判断是否导入工具包  
        if(f_util){  
            sb.append("import java.util.Date;\r\n");  
        }  
        if(f_sql){  
            sb.append("import java.sql.*;\r\n");  
        }  
        sb.append("\r\n");  
        //注释部分  
        sb.append("/**\r\n");  
        sb.append(" * @ClassName:"+tablename+" \r\n");  
        sb.append(" * @Description: TODO(类说明："+explain+" )\r\n"); 
        sb.append(" * @author "+this.authorName+" \r\n");  
        sb.append(" * @date "+df.format(new Date())+"\r\n");  
        sb.append(" */ ");  
        //实体部分  
        sb.append("\r\n\r\npublic class " + initcap(tablename) + "{\r\n");  
        processAllAttrs(sb);//属性  
        processNoarguments(sb);//无参构造方法
        processConstructor(sb);//有参构造方法
        processAllMethod(sb);//get set方法  
        processToString(sb);//toString
        sb.append("}\r\n");  
        return sb.toString();  
    }  
      
    /**
     *   
     * @Title: processAllAttrs 
     * @Description: TODO(生成所有属性 ) 
     * @param sb void
     */
    private void processAllAttrs(StringBuffer sb) {  
          
        for (int i = 0; i < colnames.length; i++) {  
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");  
        }  
    }  
    /**
     * 
     * @Title: processToString 
     * @Description: TODO(生成toString) 
     * @param sb void
     */
    private void processToString(StringBuffer sb) {
    	 sb.append("\tpublic String toString() { \r\n"); 
    	for (int i = 0; i < colnames.length; i++) {
    		if(i%3==0 && i!=0) {
    			sb.append("\"+\r\n\t\t\"");
    		}
    		if(i==0) {
    			sb.append("\t	return \""+initcap(tablename)+"["+colnames[i]+"=\" + "+colnames[i]+" + \",");
    		}else if(i==colnames.length-1) {
    			sb.append(colnames[i]+"=\" + "+colnames[i]+" + \"]\";");
    		}else {
    			sb.append(colnames[i]+"=\" + "+colnames[i]+" + \",");
    		}
    	}
    	 sb.append("\r\n\t}\r\n");
    }
  
    /**
     *  
     * @Title: processAllMethod 
     * @Description: TODO(生成所有set/get方法 ) 
     * @param sb void
     */
    private void processAllMethod(StringBuffer sb) {  
          
        for (int i = 0; i < colnames.length; i++) {  
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " +   
                    colnames[i] + "){\r\n");  
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");  
            sb.append("\t}\r\n");  
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");  
            sb.append("\t\treturn " + colnames[i] + ";\r\n");  
            sb.append("\t}\r\n");  
        }  
          
    }  
    
    /**
     * 
     * @Title: processNoarguments 
     * @Description: TODO(生成无参构造方法) 
     * @param sb void
     */
    private void processNoarguments(StringBuffer sb) {
    	sb.append("\tpublic " + initcap(tablename) + "(){\r\n");  
    	sb.append("\t}\r\n"); 
    }
    /**
     * 
     * @Title: processConstructor 
     * @Description: TODO(生成有参构造方法) 
     * @param sb void
     */
    private void processConstructor(StringBuffer sb) {
    	sb.append("\tpublic " + initcap(tablename) + "("); 
    	for (int i = 0; i < colnames.length; i++) {
    		if(i==colnames.length-1) {
    			sb.append(sqlType2JavaType(colTypes[i])+" "+colnames[i]+"){\r\n");
    		}else {
    			sb.append(sqlType2JavaType(colTypes[i])+" "+colnames[i]+",");
    		}
    	}
    	for (int i = 0; i < colnames.length; i++) {
    		 sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
    	}
    	sb.append("\t}\r\n"); 
    }
      
    /**
     *  
     * @Title: initcap 
     * @Description: TODO(将输入字符串的首字母改成大写) 
     * @param str
     * @return String
     */
    private String initcap(String str) {  
        char[] ch = str.toCharArray();  
        if(ch[0] >= 'a' && ch[0] <= 'z'){  
            ch[0] = (char)(ch[0] - 32);  
        }  
        return new String(ch);  
    }  
  
    /**
     * 
     * @Title: sqlType2JavaType 
     * @Description: TODO(获得列的数据类型) 
     * @param sqlType
     * @return String
     */
    private String sqlType2JavaType(String sqlType) {  
        if(sqlType.equalsIgnoreCase("bit")){  
            return "boolean";  
        }else if(sqlType.equalsIgnoreCase("tinyint")){  
            return "byte";  
        }else if(sqlType.equalsIgnoreCase("smallint")){  
            return "short";  
        }else if(sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("Integer")){  
            return "Integer";  
        }else if(sqlType.equalsIgnoreCase("bigint")){  
            return "long";  
        }else if(sqlType.equalsIgnoreCase("float")){  
            return "float";  
        }else if(sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")   
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")   
                || sqlType.equalsIgnoreCase("smallmoney")){  
            return "double";  
        }else{  
            return "String";  
        }  
    }  
    
public static void main(String[] args) {
	new GeneratingEntityClass().ToPerformAll("com.zsq.easyui.entity", "demon", "config.properties");//生成位置--用户名（随便打）--数据库连接文件（生成所有）
	//GeneratingEntityClass i = new GeneratingEntityClass("com.zsq.easyui.entity", "t_user", "demon", "用户信息表", "config.properties");//生成位置--表名--创建名-- 类描述--数据库连接文件（生成单一表）
//	System.out.println(i);
//	new GeneratingEntityClass("com.zsq.easyui.entity", "t_roles", "demon", "角色信息表", "config.properties");//生成位置--表名--创建名-- 类描述--数据库连接文件（生成单一表）
//	new GeneratingEntityClass("com.zsq.easyui.entity", "t_function", "demon", "功能信息表", "config.properties");//生成位置--表名--创建名-- 类描述--数据库连接文件（生成单一表）
//	new GeneratingEntityClass("com.zsq.easyui.entity", "user_roles", "demon", "用户角色表", "config.properties");//生成位置--表名--创建名-- 类描述--数据库连接文件（生成单一表）
//	new GeneratingEntityClass("com.zsq.easyui.entity", "func_roles", "demon", "角色功能表", "config.properties");//生成位置--表名--创建名-- 类描述--数据库连接文件（生成单一表）
}
  
}
