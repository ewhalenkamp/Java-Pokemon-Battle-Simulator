public class BaseAbility {
    
    private int id;
    private String name,displayname;
    
    public BaseAbility(int id, String name) {
        this.id = id;
        this.name = name;
        String str = name;
        String s = str.substring(0,1).toUpperCase();
        str = s + str.substring(1);
        int index = 0;
        if (name.contains("-")) {
            index = name.indexOf("-");
            String st = str.substring(0,index+1);
            s = str.substring(index+1,index+2).toUpperCase();
            str = st + s + str.substring(index+2,str.length());
        }
        if (name.contains("-")) {
            index = name.indexOf("-",index+1);
            String st = str.substring(0,index+1);
            s = str.substring(index+1,index+2).toUpperCase();
            str = st + s + str.substring(index+2,str.length());
        }
        
        str = str.replace("-"," ");
        
        this.displayname = str;
    }
    
    public String getDataName() {
        return this.name;
    }
    
    public String getName() {
        return this.displayname;
    }
    
    public int getID() {
        return this.id;
    }
}