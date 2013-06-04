package com.trifork.dosages.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.trifork.dosages.converter.properties.ConverterProperties;
import com.trifork.dosages.outputter.SdmOutputter;
import com.trifork.dosages.vo.DosageStructures;
import com.trifork.dosages.vo.DosageUnits;
import com.trifork.dosages.vo.Drugs;
import com.trifork.dosages.vo.DrugsDosageStructures;
import com.trifork.dosages.vo.Version;

public class Converter {
	
	public static final int releaseNumber = 7;
	public static final String releaseDate = "2013-06-03";
	public static final String lmsDate = "2013-05-27";
	public static final String daDate = "2000-01-01";

	public static final String inputDirName = "input";
	public static final String outputDirName = "output";
	public static final String unitTextFileName = "doseringsenheder_03JUN2013.txt";
	public static final String drugsFileName = "doseringsenheder_03JUN2013.txt";
	
	public static void main(String[] args) {
		new Converter().convert();
	}
	
	public void convert() {
		ConverterProperties properties = readProperties();
		
		Version version = new Version(
			null, 
			properties.getVersion().getReleaseNumber(), 
			properties.getVersion().getReleaseDate(), 
			properties.getVersion().getLmsDate(), 
			properties.getVersion().getDaDate());
		
		DosageUnits dosageUnits = DosageUnits.readDosageUnits(version, properties);
		
		Drugs drugs = Drugs.readDrugs(version, dosageUnits, properties);
		
		File zipfile = SdmOutputter.dump(properties, new File(properties.getZipFile().getOutputFileName()), 
			version, 
			dosageUnits, 
			drugs, 
			new DosageStructures(), 
			new DrugsDosageStructures());
		System.out.println("Wrote "+zipfile.getAbsolutePath());
	}
	
	public ConverterProperties readProperties() {
		try {
			GsonBuilder b = new GsonBuilder();
			b.setDateFormat("yyyy-MM-dd");
			return b.create().fromJson(new BufferedReader(new FileReader("Properties.json")), ConverterProperties.class);
		} 
		catch (JsonParseException e) {
			throw new RuntimeException(e);
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
}
