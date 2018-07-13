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
 * @Description: TODO(�������ݿ�������ɶ�Ӧʵ���࣬ע�⣺ͬһ·���¶��Ѵ�����ͬ��������ᱻ�滻) 
 * @author Uncle liu
 * @date 2018��6��11�� ����2:11:18 
 *  
 */
public class GeneratingEntityClass {
    private String packageOutPath;	//ָ��ʵ���������ڰ���·��  
    private String authorName;		//��������  
    private String tablename;		//����  
    private String explain;			//��˵��
    private String propertiesPath;	//���ݿ�����·��
    private String[] colnames;		// ��������  
    private String[] colTypes; 		//������������  
    private int[] colSizes; 		//������С����  
    private boolean f_util = false; // �Ƿ���Ҫ�����java.util.*  
    private boolean f_sql = false; 	// �Ƿ���Ҫ�����java.sql.*  
      
    //���ݿ�����  
    private String url ;  //����
    private String user;  //�˻���
    private String pwd ;  //����
	private String driver ;  //����
	
	/**
	 * 
	 * @Description: TODO(�޲ι��췽��)
	 */
	public GeneratingEntityClass() {
		
	}
    
    /**
     * 
     * @Description: TODO(�вι��캯��/��������һ����ʵ����) 
     * @param packageOutPath ָ��ʵ���������ڰ���·�� (com.ly.calculator.entity)
     * @param tablename ����  
     * @param authorName ��������
     * @param explain ��˵��
     * @param propertiesPath ���ݿ��ļ�����·��
     * �������ݿ�������ɶ�Ӧʵ���࣬ע�⣺ͬһ·���¶��Ѵ�����ͬ��������ᱻ�滻
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
     * @Description: TODO(ƥ���޲ι��췽��һ��ʹ�ã����ɶ�Ӧ���ݿ����б�ʵ����) 
     * @param packageOutPath ָ��ʵ���������ڰ���·�� (com.ly.calculator.entity)
     * @param authorName ��������
     * @param explain ��˵��
     * @param propertiesPath ���ݿ��ļ�����·�� 
     */
    public void ToPerformAll(String packageOutPath,String authorName,String propertiesPath) {
    	this.packageOutPath=packageOutPath;
    	this.authorName=authorName;
    	this.propertiesPath=propertiesPath;
    	//��ȡ�����ļ�
    	Path();
    	//��������
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
//	    	ִ�����!
	    	ResultSet rs = stmt.executeQuery("show tables; ");
//	    	ʹ��ResultSet����!
	    	while (rs.next()) {
	    		this.tablename=rs.getString(1);//����
	            //����ʵ����
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
     * @Description: TODO(��ȡ�����ļ�)  void
     */
    public void Path() {
		try {
			InputStream is = GeneratingEntityClass.class.getResourceAsStream(propertiesPath);
			//ʵ����Properties����
			Properties pt = new Properties();
			//�����ļ�����
			pt.load(is);
			//ͨ�������ȡ����ֵ
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
     * @Description: TODO(����ʵ����)  void
     */
    private void generate() {
    	//��ȡ*.properties�ļ�
    	Path();
		//-----------------------------------------------
		
        //��������  
        Connection con;  
        //��Ҫ����ʵ����ı�  
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
            int size = rsmd.getColumnCount();   //ͳ����  
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
     * ���ܣ�����ʵ����������� 
     * @param colnames 
     * @param colTypes 
     * @param colSizes 
     * @return 
     */  
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {  
        StringBuffer sb = new StringBuffer();  
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        sb.append("package " + this.packageOutPath + ";\r\n");  
        sb.append("\r\n"); 
        //�ж��Ƿ��빤�߰�  
        if(f_util){  
            sb.append("import java.util.Date;\r\n");  
        }  
        if(f_sql){  
            sb.append("import java.sql.*;\r\n");  
        }  
        sb.append("\r\n");  
        //ע�Ͳ���  
        sb.append("/**\r\n");  
        sb.append(" * @ClassName:"+tablename+" \r\n");  
        sb.append(" * @Description: TODO(��˵����"+explain+" )\r\n"); 
        sb.append(" * @author "+this.authorName+" \r\n");  
        sb.append(" * @date "+df.format(new Date())+"\r\n");  
        sb.append(" */ ");  
        //ʵ�岿��  
        sb.append("\r\n\r\npublic class " + initcap(tablename) + "{\r\n");  
        processAllAttrs(sb);//����  
        processNoarguments(sb);//�޲ι��췽��
        processConstructor(sb);//�вι��췽��
        processAllMethod(sb);//get set����  
        processToString(sb);//toString
        sb.append("}\r\n");  
        return sb.toString();  
    }  
      
    /**
     *   
     * @Title: processAllAttrs 
     * @Description: TODO(������������ ) 
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
     * @Description: TODO(����toString) 
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
     * @Description: TODO(��������set/get���� ) 
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
     * @Description: TODO(�����޲ι��췽��) 
     * @param sb void
     */
    private void processNoarguments(StringBuffer sb) {
    	sb.append("\tpublic " + initcap(tablename) + "(){\r\n");  
    	sb.append("\t}\r\n"); 
    }
    /**
     * 
     * @Title: processConstructor 
     * @Description: TODO(�����вι��췽��) 
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
     * @Description: TODO(�������ַ���������ĸ�ĳɴ�д) 
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
     * @Description: TODO(����е���������) 
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
	new GeneratingEntityClass().ToPerformAll("com.zsq.easyui.entity", "demon", "config.properties");//����λ��--�û���������--���ݿ������ļ����������У�
	//GeneratingEntityClass i = new GeneratingEntityClass("com.zsq.easyui.entity", "t_user", "demon", "�û���Ϣ��", "config.properties");//����λ��--����--������-- ������--���ݿ������ļ������ɵ�һ��
//	System.out.println(i);
//	new GeneratingEntityClass("com.zsq.easyui.entity", "t_roles", "demon", "��ɫ��Ϣ��", "config.properties");//����λ��--����--������-- ������--���ݿ������ļ������ɵ�һ��
//	new GeneratingEntityClass("com.zsq.easyui.entity", "t_function", "demon", "������Ϣ��", "config.properties");//����λ��--����--������-- ������--���ݿ������ļ������ɵ�һ��
//	new GeneratingEntityClass("com.zsq.easyui.entity", "user_roles", "demon", "�û���ɫ��", "config.properties");//����λ��--����--������-- ������--���ݿ������ļ������ɵ�һ��
//	new GeneratingEntityClass("com.zsq.easyui.entity", "func_roles", "demon", "��ɫ���ܱ�", "config.properties");//����λ��--����--������-- ������--���ݿ������ļ������ɵ�һ��
}
  
}
