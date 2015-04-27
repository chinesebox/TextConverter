package org.textconverter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Text contains list of sentences
 * 
 * @author ocean
 */
public class Text {
	
	private List<Sentence> sentences = new ArrayList<Sentence>();

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sentences == null) ? 0 : sentences.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Text other = (Text) obj;
		if (sentences == null) {
			if (other.sentences != null)
				return false;
		} else if (!sentences.equals(other.sentences))
			return false;
		return true;
	}
	
}
