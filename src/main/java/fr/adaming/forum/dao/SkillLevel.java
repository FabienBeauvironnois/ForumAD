package fr.adaming.forum.dao;

public enum SkillLevel {
	one("niveau 1"),
	two("niveau 2"),
	three("niveau 3"),
	four("niveau 4"),
	five("niveau 5");

	private String level;
	private SkillLevel(String level) {
		this.setLevel(level);
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
