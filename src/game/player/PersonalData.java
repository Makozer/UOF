package game.player;

import java.util.Date;

/**
 * This Class is for any personal Data about the User / Player
 * @author Martin
 *
 */
public class PersonalData {
	
	private int 	id = 0;
	private String 	email = "";
	
	private String 	displayName = "";
	private String 	preName = "";
	private String 	surName = "";
	private Date	birthday = null;
	
	private Date 	created = 	null;
	private Date 	lastLogin = null;
	
	public PersonalData() {}
	
	/**
	 * Constructor to create this Class
	 * @param id int ID of the User
	 * @param email
	 * @param displayName
	 * @param preName
	 * @param surName
	 * @param birthday
	 * @param created
	 * @param lastLogin
	 */
	public PersonalData(int id, String email, String displayName, String preName, String surName, Date birthday,
			Date created, Date lastLogin) {
		super();
		this.id = id;
		this.email = email;
		this.displayName = displayName;
		this.preName = preName;
		this.surName = surName;
		this.birthday = birthday;
		this.created = created;
		this.lastLogin = lastLogin;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "PersonalData [id=" + id + ", email=" + email + ", displayName=" + displayName + ", preName=" + preName
				+ ", surName=" + surName + ", birthday=" + birthday + ", created=" + created + ", lastLogin="
				+ lastLogin + "]";
	}

}
