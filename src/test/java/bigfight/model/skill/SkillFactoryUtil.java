package bigfight.model.skill;

public class SkillFactoryUtil {
    private static SkillData defaultSkillData = new SkillData();
    public static SkillFactory DEFAULT_SKILL_FACTORY = new SkillFactory(defaultSkillData);
}
