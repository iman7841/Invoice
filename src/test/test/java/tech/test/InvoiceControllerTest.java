package tech.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.XLSXConnectivity.XLSXFileRead;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import tech.test.controller.InvoiceController;

@RunWith(DataProviderRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class InvoiceControllerTest {

	@ClassRule
	public static final SpringClassRule SCR = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

	private MockMvc mockMvc;
	private MessageSource messageSourceMock;
	private MockHttpServletRequest requestMock;
	private MockHttpServletResponse responseMock;
	// private HandlerAdapter handlerAdapter;
	private ObjectMapper mapper;
	@SuppressWarnings("deprecation")
	AnnotationMethodHandlerAdapter handlerAdapter;
	/* @Autowired private RequestMappingHandlerMapping mappingHandler;
	 * 
	 * @Autowired private RequestMappingHandlerAdapter adapterHandler; */

	@Autowired
	InvoiceController invoiceController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {

		requestMock = new MockHttpServletRequest();
		responseMock = new MockHttpServletResponse();

		requestMock.setContentType(MediaType.APPLICATION_JSON_VALUE);
		requestMock.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, Boolean.FALSE);

		handlerAdapter = new AnnotationMethodHandlerAdapter();

//		HttpMessageConverter[] messageConverters = { new MappingJackson2HttpMessageConverter() };
//		handlerAdapter.setMessageConverters(messageConverters);
		mapper = new ObjectMapper();
	}

	@UseDataProvider("invoiceData") // this annotation use for read external data sheet or read data
	// String name , String age ,String expected
	@Test
	public void mapPassedInController(String invoiceId, String productId, String expected) throws Exception {
		Map<String, String> request = new HashMap<String, String>();
		Map<String, String> response = new HashMap<String, String>();
		String output = "";
		request.put("invoiceId", invoiceId);
		request.put("productId", productId);

		requestMock.setMethod("POST");
		requestMock.setRequestURI("/rest/addProductToBill");

		// method for Map to json convert and set content type
		String req = toJsonString(request);
		requestMock.setContent(req.getBytes());
		handlerAdapter.handle(requestMock, responseMock, invoiceController);
		response = mapper.readValue(responseMock.getContentAsString(), HashMap.class);
		
		Object invoiceMaster = response.get("invoice");
		
		if (invoiceMaster == null) {
			output = "success";
		} else {
			output = "error";
		}

		assertEquals(output, expected);

	}

	// this method for convert Map String to JSONString ..
	private String toJsonString(Map<String, ?> map) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(map);

	}

	@DataProvider
	public static Object[][] invoiceData() {
		Object[][] store = null;
		try {
			String fileread = System.getProperty("user.dir") + "/src/test/resources/invoiceData.xlsx";

			XLSXFileRead read = new XLSXFileRead(fileread);
			int totalraw = read.TotalRowCount(fileread, "Sheet1");
			store = new Object[totalraw][3];
			for (int i = 1; i <= totalraw; i++) {
				store[i - 1][0] = read.getCellValue("Sheet1", i, 0);
				store[i - 1][1] = read.getCellValue("Sheet1", i, 1);
				store[i - 1][2] = read.getCellValue("Sheet1", i, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return store;
	}
}