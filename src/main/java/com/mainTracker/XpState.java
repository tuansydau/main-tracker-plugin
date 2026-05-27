package com.mainTracker;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;

@Slf4j
public class XpState implements DataState {
	private final Map<String, Map<String, Integer>> skills;

	public XpState(Client client) {
		skills = new HashMap<>();
		for (Skill skill : Skill.values()) {
			Map<String, Integer> stat = new HashMap<>();
			stat.put("xp", client.getSkillExperience(skill));
			stat.put("level", client.getRealSkillLevel(skill));
			stat.put("boostedLevel", client.getBoostedSkillLevel(skill));
			skills.put(skill.name(), stat);
		}
	}

	@Override
	public Object get() {
		return skills;
	}
}
