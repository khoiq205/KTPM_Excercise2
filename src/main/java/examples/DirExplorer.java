package examples;

import java.io.File;
import java.io.FileFilter;

public class DirExplorer {
	// interface for file handler
	public interface FileHandler {
		void handle(int level, String path, File file);
	}

	// interface for file filter
	public interface Filter {
		boolean interested(int level, String path, File file);
	}

	private FileHandler fileHandler;
	private Filter filter;

	public DirExplorer(Filter filter, FileHandler fileHandler) {
		this.filter = filter;
		this.fileHandler = fileHandler;
		}
	public void explorer(File root) {
		explorer(0, "", root);
	}

	private void explorer(int level, String path, File file) {
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				explorer(level + 1, path + "/" + child.getName(), child);
			}
		} else {
			if (filter.interested(level, path, file)) {
				fileHandler.handle(level, path, file);
			}
		}
	}

	public static void main(String[] args) {
		File projectDir = new File("T:\\20062501_PhamTrongHieu_week02\\APIGETWAY");
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			System.out.println(path);
		}).explorer(projectDir);
	}
}
