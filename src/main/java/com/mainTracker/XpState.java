package com.mainTracker;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;

@Slf4j
public class XpState implements DataState {
	private Map<String, Integer> xpMap;
	
	public XpState(Client client) {
		xpMap = new HashMap<>();
		for (Skill skill : Skill.values()) {
			xpMap.put(skill.getName(), client.getSkillExperience(skill));
		}
	}	
	
	@Override
	public Object get() {
        return xpMap;
	}
}
