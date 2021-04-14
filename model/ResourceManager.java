package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager {

	// takes in a serializable object & fileName (what we call our save data file) 
	public static void save(Serializable data, String fileName) throws Exception {
		
		// opens new OutputStream, and if something goes wrong it will close automatically
		// creates a path and open a new OutputStream to that path - so it can write to it
		try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			oos.writeObject(data);
		}
	}
	
	// returning general Object type 
	// pass in the file name and return the readObject
	public static Object load(String fileName) throws Exception {
		try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
			return ois.readObject();
		}
	}
}