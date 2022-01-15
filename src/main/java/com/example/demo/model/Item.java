package  com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class Item {
	
	
	@ApiModelProperty(notes = "Item's no")
	@Id
	private long itemNo;
	
	@ApiModelProperty(notes = "Item's name")
	private String name;
	
	@ApiModelProperty(notes = "Item's amount")
	private int amount;
	
	@ApiModelProperty(notes = "Item's inventory code")
	private long inventoryCode;

	public long getItemNo() {
		return itemNo;
	}

	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(long inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	
	@Override
	public String toString() {
		return "item no=" + itemNo + ", item name=" + name + ", item amount=" + amount + ", item code=" + inventoryCode
				+ "]";
	}
}
