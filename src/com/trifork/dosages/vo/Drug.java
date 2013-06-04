package com.trifork.dosages.vo;

import java.util.ArrayList;

public class Drug {

	private Long dumpDrugPID;
	private long releaseNumber;
	private Long drugId;
	private Integer dosageUnitCode;
	private ArrayList<Long> dosageStructureCodes = new ArrayList<Long>();
	private String drugName;
	
	public Drug(Long dumpDrugPID, long releaseNumber, Long drugId,
			Integer dosageUnitCode, ArrayList<Long> dosageStructureCodes,
			String drugName) {
		this.dumpDrugPID = dumpDrugPID;
		this.releaseNumber = releaseNumber;
		this.drugId = drugId;
		this.dosageUnitCode = dosageUnitCode;
		this.dosageStructureCodes = dosageStructureCodes;
		this.drugName = drugName;
	}

	public Long getDumpDrugPID() {
		return dumpDrugPID;
	}

	public long getReleaseNumber() {
		return releaseNumber;
	}

	public Long getDrugId() {
		return drugId;
	}

	public Integer getDosageUnitCode() {
		return dosageUnitCode;
	}

	public ArrayList<Long> getDosageStructureCodes() {
		return dosageStructureCodes;
	}

	public String getDrugName() {
		return drugName;
	}
	
}
