package com.trifork.dosages.vo;


public class DosageUnit {

	private Long dumpDosageUnitPID;
	private Long releaseNumber;
	private Integer code;
	private String textSingular;
	private String textPlural;
	
	public DosageUnit(Long dumpDosageUnitPID, Long releaseNumber, Integer code, String textSingular, String textPlural) {
		super();
		this.dumpDosageUnitPID = dumpDosageUnitPID;
		this.releaseNumber = releaseNumber;
		this.code = code;
		this.textSingular = textSingular;
		this.textPlural = textPlural;
	}

	public Long getDumpDosageUnitPID() {
		return dumpDosageUnitPID;
	}

	public Long getReleaseNumber() {
		return releaseNumber;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getTextSingular() {
		return textSingular;
	}

	public String getTextPlural() {
		return textPlural;
	}
	
}
