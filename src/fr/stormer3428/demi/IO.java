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
import java.util.HashMap;
import java.util.List;

public class IO {

	public static ArrayList<String> defaultHeaders = new ArrayList<>();

	static {
		defaultHeaders.add("#");
		defaultHeaders.add("//");
	}

	private File file;
	private String fileName;
	private List<String> commentLinesHeaders = new ArrayList<>();
	private boolean printStackTrace;

	public IO(File file, List<String> commentLinesHeaders, boolean printStackTrace) {
		if(file == null) {
			DemiConsole.error("Attempted to create an IO interface for a null file!");
			DemiConsole.warning("Cancelled messages will be thrown whenever this IO is called");
			return;
		}
		this.commentLinesHeaders = commentLinesHeaders;
		this.printStackTrace = printStackTrace;
		this.file = file;
		this.fileName = file.getName();
		DemiConsole.ok("IO interface for file " + fileName + " successfully created!");
	}

	public IO(File file, List<String> commentLinesHeaders) {
		this(file, commentLinesHeaders, true);
	}

	public IO(File file, boolean printStackTrace) {
		this(file, defaultHeaders, printStackTrace);
	}

	public IO(File file) {
		this(file, defaultHeaders, true);
	}

	public final boolean fileCheck() {
		if(file == null) {
			DemiConsole.cancelled("Attempted to check integrity of null file, returning false");
			return false;
		}
		if(!(file.exists() && file.isFile())) {
			DemiConsole.warning("Missing file " + fileName +" !");
			DemiConsole.info("Attenpting to create file " + fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				DemiConsole.error("Failed to create file " + fileName);
				return false;
			}
			DemiConsole.ok("Created file " + fileName + "successfully !");
		}
		return true;
	}

	public final List<String> getKeys(){
		if(file == null) {
			DemiConsole.cancelled("Attempted to retrieve keys of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of keys for file " + fileName);
			DemiConsole.warning("retuning an empty list");
			return new ArrayList<>();
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning empty list");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not print stack trace");
			return new ArrayList<>();
		}
		List<String> keys = new ArrayList<>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.contains(":")) continue;
			keys.add(line.split(":",2)[0]);
		}
		return keys;
	}

	private boolean isCommentLine(String line) {
		for(String header : commentLinesHeaders) if(line.startsWith(header)) return true;
		return false;
	}

	public final List<String> getValues(){
		if(file == null) {
			DemiConsole.cancelled("Attempted to retrieve values of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of keys for file " + fileName);
			DemiConsole.warning("retuning an empty list");
			return new ArrayList<>();
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning null");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not print stack trace");
			return null;
		}
		List<String> keys = new ArrayList<>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.contains(":")) continue;
			keys.add(line.split(":",2)[1].replace("\"", ""));
		}
		return keys;
	}

	public final String get(String key){
		if(file == null) {
			DemiConsole.cancelled("Attempted to retrieve value " + key + " of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of value for file " + fileName);
			DemiConsole.warning("retuning an null");
			return null;
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning null");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not print stack trace");
			return null;
		}
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.startsWith(key + ":")) continue;
			String s = line.substring(key.length() + 1);
			s = s.replace("\"", "");
			return s;

		}
		return "";
	}
	
	public final HashMap<String, String> getAll(){
		if(file == null) {
			DemiConsole.cancelled("Attempted to retrieve entirety of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of entirety of file " + fileName);
			DemiConsole.warning("retuning an empty hashmap");
			return new HashMap<>();
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning null");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not print stack trace");
			return null;
		}
		HashMap<String, String> keys = new HashMap<String, String>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			if(!line.contains(":")) continue;
			keys.put(line.split(":",2)[0], line.split(":",2)[1].replace("\"", ""));

		}
		return keys;
	}

	public final ArrayList<String> getAllRaw(){
		if(file == null) {
			DemiConsole.cancelled("Attempted to retrieve raw version of null file, returning null");
			return null;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed retrieval of raw file " + fileName);
			DemiConsole.warning("retuning an empty list");
			return new ArrayList<String>();
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(file.toPath(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning null");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not print stack trace");
			return null;
		}
		ArrayList<String> keys = new ArrayList<String>();
		for(String line : lines){
			if(isCommentLine(line)) continue;
			keys.add(line);
		}
		return keys;
	}

	public final boolean setParameter(String key, String value) {
		if(file == null) {
			DemiConsole.cancelled("Attempted to edit parameter of null file, returning null");
			return false;
		}
		if(!fileCheck()) {
			DemiConsole.error("Failed to edit parameter " + key + " of file " + fileName);
			return false;
		}
		if(getKeys().contains(key)){
			if(!editParameter(key, value)) {
				DemiConsole.error("Failed to edit parameter " + key + " of file " + fileName);
				return false;
			}
			return true;
		}
		DemiConsole.warning("Attempted to set unset parameter " + key + " in file " + fileName);
		DemiConsole.info("Creating parameter at the end of file...");
		if(!addParameter(key, value)) {
			DemiConsole.error("Failed to add parameter " + key + " in file " + file.getName());
			return false;
		}
		DemiConsole.ok("Parameter created !");
		return true;
	}

	public final boolean addParameter(String key, String value) {
		File inputFile = file;
		File tempFile = new File(file.getName() + ".temp");
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){

			String currentLine;

			while((currentLine = reader.readLine()) != null) {
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.write(key + ":" + value + System.getProperty("line.separator"));
			writer.close(); 
			reader.close(); 
			inputFile.delete();
			return tempFile.renameTo(inputFile);
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning false");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not print stack trace");
			return false;
		}
	}

	public final boolean editParameter(String key, String value) {
		File inputFile = file;
		File tempFile = new File(file.getName() + ".temp");
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
			return tempFile.renameTo(inputFile);
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning false");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not stack trace");
			return false;
		}

	}

	public final boolean removeParameter(File file, String key) {
		File inputFile = file;
		File tempFile = new File(file.getName() + ".temp");

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				if(trimmedLine.startsWith(key)) continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			return tempFile.renameTo(inputFile);
		} catch (IOException e) {
			DemiConsole.error("Caught an IO exception, returning false");
			if(printStackTrace) {
				DemiConsole.info("Printing stack trace");
				e.printStackTrace();
			}else DemiConsole.cancelled(fileName + " IO set to not stack trace");
			return false;
		}
	}

	public void setPrintStackTrace(boolean printStackTrace) {
		this.printStackTrace = printStackTrace;
		DemiConsole.ok(fileName + " IO now set to " + (printStackTrace ? "" : "not") +" print stack trace");
	}

}
