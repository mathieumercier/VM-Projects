package fr.umlv.smalljs.jvminterp;

import java.util.ArrayList;

class Dictionary {
	private final ArrayList<Object> dictionnary = new ArrayList<>();

	int register(Object object) {
		int id = dictionnary.size();
		dictionnary.add(object);
		return id;
	}

	Object lookup(int id) {
		return dictionnary.get(id);
	}
}