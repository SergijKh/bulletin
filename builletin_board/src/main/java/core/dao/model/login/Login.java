package core.dao.model.login;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import core.dao.model.advertisement.Advertisement;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @author Sergey The Login Class provides functions that help identify
 *         information about the login, password, user visiting your site. In
 *         addition you can get information as well email.
 */
@Entity
@Table
public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1340222038942854796L;
	/**
	 * id login
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = " ID_LOGIN", nullable = false)
	private int idLogin;
	/**
	 * login user
	 */
	@NotNull
	@Size(min= 2, max=16)
	@Column(name = "LOGIN", nullable = false)
	private String login;
	/**
	 * password user
	 */
	@NotNull
	@Size(min= 5, max=16)
	@JsonIgnore
	@Column(name = "PASSword", nullable = false)
	private String password;

	/**
	 * name user
	 */
	@Column(name = "NAME_USER", nullable = false)
	@NotNull
	@Size(min= 2, max=16)
	private String nameUser;
	/**
	 * set advertisement which have user
	 */

	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference("login")
	@OneToMany(cascade = CascadeType.MERGE, /* fetch = FetchType.LAZY */mappedBy = "login")
	private Set<Advertisement> advertis;

	/**
	 * empty constructor
	 */
	public Login() {
	}

	/**
	 * 
	 * @param id
	 *            id Login
	 * @param login
	 *            login user
	 * @param password
	 *            password user
	 * @param nameUser
	 *            name user
	 */
	public Login(int id, String login, String password, String nameUser) {
		super();
		this.idLogin = id;
		this.login = login;
		this.password = password;
		this.nameUser = nameUser;
	}

	/**
	 * 
	 *
	 * @param login
	 *            login user
	 * @param pasword
	 *            password user
	 */
	public Login(String login, String password, String nameUser) {
		super();
		this.login = login;
		this.password = password;
		this.nameUser = nameUser;
	}

	/**
	 * @param login
	 * @param password
	 */
	public Login(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * @return the idLogin
	 */
	public int getIdLogin() {
		return idLogin;
	}

	/**
	 * @param idLogin
	 *            the idLogin to set
	 */
	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the pasword
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param pasword
	 *            the pasword to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the advertis
	 */

	public Set<Advertisement> getAdvertis() {
		return advertis;
	}

	/**
	 * @param advertis
	 *            the advertis to set
	 */
	public void setAdvertis(Set<Advertisement> advertis) {
		this.advertis = advertis;
	}

	/**
	 * @return the nameUser
	 */
	public String getNameUser() {
		return nameUser;
	}

	/**
	 * @param nameUser
	 *            the nameUser to set
	 */
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
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
		result = prime * result + idLogin;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((nameUser == null) ? 0 : nameUser.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		Login other = (Login) obj;
		if (idLogin != other.idLogin)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}
