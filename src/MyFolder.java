import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MyFolder{
	/*
	 * Class for getting folder information
	 * Stores:
	 * - List of all files in folder and subfolders
	 * - List od subfolders
	 * - Size in bytes of folder
	 * - Number of files
	 * - Number of subfolders
	 * Methods:
	 * - Inititalizes, can be called to reset attributes if folder is changed
	 * - get for each attribute
	 * - get
	 * - set folder path (used to change on the fly folder)
	 * - printInfo: prints size, count of files and count of subfolders
	 * - printFileList: prints list of files in canonical path format
	 * - printFolderList: prints list of subfolders in canonical format		 
	 */
		private String folderpath;
		private Path folder;
		private long size;
		private long filecount;
		private long dircount;

		MyFolder(File dir) {
			this.folderpath = dir.getAbsolutePath();
			this.Initialize();
			
		}
		public void Initialize() {
			this.folder = Paths.get(this.folderpath);
			this.setFileCount();
			this.setFolderCount();
			//this.filecount = this.getFileCount();
			//this.dircount = this.getFolderCount();
			this.size = this.getFileSize();			
		}
		
		
		private void setFileCount() {
			long counter = 0;
			try {
				counter = Files.walk(this.folder).filter(p -> p.toFile().isFile()).count();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.filecount = counter;
		}
		
		private void setFolderCount() {
			long counter = 0;
			try {
				counter = Files.walk(this.folder).filter(p -> p.toFile().isDirectory()).count();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.dircount = counter;
		}
		
		public long getFileCount() {
			return this.filecount;
		}
		public long getFolderCount() {
			return this.dircount;
		}
		
		/*public long getFileCount() {
			long counter = 0;
			try {
				counter = Files.walk(this.folder).filter(p -> p.toFile().isFile()).count();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return counter;
		}*/
		
		public long getSize() {
			return this.size;
		}
		/*
		public long getFolderCount() {
			long counter = 0;
			try {
				counter = Files.walk(this.folder).filter(p -> p.toFile().isDirectory()).count();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return counter;
		}*/
		public String getFolderPath() {
			return this.folderpath;
		}
		
		public long getFileSize() {
			long size = 0;
			
			try {
				size = Files.walk(this.folder).filter(p -> p.toFile().isFile()).mapToLong(p -> p.toFile().length()).sum();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return size;
		}
		
		public void changeFolder(File dir) {
			this.folderpath = dir.getAbsolutePath();
			this.Initialize();
		}
		public void printListOfFiles() {
			System.out.println("\nFiles list in folder " + this.getFolderPath());
			try {
				Files.walk(this.folder).filter(Files::isRegularFile).forEach(filePath -> System.out.println(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		public void printListOfFolders() {
			System.out.println("\nFolder list in folder " + this.getFolderPath());
			try {
				Files.walk(this.folder).filter(Files::isDirectory).forEach(filePath -> System.out.println(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean doesFileFolderExists() {
			return new File(this.folderpath).exists();
			
		}
		
		public void printFolderInfo() {
			String additional_text = "";
			String additional_size = "";
			if (this.doesFileFolderExists()== false) {
				additional_text = " - Folder does NOT exists!";
			}
			if (this.getSize() > 1000) {
				additional_size = String.format("(%s KB)", (float)this.getSize()/1024);
			}
			if (this.getSize() > 1000000) {
				additional_size = String.format("(%s MB)", (float)this.getSize()/1024/1024);
			}
			if (this.getSize() > 1000000000) {
				additional_size = String.format("(%s GB)", (float)this.getSize()/1024/1024/1024);
			}
			System.out.println(String.format("\n#################################################\nCurrent folder: %s %s", this.getFolderPath(),additional_text));
			System.out.println(String.format("Total number of files: %s", this.getFileCount()));
			System.out.println(String.format("Total number of folders: %s", this.getFolderCount()));
			System.out.println(String.format("Folder Size: %s bytes %s", this.getSize(), additional_size));
		}	
}

