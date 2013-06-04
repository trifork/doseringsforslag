package com.trifork.dosages.vo;


public class DrugsDosageStructure {

	private Long dumpDrugsDosageStructuresPID;
	private Long releaseNumber;
	private Long drugId;
	private Long dosageStructureCode;
	
	public DrugsDosageStructure(Long dumpDrugsDosageStructuresPID,
			Long releaseNumber, Long drugId, Long dosageStructureCode) {
		this.dumpDrugsDosageStructuresPID = dumpDrugsDosageStructuresPID;
		this.releaseNumber = releaseNumber;
		this.drugId = drugId;
		this.dosageStructureCode = dosageStructureCode;
	}

	public Long getDumpDrugsDosageStructuresPID() {
		return dumpDrugsDosageStructuresPID;
	}

	public Long getReleaseNumber() {
		return releaseNumber;
	}

	public Long getDrugId() {
		return drugId;
	}

	public Long getDosageStructureCode() {
		return dosageStructureCode;
	}
	
}
