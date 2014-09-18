package dcc.gaa.mes.gitresearch.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.eclipse.egit.github.core.Label;

@Entity
@SuppressWarnings("serial")
public class GitLabel implements Serializable {

	private String color;

	private String name;

	@Id
	private String url;
	
	public GitLabel() {
	}
	
	public GitLabel(Label label) {
		if (label !=null) {
			this.setColor(label.getColor());
			this.setName(label.getName());
			this.setUrl(label.getUrl());
		}
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GitLabel [color=").append(color).append(", name=")
				.append(name).append("]");
		return builder.toString();
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
