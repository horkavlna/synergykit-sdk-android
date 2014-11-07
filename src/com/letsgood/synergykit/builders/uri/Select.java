package com.letsgood.synergykit.builders.uri;

import java.util.LinkedList;
import java.util.List;

import com.letsgood.synergykit.builders.errors.Errors;
import com.letsgood.synergykit.log.SynergyKITLog;

public class Select {

	/* Attributes */
	private List<String> selectList = new LinkedList<String>();

	/* Select setter */
	public void setSelect(String attribute) {
		if (attribute == null || attribute.length() == 0) {
			SynergyKITLog.print(Errors.MSG_NULL_ARGUMENTS_OR_EMPTY);
		}

		selectList.add(attribute);
	}

	/* Select getter */
	public String getSelect() {
		String fullSelect = null;

		if (selectList.isEmpty())
			return fullSelect;

		// set order by
		for (int i = 0; i < selectList.size(); i++) {

			if (i == 0)
				fullSelect = new String("&$select=" + selectList.get(i));
			else
				fullSelect += "," + selectList.get(i);
		}

		return fullSelect;
	}
}
