package core.com.board.requestparam.model;

import org.springframework.stereotype.Component;

//class model request parameters 
@Component
public class RequestParamSerch {
	 // name rubrica advertisement
	private String select_rubric ;
	//name user  which write advertisement
	private String nameUser;
	 // all advertisement  single user (result checkbooks)
	private String myAds;
	/**
	 * 
	 */
	public RequestParamSerch() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param select_rubric
	 * @param nameUser
	 * @param myAds
	 */
	public RequestParamSerch(String select_rubric, String nameUser, String myAds) {
		this.select_rubric = select_rubric;
		this.nameUser = nameUser;
		this.myAds = myAds;
	}
	/**
	 * @return the select_rubric
	 */
	public String getSelect_rubric() {
		return select_rubric;
	}
	/**
	 * @param select_rubric the select_rubric to set
	 */
	public void setSelect_rubric(String select_rubric) {
		this.select_rubric = select_rubric;
	}
	/**
	 * @return the nameUser
	 */
	public String getNameUser() {
		return nameUser;
	}
	/**
	 * @param nameUser the nameUser to set
	 */
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	/**
	 * @return the myAds
	 */
	public String getMyAds() {
		return myAds;
	}
	/**
	 * @param myAds the myAds to set
	 */
	public void setMyAds(String myAds) {
		this.myAds = myAds;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestParamSerch [select_rubric=" + select_rubric
				+ ", nameUser=" + nameUser + ", myAds=" + myAds + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myAds == null) ? 0 : myAds.hashCode());
		result = prime * result
				+ ((nameUser == null) ? 0 : nameUser.hashCode());
		result = prime * result
				+ ((select_rubric == null) ? 0 : select_rubric.hashCode());
		return result;
	}
	/* (non-Javadoc)
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
		RequestParamSerch other = (RequestParamSerch) obj;
		if (myAds == null) {
			if (other.myAds != null)
				return false;
		} else if (!myAds.equals(other.myAds))
			return false;
		if (nameUser == null) {
			if (other.nameUser != null)
				return false;
		} else if (!nameUser.equals(other.nameUser))
			return false;
		if (select_rubric == null) {
			if (other.select_rubric != null)
				return false;
		} else if (!select_rubric.equals(other.select_rubric))
			return false;
		return true;
	}
	
	

}
