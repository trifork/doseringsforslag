package com.trifork.dosages.converter.properties;

public class DrugsProperties {

	private String inputFileName; 	
	private String outputFileName;
	private String encoding; 
	private String separatorRegEx;
	private int columnCount;
	private int columnForDrugId;
	private int columnForDrugName;
	private int columnForUnitSingular;  
	private int columnForUnitPlural;
	
	public String getInputFileName() {
		return inputFileName;
	}
	
	public String getOutputFileName() {
		return outputFileName;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public String getSeparatorRegEx() {
		return separatorRegEx;
	}
	
	public int getColumnCount() {
		return columnCount;
	}
	
	public int getColumnForDrugId() {
		return columnForDrugId;
	}

	public int getColumnForDrugName() {
		return columnForDrugName;
	}
	
	public int getColumnForUnitSingular() {
		return columnForUnitSingular;
	}
	
	public int getColumnForUnitPlural() {
		return columnForUnitPlural;
	}		
	
}
