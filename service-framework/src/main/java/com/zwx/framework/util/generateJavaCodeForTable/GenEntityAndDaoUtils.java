package com.zwx.framework.util.generateJavaCodeForTable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenEntityAndDaoUtils {

	private String packageOutPath = "com.user.entity";// 指定实体生成所在包的路径
	private String packageDaoOutPath = "com.user.dao";// dao生成所在包的路径
	private String authorName = "zwx";// 作者名字
	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colScales;
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*

	// 数据库连接
	private static final String URL = "jdbc:oracle:thin:@172.25.14.3:1522/o3g2";
	private static final String NAME = "ace_ht_beta_tst";
	private static final String PASS = "ace_ht_beta_tstpwd";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, NAME, PASS);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return con;
	}

	/*
	 * 构造函数
	 */
	public void genEntityOracle(TableEntity tableEntity) {
		String tableName = tableEntity.getTableName().toLowerCase();
		String col_seq = tableEntity.getColSeq();
		// 创建连接
		Connection con = null;
		// 查要生成实体类的表
		String sql = "select * from " + tableName;
		Statement pStemt = null;
		try {

			con = getConnection();
			pStemt = (Statement) con.createStatement();
			ResultSet rs = pStemt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount(); // 统计列
			colnames = new String[size];
			colTypes = new String[size];
			colScales = new int[size];
			colSizes = new int[size];

			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				//int displaySize = rsmd.getColumnDisplaySize(i + 1);
				// 某列类型的精确度(类型的长度)
				colScales[i] = rsmd.getScale(i + 1);
				int precision = rsmd.getPrecision(i + 1);
				// 小数点后的位数
				int scale = rsmd.getScale(i + 1);
				System.out.println(rsmd.getColumnName(i + 1) + " 类型=" + rsmd.getColumnTypeName(i + 1) + " 精确度=" + precision + " 小数点后" + scale);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);

				if (colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("timestamp")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("blob") || colTypes[i].equalsIgnoreCase("char")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}

			String content = parse(colnames, colTypes, colSizes, tableName, col_seq);

			try {
				File directory = new File("");

				String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/" + initcap(tableName) + ".java";
				System.out.println(outputPath);
				if (new File(outputPath).exists()) {
					throw new RuntimeException("Target is exists! Please check again!");
				}
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * 
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes, String tableName, String col_seq) {
		StringBuffer sb = new StringBuffer();

		// 判断是否导入工具包
		sb.append("package " + this.packageOutPath + ";\r\n");
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		sb.append("import javax.persistence.Column;\r\n");
		sb.append("import javax.persistence.Entity;\r\n");
		sb.append("import javax.persistence.GeneratedValue;\r\n");
		sb.append("import javax.persistence.GenerationType;\r\n");
		sb.append("import javax.persistence.Id;\r\n");
		sb.append("import javax.persistence.Table;\r\n");
		sb.append("import javax.persistence.SequenceGenerator;\r\n");
		sb.append("import java.math.BigDecimal;\r\n");

		sb.append("\r\n");
		// 注释部分
		sb.append("   /**\r\n");
		sb.append("    * " + tableName + " 实体类\r\n");
		sb.append("    * " + new Date() + " " + this.authorName + "\r\n");
		sb.append("    */ \r\n");
		String str1 = "\"" + tableName + "\"";
		// 实体部分
		sb.append("@Entity\r\n");
		sb.append("@Table(name = " + str1 + ")\r\n");
		sb.append("public class " + initcap(tableName) + "{\r\n");

		processAllAttrs(sb, tableName, col_seq);// 属性
		processAllMethod(sb);// get set方法
		sb.append("}\r\n");

		return sb.toString();
	}

	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrs(StringBuffer sb, String tableName, String col_seq) {
		String pk = getPK(tableName.toUpperCase());
		for (int i = 0; i < colnames.length; i++) {

			if (pk.equals(colnames[i])) {
				String seq = "\"" + colnames[i] + "_SEQ" + "\"";

				sb.append("\t@Id\r\n");
				if ("N".equals(col_seq)) {
					sb.append("@GeneratedValue(strategy = GenerationType.AUTO)\r\n");
				} else {
					if ("W".equals(col_seq)) {
						col_seq = "S_" + tableName.substring(2).toUpperCase() + "__" + pk;
					}
					col_seq = "\"" + col_seq + "\"";
					sb.append("\t@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = " + seq + ")\r\n");
					sb.append("\t@SequenceGenerator(name = " + seq + ", sequenceName = " + col_seq + ")\r\n");
				}

			}
			String str1 = "\"" + colnames[i] + "\"";
			sb.append("\t@Column(name = " + str1 + ")\r\n");
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i], colScales[i]) + " " + initcapColnames(colnames[i].toLowerCase()) + ";\r\n");
		}

	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(colnames[i].toLowerCase()) + "(" + sqlType2JavaType(colTypes[i], colScales[i]) + " "
					+ initcapColnames(colnames[i].toLowerCase()) + "){\r\n");
			sb.append("\tthis." + initcapColnames(colnames[i].toLowerCase()) + "=" + initcapColnames(colnames[i].toLowerCase()) + ";\r\n");
			sb.append("\t}\r\n");
			sb.append("\tpublic " + sqlType2JavaType(colTypes[i], colScales[i]) + " get" + initcap(colnames[i].toLowerCase()) + "(){\r\n");
			sb.append("\t\treturn " + initcapColnames(colnames[i].toLowerCase()) + ";\r\n");
			sb.append("\t}\r\n");
		}

	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		String[] strs = str.split("_");
		StringBuffer sb = new StringBuffer();
		for (String s : strs) {
			sb.append(toUpperCaseFirstOne(s));
		}
		return sb.toString();

		// char[] ch = str.toCharArray();
		// if(ch[0] >= 'a' && ch[0] <= 'z'){
		// ch[0] = (char)(ch[0] - 32);
		// }
		//
		// return new String(ch).replace("_", "");
	}

	/**
	 * 除首字母外大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcapColnames(String str) {
		String[] strs = str.split("_");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if (i == 0) {
				sb.append(strs[i]);
			} else {
				sb.append(toUpperCaseFirstOne(strs[i]));
			}
		}

		return sb.toString();

	}

	/**
	 * 功能：获得列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType, int sqlScales) {

		if (sqlType.equalsIgnoreCase("binary_double")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("binary_float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		} else if (sqlType.equalsIgnoreCase("blob")) {
			return "byte[]";
		} else if (sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar2") || sqlType.equalsIgnoreCase("varchar2")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("timestamp") || sqlType.equalsIgnoreCase("timestamp with local time zone")
				|| sqlType.equalsIgnoreCase("timestamp with time zone")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("number")) {
			if (sqlScales > 0) {
				return "BigDecimal";
			}
			return "Long";
		}

		return "String";
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	public static String getPK(String tableName) {
		Connection con = null;
		PreparedStatement ps = null;
		StringBuffer sb = new StringBuffer();
		String PK = null;
		String str1 = "\'" + tableName + "\'";
		sb.append(" select a.constraint_name, a.column_name columnName ");
		sb.append(" from user_cons_columns a, user_constraints b ");
		sb.append(" where a.constraint_name = b.constraint_name ");
		sb.append(" and b.constraint_type = 'P' ");
		sb.append(" and a.table_name = " + str1);
		try {
			con = getConnection();
			ps = con.prepareStatement(sb.toString());
			ResultSet trs = ps.executeQuery();
			if (trs.next()) {
				PK = trs.getString("columnName");
			}
			ps.close();
			trs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PK;
	}

	public void genEntitDao(TableEntity tableEntity) {
		String tableName = tableEntity.getTableName().toLowerCase();
		String content = parseDao(tableName);
		tableName = tableName + "_Repository";
		try {
			File directory = new File("");
			String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageDaoOutPath.replace(".", "/") + "/" + initcap(tableName) + ".java";
			System.out.println(outputPath);
			if (new File(outputPath).exists()) {
				throw new RuntimeException("Target is exists! Please check again!");
			}
			FileWriter fw = new FileWriter(outputPath);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(content);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String parseDao(String tableName) {

		tableName = initcap(tableName);
		StringBuffer sb = new StringBuffer();
		genDaoImport(sb, tableName);
		genDaoClass(sb, tableName);

		return sb.toString();

	}

	/**
	 * 导入工具包
	 * 
	 * @param sb
	 * @param tableName
	 */
	private void genDaoImport(StringBuffer sb, String tableName) {
		sb.append("package " + this.packageDaoOutPath + ";\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
		sb.append("import org.springframework.data.jpa.repository.Query;\r\n");
		sb.append("import org.springframework.data.repository.query.Param;\r\n");
		sb.append("import " + this.packageOutPath + "." + tableName + ";\r\n");
	}

	private void genDaoClass(StringBuffer sb, String tableName) {

		sb.append("public interface " + tableName + "Repository extends JpaRepository<" + tableName + ", Long> {}\r\n");

	}

	public List<TableEntity> getEntityList(String tableEntitys) {

		List<TableEntity> entityList = new ArrayList<TableEntity>();
		String[] strs = tableEntitys.split("\n");
		for (int i = 0; i < strs.length; i++) {
			TableEntity entity = new TableEntity();
			String[] entitys = strs[i].split("/");
			if (entitys.length == 1 && i == 0) {

				break;
			}
			if (entitys != null && entitys.length > 0) {
				if (entitys[0] == null || "".equals(entitys[0].trim())) {
					if ((entitys[1] == null || "".equals(entitys[1].trim())) && (entitys[2] == null || "".equals(entitys[2].trim()))) {
						break;
					}

				}
				entity.setListId(entitys[0]);
				entity.setTableName(entitys[1]);
				entity.setColSeq(entitys[2]);

				entityList.add(entity);
			}
		}

		return entityList;
	}

	/**
	 * 出口
	 * TODO
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// colSeq N:没有seq W:使用默认seq

		// 单表生成
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName("T_POLICY_PRODUCT");
		tableEntity.setColSeq("N");
		GenEntityAndDaoUtils utils = new GenEntityAndDaoUtils();
		utils.genEntityOracle(tableEntity);
		utils.genEntitDao(tableEntity);
		System.out.println("create successful");
		//
		// 批量生成
		// List<TableEntity> entityList= new GenEntityAndDaoUtil().readFile();
		// if(entityList!=null && entityList.size()>0){
		// for(TableEntity tableEntity :entityList){
		//
		// new GenEntityAndDaoUtils().genEntityOracle(tableEntity);
		// new GenEntityAndDaoUtils().genEntitDao(tableEntity);
		// }
		// }

	}

}
