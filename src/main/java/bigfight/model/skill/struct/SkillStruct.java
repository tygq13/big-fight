package bigfight.model.skill.struct;

public class SkillStruct {
    public SkillType type;
    public String description;
    public SkillIdentity identity;
    public String name;

    public SkillStruct(SkillType type, String description, SkillIdentity identity,  String name) {
        this.type = type;
        this.description = description;
        this.identity = identity;
        this.name = name;
    }
}
