package  com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import  com.example.demo.jpa.ItemRepository;
import  com.example.demo.model.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="Items API")
@RestController
@RequestMapping("/api")

public class ItemController {

	@Autowired
	private ItemRepository repo;
	
	@ApiOperation(value="List of the inventory items")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrive item list"),	
	})
	@GetMapping("/items")
	public List<Item> getItems() {
		return repo.findAll();
	}
	
	@ApiOperation(value="Get item details by itemno")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrive item by item no"),
	})
	@GetMapping("/item/{itemno}")
	public Item getItem(@PathVariable("itemno") long itemno) {
		Optional<Item> theItem = repo.findById(itemno);
		if (!theItem.isPresent())
			throw new RuntimeException("Item " +itemno+ " not exist");
		return theItem.get();
	}
	
	@ApiOperation(value="Add item to stock")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added"),
	})
	@PostMapping("/item")
	public Item addItem(@RequestBody Item item) {
		if (repo.findById(item.getItemNo()).isPresent())
			throw new RuntimeException();
		repo.save(item);
		return item;
	}
	
	@ApiOperation(value="Delete an item from stock")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted"),
	})
	@DeleteMapping("/item/{itemno}")
	public Item deleteItem(@PathVariable long itemno) {
		Optional<Item> theItem = repo.findById(itemno);
		if (!theItem.isPresent())
			throw new RuntimeException("Item " +itemno+ " not exist");
		Item temp = repo.getOne(itemno);
		repo.delete(temp);
		return theItem.get();
	}
	
	@ApiOperation(value="Deposit quantity of a specific item to stock")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deposit additional quantity"),
	})
	@PutMapping("/item/{itemno}/deposit")
	public Item depositeItem(@PathVariable long itemno, @RequestBody int addedAmount) throws RuntimeException {
		if (addedAmount < 0)
			throw new RuntimeException();
		Item updateItem = addAmount(itemno, addedAmount);
		return updateItem;
	}
	
	@ApiOperation(value="Withdrawal quantity of a specific item from stock")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully withdrawal additional quantity"),
	})
	@PutMapping("/item/{itemno}/withdrawal")
	public Item withdrawItem(@PathVariable long itemno, @RequestBody int withdrawedAmount) throws RuntimeException {
		if (withdrawedAmount < 0)
			throw new RuntimeException();
		Item updateItem = withdrawAmount(itemno, withdrawedAmount);
		return updateItem;
	}
	

	private Item addAmount(long itemno, int addedAmount) {
		Optional<Item> theItem = repo.findById(itemno);
		if (!theItem.isPresent())
			throw new RuntimeException("Item " +itemno+ " not exist"); 
		Item updatedItem = theItem.get();
		int currentAmount = updatedItem.getAmount();
		updatedItem.setAmount(currentAmount + addedAmount);
		repo.save(updatedItem);
		return updatedItem;
	}
	
	private Item withdrawAmount(long itemno, int withdrawedAmount) {
		Optional<Item> theItem = repo.findById(itemno);
		if (!theItem.isPresent())
			throw new RuntimeException("Item " +itemno+ " not exist"); 
		Item updatedItem = theItem.get();
		int currentAmount = updatedItem.getAmount();
		if (currentAmount - withdrawedAmount <0 ) {
			throw new RuntimeException("amount cant be smaller than 0");
		}
		updatedItem.setAmount(currentAmount - withdrawedAmount);
		repo.save(updatedItem);
		return updatedItem;
	}
}