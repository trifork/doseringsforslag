package com.trifork.dosages.outputter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.trifork.dosages.converter.properties.ConverterProperties;
import com.trifork.dosages.vo.DosageStructure;
import com.trifork.dosages.vo.DosageStructures;
import com.trifork.dosages.vo.DosageUnit;
import com.trifork.dosages.vo.DosageUnits;
import com.trifork.dosages.vo.Drug;
import com.trifork.dosages.vo.Drugs;
import com.trifork.dosages.vo.DrugsDosageStructure;
import com.trifork.dosages.vo.DrugsDosageStructures;
import com.trifork.dosages.vo.Version;

public class SdmOutputter {

	public static File dump(
			ConverterProperties properties,
			File outputDir, 
			Version version, 
			DosageUnits dosageUnits, 
			Drugs drugs, DosageStructures dosageStructures, DrugsDosageStructures drugsDosageStructures) {
		File versionFile = dumpVersion(properties, version);
		File unitsFile = dumpUnits(properties, dosageUnits);
		File drugsFile = dumpDrugs(properties, drugs);
		File dosageStructuresFile = dumpDosageStructures(properties, dosageStructures);
		File drugsDosageStructureFile = dumpDrugsDosageStructures(properties, drugsDosageStructures);
		File zipfile = zip(properties, versionFile, unitsFile, drugsFile, dosageStructuresFile, drugsDosageStructureFile);
		return zipfile;
	}	
	
	public static File dumpVersion(ConverterProperties properties, Version version) {
		try {
			File file = new File(properties.getVersion().getOutputFileName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("{\"version\":");
			writer.write(JsonHelper.toJsonString(version));
			writer.write("}");
			writer.close();
			return file;
		}
		catch(IOException e) {
			throw new RuntimeException("Error writing version", e);
		}
	}

	public static File dumpUnits(ConverterProperties properties, DosageUnits dosageUnits) {
		try {
			File file = new File(properties.getUnits().getOutputFileName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("{\"dosageUnits\":[");
			writer.newLine();
			int sz = dosageUnits.getAll().size();
			ArrayList<DosageUnit> d = new ArrayList<DosageUnit>(dosageUnits.getAll());
			for(int i=0; i<sz; i++) {
				DosageUnit u = d.get(i);
				writer.write("   ");
				writer.write(JsonHelper.toJsonString(u));
				if(i<sz-1)
					writer.write(",");
				writer.newLine();			
			}
			writer.write("]}");
			writer.close();
			return file;
		}
		catch(IOException e) {
			throw new RuntimeException("Error writing units", e);
		}
	}

	public static File dumpDrugs(ConverterProperties properties, Drugs drugs) {
		try {
			File file = new File(properties.getDrugs().getOutputFileName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("{\"drugs\":[");
			boolean drugFirstRow = true;
			for(Drug drug: drugs.getAll()) {
				if(drug.getDosageUnitCode()!=null) {
					// write the drug
					if(drugFirstRow) {
						drugFirstRow = false;
						writer.newLine();
					}
					else {
						writer.write(",");
						writer.newLine();
					}
					writer.write("   ");
					writer.write(JsonHelper.toJsonString(drug));
				}
			}
			writer.newLine();
			writer.write("]}");
			writer.close();
			return file;
		}
		catch(IOException e) {
			throw new RuntimeException("Error writing drugs", e);
		}
	}

	
	public static File dumpDosageStructures(ConverterProperties properties, DosageStructures dosageStructures) {
		try {
			File file = new File(properties.getDosageStructures().getOutputFileName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			boolean firstRow = true; 
			writer.write("{\"dosageStructures\":[");
			for(DosageStructure dosageStructure: dosageStructures.getAll()) {
				if(firstRow) {
					firstRow = false;
					writer.newLine();
				}
				else {
					writer.write(",");
					writer.newLine();
				}
				writer.write("   ");
				dosageStructure.setDumpDosageStructurePID(null);
				writer.write(JsonHelper.toJsonString(dosageStructure));
			}
			writer.newLine();
			writer.write("]}");
			writer.close();		
			return file;
		}
		catch(IOException e) {
			throw new RuntimeException("Error writing dosage structures", e);
		}		
	}
	
	public static File dumpDrugsDosageStructures(ConverterProperties properties, DrugsDosageStructures drugsDosageStructures) {
		try {
			File file = new File(properties.getDrugsDosageStructures().getOutputFileName());
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("{\"drugsDosageStructures\":[");
			boolean relFirstRow = true;
			for(DrugsDosageStructure drugsDosageStructure: drugsDosageStructures.getAll()) {
				if(relFirstRow) {
					relFirstRow = false;
					writer.newLine();
				}
				else {
					writer.write(",");
					writer.newLine();
				}
				writer.write("   ");
				writer.write(JsonHelper.toJsonString(drugsDosageStructure));			
			}
			writer.newLine();
			writer.write("]}");
			writer.close();
			return file;
		}
		catch(IOException e) {
			throw new RuntimeException("Error writing drugs - dosage structures relations", e);
		}		
	}
	
	private static File zip(ConverterProperties properties, File... files) {
		File zf = new File(properties.getZipFile().getOutputFileName());
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(zf));
			for (File f: files) {
				zip(zos, f);
				System.out.println("Adding "+f.getAbsolutePath()+" to zip file");
			}
			return zf;
		} 
		catch (IOException e) {
			throw new RuntimeException("Error creating zipfile", e);
		}
		finally {
			try {
				if(zos!=null)
					zos.close();
			} 
			catch (IOException e1) {
				// Ignore
			}
		}
	}

	private static void zip(ZipOutputStream zos, File f) throws IOException {
		FileInputStream is = null;
		try {
			is = new FileInputStream(f);
			zos.putNextEntry(new ZipEntry(f.getName()));
			byte[] buffer = new byte[1024];
			int len; 
		    while ((len = is.read(buffer)) > 0)  
		    	zos.write(buffer, 0, len); 
		    zos.closeEntry();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e1) {
				// Ignore
			}
		}
	}
	    
}
