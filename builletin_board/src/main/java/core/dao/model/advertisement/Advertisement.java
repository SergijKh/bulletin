package core.dao.model.advertisement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import core.dao.model.login.Login;

/**
 * class describe model advertisement
 * 
 * @author Sergey
 *
 */
@Entity
@Table
public class Advertisement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1038570032143070431L;
	/**
	 * Id advertisement
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADVERTISEMENT", nullable = false)
	private int idAdvertisement;
	/**
	 * date create and update advertisement
	 */

	@Column(name = "PUBLICATION_DATE")
	private Long modifiedDate;
	/**
	 * rubric advertisement
	 */
	@Column(name = "RUBRIC", nullable = false)
	private String rubric;
	/**
	 * title advertisement
	 */
	@NotNull
	@Size(min= 10, max=30)
	@Column(name = "TITLE", nullable = false)
	private String title;
	/**
	 * text advertisement
	 */
	@Size(min= 20, max=400)
	@Column(name = "TEXT")
	private String text;

	/**
	 * Login
	 */

	@JsonManagedReference("login")
	@ManyToOne
	@JoinColumn(name = "ID_LOGIN", nullable = false)
	private Login login;

	/**
	 * empty constructor
	 */
	public Advertisement() {
		super();

	}

	/**
	 * 
	 * @param rubric
	 *            rubric advertisement
	 * @param title
	 *            title advertisement
	 * @param text
	 *            text advertisement
	 */
	public Advertisement(String rubric, String title, String text) {

		this.rubric = rubric;
		this.title = title;
		this.text = text;
	}

	/**
	 * 
	 * @param rubric
	 *            rubric advertisement
	 * @param title
	 *            title advertisement
	 * @param text
	 *            text advertisement
	 * @param idLogin
	 *            id login
	 */
	public Advertisement(String rubric, String title, String text, Login idLogin) {

		this.rubric = rubric;
		this.title = title;
		this.text = text;
		this.login = idLogin;
	}

	/**
	 * @param idAdvertisement
	 * @param rubric
	 * @param title
	 * @param text
	 */
	public Advertisement(int idAdvertisement, String rubric, String title,
			String text) {
		this.idAdvertisement = idAdvertisement;
		this.rubric = rubric;
		this.title = title;
		this.text = text;
	}

	/**
	 * @param rubric
	 * @param title
	 * @param text
	 * @param modifiedDate
	 */
	public Advertisement(String rubric, String title, String text, long date) {

		this.rubric = rubric;
		this.title = title;
		this.text = text;
		this.modifiedDate = date;
	}

	/**
	 * @return the login
	 */

	public Login getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @return the idAdvertisement
	 */
	public int getIdAdvertisement() {
		return idAdvertisement;
	}

	/**
	 * @param idAdvertisement
	 *            the idAdvertisement to set
	 */
	public void setIdAdvertisement(int idAdvertisement) {
		this.idAdvertisement = idAdvertisement;
	}

	/**
	 * @return the modifiedDate
	 */
	public Long getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the rubric
	 */
	public String getRubric() {
		return rubric;
	}

	/**
	 * @param rubric
	 *            the rubric to set
	 */
	public void setRubric(String rubric) {
		this.rubric = rubric;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAdvertisement;
		result = prime * result
				+ ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
		result = prime * result + ((rubric == null) ? 0 : rubric.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advertisement other = (Advertisement) obj;
		if (idAdvertisement != other.idAdvertisement)
			return false;
		if (modifiedDate == null) {
			if (other.modifiedDate != null)
				return false;
		} else if (!modifiedDate.equals(other.modifiedDate))
			return false;
		if (rubric == null) {
			if (other.rubric != null)
				return false;
		} else if (!rubric.equals(other.rubric))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
