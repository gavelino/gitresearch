package dcc.gaa.mes.gitproject.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GitLabel implements Serializable {

	private String color;

	private String name;

	private String url;

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof GitLabel))
			return false;

		final String name = this.name;
		return name != null && name.equals(((GitLabel) obj).name);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final String name = this.name;
		return name != null ? name.hashCode() : super.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		final String name = this.name;
		return name != null ? name : super.toString();
	}

	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 * @return this label
	 */
	public GitLabel setColor(String color) {
		this.color = color;
		return this;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * @return this label
	 */
	public GitLabel setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 * @return this label
	 */
	public GitLabel setUrl(String url) {
		this.url = url;
		return this;
	}

}
