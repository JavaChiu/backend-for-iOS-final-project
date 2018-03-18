package mobi.uchicago.finalproject.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name="item")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
@JsonIgnoreProperties(value = {"user"}, allowGetters = true)
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	private boolean gived;

	@Column(name="img_url")
	private String imgUrl;

	@Lob
	@Column(name="pickup_address")
	private String pickupAddress;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;
	
//	@Column(name="user_id", updatable=false, insertable=false)
//	private Integer userId;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getGived() {
		return this.gived;
	}

	public void setGived(boolean gived) {
		this.gived = gived;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPickupAddress() {
		return this.pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}