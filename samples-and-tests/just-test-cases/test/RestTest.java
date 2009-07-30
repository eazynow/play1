import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import play.libs.WS;
import play.libs.WS.FileParam;
import play.test.UnitTest;


public class RestTest extends UnitTest{
	static Map<String, Object> params;
	@Before
	public void setUp() {
		params = new HashMap<String, Object>();
		params.put("timestamp", 1200000L);
		params.put("cachable", true);
		params.put("multipleValues", new String[]{"欢迎", "dobrodošli", "ยินดีต้อนรับ"});
	}
	
	@Test
	public void testGet(){
		assertEquals("对!", WS.url("http://localhost:9003/ressource/%s","ééééééçççççç汉语漢語").get().getString());
	}
	@Test
	public void testPost(){
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("id", 101);
		assertEquals(jsonResponse, WS.url("http://localhost:9003/ressource/%s", "名字").params(params).post().getJSONObject());
		File fileToSend = new File(getClass().getResource("/kiki.txt").getFile());
		assertTrue(fileToSend.exists());
		assertEquals("POSTED!", WS.url("http://localhost:9003/ressource/file/%s", "名字").files(new FileParam(fileToSend, "file")).post().getString());
		assertEquals("FILE AND PARAMS POSTED!", WS.url("http://localhost:9003/ressource/fileAndParams/%s", "名字").files(new FileParam(fileToSend, "file")).params(params).post().getString());
		
	}
	@Test
	public void testPut(){
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("id", 101);
		assertEquals(jsonResponse, WS.url("http://localhost:9003/ressource/%s", "名字").params(params).put().getJSONObject());
		File fileToSend = new File(getClass().getResource("/kiki.txt").getFile());
		assertTrue(fileToSend.exists());
		assertEquals("POSTED!", WS.url("http://localhost:9003/ressource/file/%s", "名字").files(new FileParam(fileToSend, "file")).put().getString());
		assertEquals("FILE AND PARAMS POSTED!", WS.url("http://localhost:9003/ressource/fileAndParams/%s", "名字").files(new FileParam(fileToSend, "file")).params(params).put().getString());
	}
}
