package com.jhj.page;

public class Search {
	private String kind;
	private String search;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
		if (this.kind == null || this.kind.equals("")) {
			this.kind = "title";
		}
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
		if (this.search == null) {
			this.search = "";
		}
	}

}
