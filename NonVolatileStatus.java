public class NonVolatileStatus {
    
    private String name,displayname;
    private Double compoundingby;
    private Double maxHealthDamage;
    
    public NonVolatileStatus(String displayname, Double maxHealthDamage, Double compoundingby) {
        this.displayname = displayname;
        this.name = this.displayname.toLowerCase();
        this.name = this.name.replace(" ","-");
        this.maxHealthDamage= maxHealthDamage;
        this.compoundingby = compoundingby;
    }
    
    public String getName() {
        return this.displayname;
    }
    
    public String getDataName() {
        return this.name;
    }
    
    public Double getMaxHealthDamage() {
        return this.maxHealthDamage;
    }
    
    public Double getCompoundingBy() {
        return this.compoundingby;
    }
    
    
    
}