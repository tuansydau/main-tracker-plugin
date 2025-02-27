package com.mainTracker;

import com.google.inject.Provides;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.task.Schedule;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;

@Slf4j
@PluginDescriptor(
	name = "Main Tracker"	
)
public class MainTrackerPlugin extends Plugin
{
	@Inject
	private Client client;
	
//	@Inject
//	private MainTrackerConfig config;
	private static final int SECONDS_BETWEEN_UPLOADS = 5;
	private static final String MAINTRACKER_API_URL = "localhost:8080/api/";
	OkHttpClient httpClient = new OkHttpClient();
	public static final MediaType JSON = MediaType.get("application/json");
	
//	Initialize JSON builder
	Gson gson = new Gson();

	@Override
	protected void startUp() throws Exception
	{
		System.out.println("MainTracker started.");
	}

	@Override
	protected void shutDown() throws Exception
	{
		System.out.println("MainTracker ended.");
	}
	
	String postUserData(String url, String requestObject) throws IOException {
		RequestBody body = RequestBody.create(JSON, requestObject);
		Request request = new Request.Builder()
				.url(url)
			    .post(body)
			    .build();
		  try (Response response = httpClient.newCall(request).execute()) {
			    return response.body().string();
		  }
		  catch (IOException e) {
			    // Handle network-related issues
			    System.out.println("Connection error: " + e.getMessage());
			    throw new RuntimeException("Connection error occurred", e);
			}
	}
	
	@Schedule(period=SECONDS_BETWEEN_UPLOADS, unit=ChronoUnit.SECONDS)
	public void updateSkills() throws IOException {
		HashMap<String, Object> updatedSkills = new HashMap<>();
		if (notLoggedIn()) {
			return;
		}
		
//		Create JSON of username, Hash, Skills
		XpState xpState = new XpState(client);	
		updatedSkills.put("username", client.getLauncherDisplayName());
		updatedSkills.put("accountHash", Long.toString(client.getAccountHash()));
		updatedSkills.put("skills", xpState.get());
		
//		Send HTTP request to my gleam server
		String requestObject = gson.toJson(updatedSkills);
		
		postUserData(MAINTRACKER_API_URL, requestObject);
		
	}

	@Provides
	MainTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(MainTrackerConfig.class);
	}
	
	private boolean notLoggedIn() {
		
		return client.getGameState() != GameState.LOGGED_IN || client.getLocalPlayer() == null;
	}
}
