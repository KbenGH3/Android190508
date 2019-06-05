package Player;

public class Player {

    public int player_id;
    public String name;
    public String position;
    public String remark;

    public Player(int i){
        player_id=i;
        name = "name_" + String.valueOf(player_id);
        position="";
        remark="new gen player";
    }
}
