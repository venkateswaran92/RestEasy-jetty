package com.venkat.gerericDao;

public class Page {

	private int currentSize;
	private int offSize;
	private int totalSize;
	private int totalPages;
	private int currentPage;
	private Boolean totalSizeNew;

	public Page() {
		super();
	}

	/**
	 * 
	 * @param currentSize
	 * @param offSize
	 * @since 2015-10-22
	 */
	public Page(int currentSize, int offSize) {
		super();
		this.currentSize = currentSize;
		this.offSize = offSize;
	}

	/**
	 * 
	 * @param currentSize
	 * @param offSize
	 * @param totalSizeNew
	 */
	public Page(int currentSize, int offSize, boolean totalSizeNew) {
		super();
		this.currentSize = currentSize;
		this.offSize = offSize;
		this.totalSizeNew = totalSizeNew;
	}
	/**
	 * 
	 * @param currentSize
	 * @param offSize
	 * @param totalSize
	 * @param totalPages
	 * @param currentPage
	 * @param totalSizeNew
	 * @author lqy
	 * @since 2015-10-22
	 */
	public Page(int currentSize, int offSize, int totalSize, int totalPages, int currentPage, boolean totalSizeNew) {
		super();
		this.currentSize = currentSize;
		this.offSize = offSize;
		this.totalSize = totalSize;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.totalSizeNew = totalSizeNew;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public int getOffSize() {
		return offSize;
	}

	public void setOffSize(int offSize) {
		this.offSize = offSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Boolean getTotalSizeNew() {
		return totalSizeNew;
	}

	public void setTotalSizeNew(Boolean totalSizeNew) {
		this.totalSizeNew = totalSizeNew;
	}

	@Override
	public String toString() {
		return "Page [currentSize=" + currentSize + ", offSize=" + offSize + ", totalSize=" + totalSize
				+ ", totalPages=" + totalPages + ", currentPage=" + currentPage + ", totalSizeNew=" + totalSizeNew
				+ "]";
	}
}
