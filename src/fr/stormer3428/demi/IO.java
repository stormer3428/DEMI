package fr.stormer3428.demi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class IO {

	public static List<IO> all = new ArrayList<>();
	public static List<String> defaultHeaders = new ArrayList<>();
	private List<Key> defaultKeys = new ArrayList<>();

	private Semaphore fileSemaphore = new Semaphore(1);

	static {
		defaultHeaders.add("#");
		defaultHeaders.add("//");
	}

	private File file;
	private String fileName;
	private List<String> commentLinesHeaders = new ArrayList<>();
	private boolean printStackTrace;

	public IO(File file, List<Key> defaultKeys, boolean printStackTrace, List<String> commentLinesHeaders) {
		if(file == null) {
			DemiConsole.error("Attempted to create an IO interface for a null file!");
			DemiConsole.warning("Cancelled messages will be thrown whenever this IO is called");
			return;
		}
		this.commentLinesHeaders = commentLinesHeaders;
		this.printStackTrace = printStackTrace;
		this.file = file;
		this.fileName = file.getName();
		this.defaultKeys = defaultKeys;
		DemiConsole.ok("IO interface for file " + getFileName() + " successfully created!");
		all.add(this);
		DemiConsole.action("Checking File...");
		if(fileCheck(true)) DemiConsole.ok("File check successful");
		else DemiConsole.error("Failed to pass file check");
	}

	public IO(File file, List<Key> defaultKeys, boolean printStackTrace) {
		this(file, defaultKeys, printStackTrace, defaultHeaders);
	}

	public final boolean fileCheck() {
		return fileCheck(false);
	}

	public final boolean fileCheck(boolean checkKeys) {
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to check integrity of null file, returning false");
			return false;
		}
		if(!(getFile().exists() && getFile().isFile())) {
			DemiConsole.warning("Missing file " + getFileName() +" !");
			DemiConsole.action("Attenpting to create file " + getFileName());
			try {
				getFile().getAbsoluteFile().getParentFile().mkdir();
				getFile().createNewFile();
			} catch (IOException e) {
				DemiConsole.error("Failed to create file " + getFileName());
				handleTrace(e);
				return false;
			}
			DemiConsole.ok("Created file " + getFileName() + " successfully !");
		}
		if(checkKeys) {
			List<String> keys = getKeys();
			for(Key defaultKey : this.defaultKeys) {
				if(keys.contains(defaultKey.name())) continue;
				DemiConsole.info("File " + getFileName() + " is missing the key " + defaultKey.name());
				DemiConsole.action("Adding the missing key at the end of file");
				addParameter(defaultKey);
			}
		}
		return true;
	}

	public final List<String> getKeys(){
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to retrieve keys of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of keys for file " + getFileName());
			DemiConsole.warning("retuning an empty list");
			return new ArrayList<>();
		}

		try {
			fileSemaphore.acquire();
		} catch (Exception e) {
			handleTrace(e);
		}
		List<String> lines;
		try {
			lines = Files.readAllLines(getFile().toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to retrieve keySet from file ("+getFile().getName()+"), returning empty list");
			handleTrace(e);
			fileSemaphore.release();
			return new ArrayList<>();
		}
		List<String> keys = new ArrayList<>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.contains(":")) continue;
			keys.add(line.split(":",2)[0]);
		}
		fileSemaphore.release();
		return keys;
	}

	private boolean isCommentLine(String line) {
		for(String header : this.commentLinesHeaders) if(line.startsWith(header)) return true;
		return false;
	}

	public final List<String> getValues(){
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to retrieve values of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of keys for file " + getFileName());
			DemiConsole.warning("retuning an empty list");
			return new ArrayList<>();
		}

		try {
			fileSemaphore.acquire();
		} catch (Exception e) {
			handleTrace(e);
		}
		List<String> lines;
		try {
			lines = Files.readAllLines(getFile().toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to retrieve all values from file ("+getFile().getName()+"), returning null");
			handleTrace(e);
			fileSemaphore.release();
			return null;
		}
		List<String> keys = new ArrayList<>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.contains(":")) continue;
			keys.add(line.split(":",2)[1].replace("\"", ""));
		}
		fileSemaphore.release();
		return keys;
	}

	public final List<String> getList(String key){
		String arrayString = get(key);
		if(arrayString.isEmpty()) {
			DemiConsole.warning("Unable to retrieve list " + key + " from file " + getFileName());
			DemiConsole.info("Returned an empty list, if it is intentional, ignore this message");
			return new ArrayList<>();
		}

		arrayString = arrayString.replace("[", "");
		arrayString = arrayString.replace("]", "");

		List<String> array = new ArrayList<>();

		for(String string : arrayString.split(",")) if(!string.isBlank() && !string.isEmpty()) array.add(string);

		return array;
	}

	public final String get(String key){
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to retrieve value " + key + " of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of value for file " + getFileName());
			DemiConsole.warning("retuning an null");
			return null;
		}

		try {
			fileSemaphore.acquire();
		} catch (Exception e) {
			handleTrace(e);
		}
		List<String> lines;
		try {
			lines = Files.readAllLines(getFile().toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to retrieve parameter ("+key+") from file ("+getFile().getName()+"), returning null");
			handleTrace(e);
			fileSemaphore.release();
			return null;
		}
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.startsWith(key + ":")) continue;
			String s = line.substring(key.length() + 1);
			s = s.replace("\"", "");
			fileSemaphore.release();
			return s;

		}
		fileSemaphore.release();
		return "";
	}

	public final HashMap<String, String> getAll(){
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to retrieve entirety of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of entirety of file " + getFileName());
			DemiConsole.warning("retuning an empty hashmap");
			return new HashMap<>();
		}

		try {
			fileSemaphore.acquire();
		} catch (Exception e) {
			handleTrace(e);
		}
		List<String> lines;
		try {
			lines = Files.readAllLines(getFile().toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to retrieve hashMap of file ("+getFile().getName()+"), returning null");
			handleTrace(e);
			fileSemaphore.release();
			return null;
		}
		HashMap<String, String> keys = new HashMap<>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.contains(":")) continue;
			keys.put(line.split(":",2)[0], line.split(":",2)[1].replace("\"", ""));

		}
		fileSemaphore.release();
		return keys;
	}

	public final HashMap<String, String> getSortedAll(){
		HashMap<String, String> unsortedAll = getAll();
		HashMap<String, String> sortedAll = new HashMap<>();
		Set<String> keySet = unsortedAll.keySet();
		List<String> sortedKeySet = new ArrayList<>();
		sortedKeySet.addAll(keySet);
		Collections.sort(sortedKeySet);
		for(String sortedKey : sortedKeySet) sortedAll.put(sortedKey, unsortedAll.get(sortedKey));
		return sortedAll;
	}

	public final ArrayList<String> getAllRaw(){
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to retrieve raw version of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of raw file " + getFileName());
			DemiConsole.warning("retuning an empty list");
			return new ArrayList<>();
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(getFile().toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attemting to raw version of file ("+getFile().getName()+"), returning null");
			handleTrace(e);
			return null;
		}
		ArrayList<String> keys = new ArrayList<>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			keys.add(line);
		}
		return keys;
	}

	public final boolean setParameter(String key, String value) {
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to edit parameter of null file, returning null");
			return false;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed to edit parameter " + key + " of file " + getFileName() + "(0)");
			return false;
		}
		if(getKeys().contains(key)){
			if(!editParameter(key, value)) {
				DemiConsole.error("Failed to edit parameter " + key + " of file " + getFileName() + "(1)");
				return false;
			}
			return true;
		}
		DemiConsole.warning("Attempted to set unset parameter " + key + " in file " + getFileName());
		DemiConsole.action("Creating parameter at the end of file...");
		if(!addParameter(key, value)) {
			DemiConsole.error("Failed to add parameter " + key + " in file " + getFile().getName());
			return false;
		}
		DemiConsole.ok("Parameter created !");
		return true;
	}
	
	public final boolean addParameter(String key, String value) {
		try {
			fileSemaphore.acquire();
		} catch (InterruptedException e1) {
			DemiConsole.error("Internal error occured while aquiring semaphore (add)");
			return false;
		}
		File inputFile = getFile();
		File tempFile = new File(getFile().getName() + ".temp");
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){

			String currentLine;

			while((currentLine = reader.readLine()) != null) {
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.write(key + ":" + value + System.getProperty("line.separator"));
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			boolean success = tempFile.renameTo(inputFile);
			fileSemaphore.release();
			return success;
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to add parameter ("+key+") in file ("+getFile().getName()+"), returning false");
			handleTrace(e);
			fileSemaphore.release();
			return false;
		}
	}

	public final boolean setParameter(Key key) {
		if(getFile() == null) {
			DemiConsole.cancelled("Attempted to edit parameter of null file, returning null");
			return false;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed to edit parameter " + key + " of file " + getFileName() + "(0)");
			return false;
		}
		if(getKeys().contains(key.name())){
			if(!editParameter(key.name(), key.defaultValue())) {
				DemiConsole.error("Failed to edit parameter " + key + " of file " + getFileName() + "(1)");
				return false;
			}
			return true;
		}
		DemiConsole.warning("Attempted to set unset parameter " + key + " in file " + getFileName());
		DemiConsole.action("Creating parameter at the end of file...");
		if(!addParameter(key)) {
			DemiConsole.error("Failed to add parameter " + key + " in file " + getFile().getName());
			return false;
		}
		DemiConsole.ok("Parameter created !");
		return true;
	}

	public final boolean addParameter(Key key) {
		try {
			fileSemaphore.acquire();
		} catch (InterruptedException e1) {
			DemiConsole.error("Internal error occured while aquiring semaphore (add)");
			return false;
		}
		
		File inputFile = getFile();
		File tempFile = new File(getFile().getName() + ".temp");
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){

			String currentLine;

			while((currentLine = reader.readLine()) != null) {
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.write(System.getProperty("line.separator"));
			writer.write(key.defaultComment() + System.getProperty("line.separator"));
			writer.write(key.name() + ":" + key.defaultValue() + System.getProperty("line.separator"));
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			boolean success = tempFile.renameTo(inputFile);
			fileSemaphore.release();
			return success;
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to add parameter ("+key+") in file ("+getFile().getName()+"), returning false");
			handleTrace(e);
			fileSemaphore.release();
			return false;
		}
	}
	
	public final boolean editParameter(String key, String value) {
		try {
			fileSemaphore.acquire();
		} catch (InterruptedException e1) {
			DemiConsole.error("Internal error occured while aquiring semaphore (edit)");
			return false;
		}
		//DemiConsole.action("Starting writing for " + key + " " + value);
		
		File inputFile = getFile();
		File tempFile = new File(getFile().getName() + ".temp");
		try {
			tempFile.createNewFile();
		} catch (IOException e) {
			DemiConsole.error("Failed to create temp file");
			handleTrace(e);
			fileSemaphore.release();
			return false;
		}
		//System.out.println("tempFile.isFile = " + tempFile.isFile());
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				if(trimmedLine.startsWith(key)) {
					writer.write(key + ":" + value + System.getProperty("line.separator"));
					continue;
				}
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			tempFile.renameTo(inputFile);
			
			
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to edit parameter ("+key+") in file ("+getFile().getName()+"), returning false");
			handleTrace(e);
			fileSemaphore.release();
			return false;
		}


		//DemiConsole.ok("Finished writing for " + key + " " + value);
		
		fileSemaphore.release();
		return true;
	}

	public final boolean removeParameter(File givenFile, String key) {
		try {
			fileSemaphore.acquire();
		} catch (InterruptedException e1) {
			DemiConsole.error("Internal error occured while aquiring semaphore (remove)");
			return false;
		}
		
		File inputFile = givenFile;
		File tempFile = new File(givenFile.getName() + ".temp");

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				if(trimmedLine.startsWith(key)) continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			fileSemaphore.release();
			return tempFile.renameTo(inputFile);
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception while attempting to remove parameter ("+key+") in file ("+givenFile.getName()+"), returning false");
			handleTrace(e);
			fileSemaphore.release();
			return false;
		}
	}

	public void setPrintStackTrace(boolean printStackTrace) {
		this.printStackTrace = printStackTrace;
		DemiConsole.ok(getFileName() + " IO now set to " + (printStackTrace ? "" : "not") +" print stack trace");
	}

	private void handleTrace(Exception e) {
		if(this.printStackTrace) {
			DemiConsole.info("Printing stack trace");
			e.printStackTrace();
		}else DemiConsole.cancelled(getFileName() + " IO set to not print stack trace");
	}

	public String getFileName() {
		return this.fileName;
	}

	public static IO findIOByFileName(String fileName2) {
		for(IO io : IO.all)	if(io.getFileName().equalsIgnoreCase(fileName2.replace(".cfg", "") + ".cfg")) return io;
		return null;
	}

	public static IO findIOByFile(File file) {
		for(IO io : IO.all) if(io.getFile().equals(file)) return io;
		return null;
	}

	public File getFile() {
		return this.file;
	}

	public HashMap<String, List<String>> getReversedMap(){
		HashMap<String, String> returnedAll = getAll();
		HashMap<String, List<String>> reversedMap = new HashMap<>();
		for(String key : returnedAll.keySet()) {
			String reversedKey = returnedAll.get(key);
			if(!reversedMap.containsKey(reversedKey)) reversedMap.put(reversedKey, new ArrayList<>());
			reversedMap.get(reversedKey).add(key);
		}
		return reversedMap;
	}

	public HashMap<String, List<String>> getSortedReversedMap() {
		HashMap<String, List<String>> reversedMap = getReversedMap();
		if(reversedMap == null) return null;
		HashMap<String, List<String>> sortedReversedMap = new HashMap<>();
		Set<String> keySet = reversedMap.keySet();
		List<String> sortedKeySet = new ArrayList<>();
		sortedKeySet.addAll(keySet);
		Collections.sort(sortedKeySet);
		for(String sortedKey : sortedKeySet) sortedReversedMap.put(sortedKey, reversedMap.get(sortedKey));
		return sortedReversedMap;
	}

	public HashMap<String, String> getSingleReversedMap(boolean returnNullIfMulti) {
		HashMap<String, List<String>> reversedMap = getReversedMap();
		HashMap<String, String> singleReversedMap = new HashMap<>();

		for(String key : reversedMap.keySet()) {
			List<String> list = reversedMap.get(key);
			if(list.size() > 1 && returnNullIfMulti) return null;
			singleReversedMap.put(key, list.get(0));
		}
		return singleReversedMap;
	}

	public HashMap<String, String> getSortedSingleReversedMap(boolean returnNullIfMulti) {
		HashMap<String, String> singleReversedMap = getSingleReversedMap(returnNullIfMulti);
		if(singleReversedMap == null) return null;
		HashMap<String, String> sortedSingleReversedMap = new HashMap<>();
		Set<String> keySet = singleReversedMap.keySet();
		List<String> sortedKeySet = new ArrayList<>();
		sortedKeySet.addAll(keySet);
		Collections.sort(sortedKeySet);
		for(String sortedKey : sortedKeySet) sortedSingleReversedMap.put(sortedKey, singleReversedMap.get(sortedKey));
		return sortedSingleReversedMap;
	}

}













