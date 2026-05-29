package com.mainTracker;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;

@Slf4j
public class QuestsState implements DataState {
	private final Map<String, String> quests;

	public QuestsState(Client client) {
		quests = new HashMap<>();
		for (Quest quest : Quest.values()) {
			QuestState state = quest.getState(client);
			if (state != QuestState.NOT_STARTED) {
				quests.put(quest.name(), state.name());
			}
		}
	}

	@Override
	public Object get() {
		return quests;
	}
}
