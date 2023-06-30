package main.test.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.controllers.StoreIndentController;
import main.dao.storeIndents.StoreIndentsDao;
import main.models.storeIndentModels.outputmodels.StoreIndentDataOutput;
import main.models.storeIndentModels.outputmodels.StoreIndentProducts;
import main.models.storeModels.entities.StoreIndentData;
import main.models.storeModels.entities.StoreIndentsList;
import main.models.storeModels.inputmodels.IndentId;
import main.models.storeModels.inputmodels.StoreIndentsInputList;
import main.service.store.interfaces.StoreService;

public class StoreIndentControllerTest {

	@Mock
	private StoreIndentsDao storeIndentsDao;

	@Mock
	private StoreService storeService;

	@Mock
	private ModelMapper modelMapper;
	@Mock
	private ObjectMapper objectMapper;

	@InjectMocks
	private StoreIndentController storeIndentController;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetStoreIndentList() {

		System.out.println("Data isssss \n\n\n");
		System.out.println(storeIndentsDao.getStoreIndentsList());
		// Mock input data
		List<StoreIndentData> storeIndentsList = new ArrayList<>();
		storeIndentsList.add(new StoreIndentData());

		// Mock dependencies
		when(storeIndentsDao.getStoreIndentsList()).thenReturn(storeIndentsList);
		when(modelMapper.map(storeIndentsList.get(0), StoreIndentDataOutput.class))
				.thenReturn(new StoreIndentDataOutput());

		// Call the controller method
		List<StoreIndentDataOutput> result = storeIndentController.getStoreIndentList(mock(Model.class));

		// Verify the interactions and assertions
		assertNotNull(result);
		assertEquals(result.size(), 1);
	}

	@Test
	public void testGetStoreIndentProductsListData() throws Exception {
		// Mock input data
		String indentId = "{\"id\": 1}";

		// Create a mock IndentId object
		IndentId indentIdObj = new IndentId();
		indentIdObj.setIndentId(1);

		// Create a mock list of StoreIndentProducts
		List<StoreIndentProducts> mockStoreIndentProductsList = new ArrayList<>();
		// Add mock StoreIndentProducts to the list

		// Mock the behavior of storeIndentsDao.getStoreIndentsProductsList()
		when(storeIndentsDao.getStoreIndentsProductsList(Mockito.any(IndentId.class)))
				.thenReturn(mockStoreIndentProductsList);

		// Mock the behavior of objectMapper.readValue()
		when(objectMapper.readValue(Mockito.eq(indentId), Mockito.eq(IndentId.class))).thenReturn(indentIdObj);

		// Call the controller method
		List<StoreIndentProducts> result = storeIndentController.getStoreIndentProductsListData(indentId, null);

		// Verify the interaction with storeIndentsDao
		verify(storeIndentsDao, Mockito.times(1)).getStoreIndentsProductsList(Mockito.any(IndentId.class));

		// Perform assertions on the result
		assertEquals(mockStoreIndentProductsList, result,
				"Returned list of StoreIndentProducts should match the mock list");

		// Additional assertions and verifications
		// ...

		// Assert that the result list is not null
		assertNotNull(result, "Returned list of StoreIndentProducts should not be null");

		// Assert that the result list is empty
		assertTrue(result.isEmpty(), "Returned list of StoreIndentProducts should be empty");
	}

	@Test
	public void testGetStoreIndentProductsList() throws Exception {
		// Mock input data
		String indentId = "{\"id\": 1}";

		// Create a mock IndentId object
		IndentId indentIdObj = new IndentId();
		indentIdObj.setIndentId(1);

		// Create a mock list of StoreIndentProducts
		List<StoreIndentProducts> mockStoreIndentProductsList = new ArrayList<>();

		// Mock the behavior of objectMapper.readValue()
		when(objectMapper.readValue(eq(indentId), eq(IndentId.class))).thenReturn(indentIdObj);

		// Mock the behavior of storeIndentsDao.getStoreIndentsProductsList()
		when(storeIndentsDao.getStoreIndentsProductsList(eq(indentIdObj))).thenReturn(mockStoreIndentProductsList);

		// Create a mock Model object
		Model mockModel = mock(Model.class);

		// Call the controller method
		String viewName = storeIndentController.getStoreIndentProductsList(indentId, mockModel);

		// Verify the interaction with objectMapper
		verify(objectMapper, times(1)).readValue(eq(indentId), eq(IndentId.class));

		// Verify the interaction with storeIndentsDao
		verify(storeIndentsDao, times(1)).getStoreIndentsProductsList(eq(indentIdObj));

		// Verify the interaction with the model
		verify(mockModel, times(1)).addAttribute(eq("productsList"), eq(mockStoreIndentProductsList));

		// Assert the returned view name
		assertEquals(viewName, "store/storeIndentProducts");

		// Additional assertions and verifications
		// ...
	}

	@Test
	public void testCreateStoreIndent() throws Exception {
		// Mock input data
		String jsonData = "{\"id\": 1}";

		// Create a mock StoreIndentsInputList object
		StoreIndentsInputList storeIndentsInputList = new StoreIndentsInputList();
		// Set properties of storeIndentsInputList

		// Create a mock StoreIndentsList object
		StoreIndentsList storeIndentsList = new StoreIndentsList();
		// Set properties of storeIndentsList

		// Mock the behavior of objectMapper.readValue()
		when(objectMapper.readValue(jsonData, StoreIndentsInputList.class)).thenReturn(storeIndentsInputList);

		// Mock the behavior of modelMapper.map()
		when(modelMapper.map(storeIndentsInputList, StoreIndentsList.class)).thenReturn(storeIndentsList);

		// Create a mock Model object
		Model model = mock(Model.class);

		// Call the controller method
		String result = storeIndentController.createStoreIndent(jsonData, model);

		// Verify the interaction with storeIndentsDao
		verify(storeIndentsDao, times(1)).saveStoreIndent(ArgumentMatchers.any(StoreIndentsList.class));

		// Perform assertions on the result
		assertEquals("store/createStoreIndent", result, "Returned view name should match the expected value");

		// Additional assertions and verifications
		// ...
	}

}
