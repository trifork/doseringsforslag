package com.trifork.dosages.vo;


public class DosageStructure {

	private Long dumpDosageStructurePID;
	private Long releaseNumber;
	private Long code;
	private String hashKey;
	
	private String type;
	private String simpleString;
	private String supplementaryText;
	private String xml;
	private String shortTranslation;
	private String longTranslation;
	
	public DosageStructure(Long dumpDosageStructurePID, Long releaseNumber,
			Long code, String hashKey, String type, String simpleString,
			String supplementaryText, String xml, String shortTranslation,
			String longTranslation) {
		this.dumpDosageStructurePID = dumpDosageStructurePID;
		this.releaseNumber = releaseNumber;
		this.code = code;
		this.hashKey = hashKey;
		this.type = type;
		this.simpleString = simpleString;
		this.supplementaryText = supplementaryText;
		this.xml = xml;
		this.shortTranslation = shortTranslation;
		this.longTranslation = longTranslation;
	}

	public Long getDumpDosageStructurePID() {
		return dumpDosageStructurePID;
	}

	public void setDumpDosageStructurePID(Long dumpDosageStructurePID) {
		this.dumpDosageStructurePID = dumpDosageStructurePID;
	}

	public Long getReleaseNumber() {
		return releaseNumber;
	}

	public void setReleaseNumber(Long releaseNumber) {
		this.releaseNumber = releaseNumber;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSimpleString() {
		return simpleString;
	}

	public void setSimpleString(String simpleString) {
		this.simpleString = simpleString;
	}

	public String getSupplementaryText() {
		return supplementaryText;
	}

	public void setSupplementaryText(String supplementaryText) {
		this.supplementaryText = supplementaryText;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getShortTranslation() {
		return shortTranslation;
	}

	public void setShortTranslation(String shortTranslation) {
		this.shortTranslation = shortTranslation;
	}

	public String getLongTranslation() {
		return longTranslation;
	}

	public void setLongTranslation(String longTranslation) {
		this.longTranslation = longTranslation;
	}
	
}
