package com.eg.util;

import java.io.File;
import java.util.List;

public class FileRenameUtil {

	public static int rename(File folder, List<String> deleteList) {
		File[] files = folder.listFiles();
		int count = 0;
		for (File file : files) {
			String filename = null;
			for (String deleteString : deleteList) {
				if (deleteString.isEmpty()) {
					continue;
				}
				if (filename == null) {
					filename = file.getName();
				}
				while (filename.contains(deleteString)) {
					filename = filename.replace(deleteString, "");
				}
			}
			String oldPath = file.getAbsolutePath();
			String newPath = oldPath.substring(0, oldPath.lastIndexOf(File.separator)) + File.separator + filename;
			if (oldPath.equals(newPath) == false) {
				file.renameTo(new File(newPath));
				count++;
			}
		}
		return count;
	}

}
