package com.zwx.framework.util.generateJavaCodeForTable;

public class TableEntity {

	private String ListId ;
	
	private String  tableName ;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColSeq() {
		return colSeq;
	}

	public void setColSeq(String colSeq) {
		this.colSeq = colSeq;
	}

	private String colSeq;

	public String getListId() {
		return ListId;
	}

	public void setListId(String listId) {
		ListId = listId;
	}


	
	
}
