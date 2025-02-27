package mainTrackerTest;

import com.mainTracker.MainTrackerPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class MainTrackerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(MainTrackerPlugin.class);
		RuneLite.main(args);
	}
}