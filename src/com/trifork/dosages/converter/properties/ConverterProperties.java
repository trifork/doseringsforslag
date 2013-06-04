package com.trifork.dosages.converter.properties;

public class ConverterProperties {

	private VersionProperties version;

	private UnitsProperties units;
	
	private DrugsProperties drugs;
	
	private DosageStructuresProperties dosageStructures; 
	
	private DrugsDosageStructuresProperties drugsDosageStructures;
	
	private ZipFileProperties zipFile;

	public VersionProperties getVersion() {
		return version;
	}

	public UnitsProperties getUnits() {
		return units;
	}
	
	public DrugsProperties getDrugs() {
		return drugs;
	}

	public DosageStructuresProperties getDosageStructures() {
		return dosageStructures;
	}

	public DrugsDosageStructuresProperties getDrugsDosageStructures() {
		return drugsDosageStructures;
	}

	public ZipFileProperties getZipFile() {
		return zipFile;
	}

}
