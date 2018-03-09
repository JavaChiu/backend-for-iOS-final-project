package mobi.uchicago.finalproject.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String address;

	@Lob
	private String description;

	private String email;

	@Column(name="facebook_id")
	private int facebookId;

	@Column(name="user_name")
	private String userName;

//	//bi-directional many-to-one association to Item
//	@OneToMany(mappedBy="user")
//	private List<Item> items;
//
//	//bi-directional many-to-one association to Message
//	@OneToMany(mappedBy="user1")
//	private List<Message> messages1;
//
//	//bi-directional many-to-one association to Message
//	@OneToMany(mappedBy="user2")
//	private List<Message> messages2;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(int facebookId) {
		this.facebookId = facebookId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public List<Item> getItems() {
//		return this.items;
//	}
//
//	public void setItems(List<Item> items) {
//		this.items = items;
//	}

//	public Item addItem(Item item) {
//		getItems().add(item);
//		item.setUser(this);
//
//		return item;
//	}

//	public Item removeItem(Item item) {
//		getItems().remove(item);
//		item.setUser(null);
//
//		return item;
//	}
//
//	public List<Message> getMessages1() {
//		return this.messages1;
//	}
//
//	public void setMessages1(List<Message> messages1) {
//		this.messages1 = messages1;
//	}

//	public Message addMessages1(Message messages1) {
//		getMessages1().add(messages1);
//		messages1.setUser1(this);
//
//		return messages1;
//	}
//
//	public Message removeMessages1(Message messages1) {
//		getMessages1().remove(messages1);
//		messages1.setUser1(null);
//
//		return messages1;
//	}
//
//	public List<Message> getMessages2() {
//		return this.messages2;
//	}
//
//	public void setMessages2(List<Message> messages2) {
//		this.messages2 = messages2;
//	}
//
//	public Message addMessages2(Message messages2) {
//		getMessages2().add(messages2);
//		messages2.setUser2(this);
//
//		return messages2;
//	}
//
//	public Message removeMessages2(Message messages2) {
//		getMessages2().remove(messages2);
//		messages2.setUser2(null);
//
//		return messages2;
//	}

}