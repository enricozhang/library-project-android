package com.meimob.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class SearchFile {
	/**
	 * @param allList
	 * @param endName
	 * @param filenames
	 */
	public static void findFile(File allList, String endName, List<File> filenames) {
		FileFilter filefilter = new ExtensionFileFilter(endName);
		// 创建fileArray名字的数组 ，存储allList目录下的所有文件
		File[] fileArray = allList.listFiles();
		// 如果传进来一个以文件作为对象的allList 返回
		if (null == fileArray) {
			return;
		}
		File[] files = null;
		if ((allList.getAbsolutePath().indexOf("sd") != -1)
				|| (allList.getAbsolutePath().indexOf("SD") != -1)) {
			files = allList.listFiles(filefilter);
			if (files.length > 0) {
				System.out.println("目录: " + files[0].getAbsolutePath());
				filenames.add(files[0]);// 找到所搜索文件，添加到存储器
			}
		}

		// 偏历目录下的文件
		for (int i = 0; i < fileArray.length; i++) {
			// 如果是个目录
			if (fileArray[i].isDirectory()) {
				String dir = fileArray[i].getAbsolutePath();
				if (dir.length() - dir.replaceAll("/", "").length() > 3)
					break;
				findFile(fileArray[i].getAbsoluteFile(), endName, filenames); // 递归
			}
		}
	}

	public static class ExtensionFileFilter implements FileFilter {
		private String extension;

		public ExtensionFileFilter(String extension) {
			this.extension = extension;
		}

		public boolean accept(File file) {
			if (file.isDirectory()) {
				return false;
			}

			String name = file.getName();// find the last
			return name.equals(extension);// 搜索特定名称的文件

			/*
			 * int index = name.lastIndexOf(".");//搜索特定后缀名文件 if(index == -1) { return false; }else
			 * if(index == name.length( ) -1) { return false; } else { return
			 * this.extension.equals(name.substring(index+1)); }
			 */
		}
	}

	public static void getpath() {
		String dir = "/";
		File file = new File(dir);
		List<File> filenames = new ArrayList<File>();
		findFile(file, "hello_sd.key", filenames);
		// File[] files = (File[]) filenames.toArray();
		System.out.println("file:=" + filenames.get(0));// 打印搜索文件，filenames中存储找到的文件信息
	}

}
